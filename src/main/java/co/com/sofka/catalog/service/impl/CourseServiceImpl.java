package co.com.sofka.catalog.service.impl;


import co.com.sofka.catalog.dto.CourseDTO;
import co.com.sofka.catalog.entity.Course;
import co.com.sofka.catalog.service.ICourseService;

import java.util.List;

public class CourseServiceImpl implements ICourseService {


    @Override
    public Course dtoToEntity(CourseDTO courseDTO) {
        return null;
    }

    @Override
    public CourseDTO entityToDto(Course course) {
        return null;
    }

    @Override
    public List<CourseDTO> getAllCourses() {
        return null;
    }

    @Override
    public List<CourseDTO> getByName(String name) {
        return null;
    }

    @Override
    public List<CourseDTO> getByCoach(String c) {
        return null;
    }

    @Override
    public List<CourseDTO> getByLevel(String level) {
        return null;
    }

    @Override
    public CourseDTO editCourse(String id, CourseDTO courseDTO) {
        return null;
    }

    @Override
    public CourseDTO deleteCourse(String id) {
        return null;
    }
}
