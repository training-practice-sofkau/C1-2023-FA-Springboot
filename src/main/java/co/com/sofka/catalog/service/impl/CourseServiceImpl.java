package co.com.sofka.catalog.service.impl;


import co.com.sofka.catalog.dto.CourseDTO;
import co.com.sofka.catalog.entity.Course;
import co.com.sofka.catalog.repository.CourseRepository;
import co.com.sofka.catalog.service.ICourseService;
import co.com.sofka.catalog.utils.CustomMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements ICourseService {

    @Autowired
    CourseRepository repository;

    @Override
    public List<CourseDTO> getAll() {
        try {
            List<Course> courses = repository.findAll();
            return courses.stream().map(CustomMapper::courseDTO).collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Failed course service");
        }
    }

    @Override
    public List<CourseDTO> getByName(String name) {
        try {
            List<Course> courses = repository.findByNameContaining(name);
            return courses.stream().map(CustomMapper::courseDTO).collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Failed course service");
        }
    }

    @Override
    public List<CourseDTO> getByCoach(String c) {
        try {
            return null;
        } catch (Exception e) {
            throw new RuntimeException("Failed course service");
        }
    }

    @Override
    public List<CourseDTO> getByLevel(String level) {
        try {
            return null;
        } catch (Exception e) {
            throw new RuntimeException("Failed course service");
        }
    }

    @Override
    public CourseDTO save(CourseDTO courseDTO) {
        try {
            return null;
        } catch (Exception e) {
            throw new RuntimeException("Failed course service");
        }
    }

    @Override
    public CourseDTO update(CourseDTO courseDTO) {
        try {
            return null;
        } catch (Exception e) {
            throw new RuntimeException("Failed course service");
        }
    }

    @Override
    public String delete(CourseDTO courseDTO) {
        try {
            return null;
        } catch (Exception e) {
            throw new RuntimeException("Failed course service");
        }
    }
}
