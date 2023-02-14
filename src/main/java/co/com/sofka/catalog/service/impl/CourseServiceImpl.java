package co.com.sofka.catalog.service.impl;


import co.com.sofka.catalog.dto.CourseDTO;
import co.com.sofka.catalog.repository.CourseRepository;
import co.com.sofka.catalog.service.ICourseService;
import co.com.sofka.catalog.utils.CustomMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements ICourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public List<CourseDTO> getAllCourses() {
        return courseRepository.findAll().stream()
                .map(CustomMapper::courseDTO)
                .toList();
    }

    @Override
    public CourseDTO getByName(String name) {
        return CustomMapper.courseDTO(courseRepository.findByName(name));
    }

    @Override
    public List<CourseDTO> getByCoach(String coach) {
        return courseRepository.findByCoach(coach).stream()
                .map(CustomMapper::courseDTO)
                .toList();
    }

    @Override
    public List<CourseDTO> getByLevel(String level) {
        return courseRepository.findByLevel(Integer.parseInt(level)).stream()
                .map(CustomMapper::courseDTO)
                .toList();
    }

    @Override
    public CourseDTO saveCourse(CourseDTO courseDTO) {
        return null;
    }

    @Override
    public CourseDTO editCourse(CourseDTO courseDTO) {
        return null;
    }

    @Override
    public String deleteCourse(CourseDTO courseDTO) {
        return null;
    }
}
