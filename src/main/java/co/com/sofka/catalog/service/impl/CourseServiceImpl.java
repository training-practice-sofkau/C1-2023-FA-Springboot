package co.com.sofka.catalog.service.impl;


import co.com.sofka.catalog.dto.CourseDTO;
import co.com.sofka.catalog.entity.Course;
import co.com.sofka.catalog.repository.CourseRepository;
import co.com.sofka.catalog.service.ICourseService;
import co.com.sofka.catalog.utils.CourseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements ICourseService {

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    CourseMapper courseMapper;


    @Override
    public List<CourseDTO> getAllCourses() {
        return courseRepository.findAll()
                .stream()
                .map(CourseMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public CourseDTO getByName(String name) {
        return null;
    }

    @Override
    public List<CourseDTO> getByCoach(String coach) {
        return null;
    }

    @Override
    public List<CourseDTO> getByLevel(String level) {
        return null;
    }

    @Override
    public CourseDTO editCourse(Course course) {
        return null;
    }

    @Override
    public String deleteCourse(Course course) {
        return null;
    }
}
