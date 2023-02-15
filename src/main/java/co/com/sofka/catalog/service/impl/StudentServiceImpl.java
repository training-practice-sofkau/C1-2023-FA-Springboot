package co.com.sofka.catalog.service.impl;

import co.com.sofka.catalog.dto.CourseDTO;
import co.com.sofka.catalog.dto.StudentDTO;
import co.com.sofka.catalog.entity.Course;
import co.com.sofka.catalog.entity.Student;
import co.com.sofka.catalog.repository.StudentRepository;
import co.com.sofka.catalog.service.IStudentService;
import co.com.sofka.catalog.utils.CustomMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;


public class StudentServiceImpl implements IStudentService {

    @Autowired
    StudentRepository studentRepository;
    @Override
    public List<StudentDTO> getAllStudents(){
        return studentRepository.findAll()
                .stream().map(CustomMapper::studentDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<StudentDTO> getByDni(String dni){
        return studentRepository.findAll()
                .stream()
                .filter(student -> student.getDni().equals(dni))
                .map(CustomMapper::studentDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<StudentDTO> getByName(String name) {
        return studentRepository.findAll()
                .stream()
                .filter(student -> student.getName().contains(name))
                .map(CustomMapper::studentDTO)
                .collect(Collectors.toList());
    }

    @Override
    public StudentDTO saveStudent(StudentDTO studentDTO) {
        return CustomMapper.studentDTO(
                studentRepository.save(CustomMapper.student(studentDTO))
        );
    }

    @Override
    public StudentDTO editStudent(StudentDTO studentDTO, String studentID) {
        StudentDTO student = CustomMapper.studentDTO(
                studentRepository
                        .findById(studentID)
                        .orElse(new Student()));

        if (student.getId() == null) {
            return student;
        }
        else {
            student.setName(studentDTO.getName());
            student.setDni(studentDTO.getDni());
            student.setAge(studentDTO.getAge());
            student.setEmail(studentDTO.getEmail());
            student.setCourseDTO(studentDTO.getCourseDTO());

            studentRepository.save(CustomMapper.student(student));

            return student;
        }
    }

    @Override
    public String deleteStudent(String studentID) {
        try {
            studentRepository.deleteById(studentID);
            return "Deleted";
        }
        catch (Exception e){
            System.out.println(e);
            return "Unsuccessful";
        }
    }
}
