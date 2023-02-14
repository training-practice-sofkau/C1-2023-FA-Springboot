package co.com.sofka.catalog.service.impl;

import co.com.sofka.catalog.controller.ItemNotFoundException;
import co.com.sofka.catalog.dto.StudentDTO;
import co.com.sofka.catalog.entity.Student;
import co.com.sofka.catalog.repository.StudentRepository;
import co.com.sofka.catalog.service.IStudentService;
import co.com.sofka.catalog.utils.CustomMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


public class StudentServiceImpl implements IStudentService {

    @Autowired
    StudentRepository studentRepository;

    @Override
    public Student dtoToEntity(StudentDTO studentDTO) {
        return CustomMapper.student(studentDTO);
    }

    @Override
    public StudentDTO entityToDto(Student student) {
        return CustomMapper.studentDTO(student);
    }

    @Override
    public List<StudentDTO> getAllStudents() {
        return studentRepository.findAll()
                .stream().map(this::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public StudentDTO getByIdentificationNumber(String idNum) {
        return entityToDto(studentRepository.findById(idNum).orElseThrow(() -> new ItemNotFoundException(idNum)));
    }

    @Override
    public List<StudentDTO> getByName(String s) {
        return studentRepository.findAll()
                .stream()
                .filter(student -> Objects.equals(student.getName(), s))
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public StudentDTO saveStudent(StudentDTO studentDTO) {
        return entityToDto(studentRepository.save(dtoToEntity(studentDTO)));

    }

    @Override
    public StudentDTO editStudent(String idNum, StudentDTO studentDTO) {
        var studentBody = studentRepository.findById(idNum)
                .map(student -> {
                    student.setName(studentDTO.getName());
                    student.setIdNum(studentDTO.getIdNum());
                    student.setAge(studentDTO.getAge());
                    student.setMail(studentDTO.getMail());
                    student.setNumCourses(studentDTO.getNumCourses());
                    return studentRepository.save(student);
                }).orElseThrow(() -> new ItemNotFoundException(idNum));
        return entityToDto(studentBody);
    }

    @Override
    public StudentDTO deleteStudent(String idNum) {
        var artist = studentRepository.findById(idNum).orElseThrow(() -> new ItemNotFoundException(idNum));
        studentRepository.delete(artist);
        return entityToDto(artist);
    }

}
