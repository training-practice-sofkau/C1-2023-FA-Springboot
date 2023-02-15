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
            throw new RuntimeException(e.getMessage() + "In Course service");
        }
    }

    @Override
    public List<CourseDTO> getByName(String name) {
        try {
            List<Course> courses = repository.findByNameContaining(name);
            return courses.stream().map(CustomMapper::courseDTO).collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage() + "In Course service");
        }
    }

    @Override
    public List<CourseDTO> getByCoach(String coach) {
        try {
            List<Course> courses = repository.findByCoachContaining(coach);
            return courses.stream().map(CustomMapper::courseDTO).collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage() + "In Course service");
        }
    }

    @Override
    public List<CourseDTO> getByLevel(Integer level) {
        try {
            List<Course> courses = repository.findByLevel(level);
            return courses.stream().map(CustomMapper::courseDTO).collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage() + "In Course service");
        }
    }

    @Override
    public CourseDTO save(CourseDTO courseDTO) {
        try {
            courseDTO.setCourseId(null);
            return CustomMapper.courseDTO(repository.save(CustomMapper.course(courseDTO)));
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage() + "In Course service");
        }
    }

    @Override
    public CourseDTO update(CourseDTO courseDTO) {
        try {
            Course course = CustomMapper.course(courseDTO);
            if (repository.existsById(course.getCourseId())) {
                return CustomMapper.courseDTO(repository.save(course));
            } else {
                throw new RuntimeException("Course not found");
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage() + "In Course service");
        }
    }

    @Override
    public Boolean delete(String courseId) {
        try {
            if (repository.existsById(courseId)) {
                repository.deleteById(courseId);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage() + "In Course service");
        }
    }
}
