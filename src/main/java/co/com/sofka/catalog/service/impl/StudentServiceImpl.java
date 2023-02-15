package co.com.sofka.catalog.service.impl;

import co.com.sofka.catalog.dto.StudentDTO;
import co.com.sofka.catalog.entity.Student;
import co.com.sofka.catalog.repository.StudentRepository;
import co.com.sofka.catalog.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.com.sofka.catalog.utils.CustomMapper;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements IStudentService {

    @Autowired
    StudentRepository studentRepository;
    @Override
    public List<Student> getAllStudents() {
       return studentRepository.findAll();
    }

    @Override
    public StudentDTO getByIdentificationNumber(String idNum) {

        return CustomMapper.studentDTO(studentRepository.findByIdNum(idNum));
    }

    @Override
    public List<Student> getByName(String s) {

        return studentRepository.findByNameContaining(s);
    }

    @Override
    public StudentDTO saveStudent(StudentDTO studentDTO) {

        return CustomMapper.studentDTO(studentRepository.save(CustomMapper.student(studentDTO)));
    }

    @Override
    public StudentDTO editStudent(StudentDTO studentDTO) {
        return CustomMapper.studentDTO(studentRepository.save(CustomMapper.student(studentDTO)));
    }

    @Override
    public String deleteStudent(String idNum) {
        Optional<Student> d = studentRepository.findById(idNum);
        if(d.isEmpty())return null;

        else {
            studentRepository.deleteById(idNum);
            return "Student deleted";
        }
    }

}
