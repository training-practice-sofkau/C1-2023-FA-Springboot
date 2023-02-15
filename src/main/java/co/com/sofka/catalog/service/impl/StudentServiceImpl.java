package co.com.sofka.catalog.service.impl;

import co.com.sofka.catalog.dto.StudentDTO;
import co.com.sofka.catalog.entity.Student;
import co.com.sofka.catalog.repository.StudentRepository;
import co.com.sofka.catalog.service.IStudentService;
import co.com.sofka.catalog.utils.CustomMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class StudentServiceImpl implements IStudentService {

    @Autowired
    StudentRepository studentRepository;
    public Student dtoToEntity(StudentDTO studentDTO) {
        return CustomMapper.student(studentDTO);
    }
    public StudentDTO entityToDto(Student student) { return CustomMapper.studentDTO(student);}
    @Override
    public StudentDTO createStudent(StudentDTO studentDTO) {
       return entityToDto(this.studentRepository.save(dtoToEntity(studentDTO)));
    }
    @Override
    public List<StudentDTO> getAllStudents() {
        return studentRepository.findAll()
                .stream().map(this::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public StudentDTO getByIdentificationNumber(String idNum) {
        return entityToDto(this.studentRepository.findByIdNum(idNum));
    }

    @Override
    public List<StudentDTO> getByName(String s) {
        return this.studentRepository.findByName(s)
                .stream().map(this::entityToDto).collect(Collectors.toList());
    }

    @Override
    public StudentDTO editStudent(StudentDTO studentDTO) {
       return entityToDto(studentRepository.save(dtoToEntity(studentDTO)));
    }

    @Override
    public void deleteStudent(String id) {
        studentRepository.deleteById(id);
    }

    public StudentDTO findStudentById(String id){
        return entityToDto(studentRepository.findById(id).orElse(new Student()));
    }

}
