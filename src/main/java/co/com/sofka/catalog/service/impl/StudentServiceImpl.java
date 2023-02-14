package co.com.sofka.catalog.service.impl;

import co.com.sofka.catalog.dto.StudentDTO;
import co.com.sofka.catalog.entity.Student;
import co.com.sofka.catalog.repository.StudentRepository;
import co.com.sofka.catalog.service.IStudentService;
import co.com.sofka.catalog.utils.CustomMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements IStudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public List<StudentDTO> getAllStudents() {
       return studentRepository.findAll().stream()
               .map(CustomMapper::studentDTO)
               .toList();
    }

    @Override
    public StudentDTO getByIdentificationNumber(String idNum) {
        return CustomMapper.studentDTO(studentRepository.findByIdNum(idNum));
    }

    @Override
    public StudentDTO getByName(String name) {
        return CustomMapper.studentDTO(studentRepository.findByName(name));
    }

    @Override
    public StudentDTO saveStudent(StudentDTO studentDTO) {
        return CustomMapper.studentDTO(studentRepository.save(CustomMapper.student(studentDTO)));
    }

    @Override
    public StudentDTO editStudent(StudentDTO studentDTO) {
        Student student = studentRepository.findById(studentDTO.getId())
                .orElseThrow(() -> new EntityNotFoundException("Student not found"));
        student.setName(studentDTO.getName());
        student.setAge(studentDTO.getAge());
        student.setIdNum(studentDTO.getIdNum());
        student.setMail(studentDTO.getMail());
        student.setNumCourses(studentDTO.getNumCourses());

        return CustomMapper.studentDTO(studentRepository.save(student));
    }

    @Override
    public String deleteStudent(String idNum) {
        return null;
    }

}
