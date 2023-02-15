package co.com.sofka.catalog.service.impl;

import co.com.sofka.catalog.dto.CourseDTO;
import co.com.sofka.catalog.dto.StudentDTO;
import co.com.sofka.catalog.entity.Course;
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
    StudentRepository repository;

    @Override
    public List<StudentDTO> getAll() {
        try {
            List<Student> students= repository.findAll();
            return students.stream().map(CustomMapper::studentDTO).collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage() + "In Student service");
        }
    }
    @Override
    public List<StudentDTO> getByIdNum(String idNum) {
        try {
            List<Student> students= repository.findByIdNum(idNum);
            return students.stream().map(CustomMapper::studentDTO).collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage() + "In Student service");
        }
    }

    @Override
    public List<StudentDTO> getByName(String name) {
        try {
            List<Student> students= repository.findByNameContaining(name);
            return students.stream().map(CustomMapper::studentDTO).collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage() + "In Student service");
        }
    }


    @Override
    public StudentDTO save(StudentDTO studentDTO) {
        try {
            studentDTO.setStudentId(null);
            return CustomMapper.studentDTO(repository.save(CustomMapper.student(studentDTO)));
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage() + "In Student service");
        }
    }

    @Override
    public StudentDTO update(StudentDTO studentDTO) {
        try {
            Student student = CustomMapper.student(studentDTO);
            if (repository.existsById(student.getStudentId())) {
                return CustomMapper.studentDTO(repository.save(student));
            } else {
                throw new RuntimeException("Student not found");
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage() + "In Student service");
        }
    }

    @Override
    public Boolean delete(String studentId) {
        try {
            if (repository.existsById(studentId)) {
                repository.deleteById(studentId);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage() + "In Course service");
        }
    }
}
