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
            throw new RuntimeException("Failed course service");
        }
    }
    @Override
    public List<StudentDTO> getByIdNum(String idNum) {
        return null;
    }

    @Override
    public List<StudentDTO> getByName(String s) {
        return null;
    }


    @Override
    public StudentDTO save(StudentDTO studentDTO) {
        return null;
    }

    @Override
    public StudentDTO update(StudentDTO studentDTO) {
        return null;
    }

    @Override
    public Boolean delete(StudentDTO studentDTO) {
        return null;
    }
}
