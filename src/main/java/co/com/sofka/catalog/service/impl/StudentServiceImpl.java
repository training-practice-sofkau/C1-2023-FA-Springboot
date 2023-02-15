package co.com.sofka.catalog.service.impl;

import co.com.sofka.catalog.dto.StudentDTO;
import co.com.sofka.catalog.entity.Student;
import co.com.sofka.catalog.repository.StudentRepository;
import co.com.sofka.catalog.service.IStudentService;
import co.com.sofka.catalog.utils.CustomMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Service
public class StudentServiceImpl implements IStudentService {

    @Autowired
    StudentRepository studentRepository;
    public Student dtoToEntity(StudentDTO studentDTO) {
        return CustomMapper.student(studentDTO);
    }

    public StudentDTO entityToDto(Student student) {
        return CustomMapper.studentDTO(student);
    }
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
    public StudentDTO getByName(String s) {
        return null;
    }

    @Override
    public StudentDTO editStudent(StudentDTO studentDTO) {
        return null;
    }

    @Override
    public String deleteStudent(String idNum) {
        return null;
    }

}
