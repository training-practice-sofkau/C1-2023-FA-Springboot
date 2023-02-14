package co.com.sofka.catalog.service.impl;


import co.com.sofka.catalog.dto.CourseDTO;
import co.com.sofka.catalog.entity.Course;
import co.com.sofka.catalog.service.ICourseService;

import java.util.List;

public class CourseServiceImpl implements ICourseService {

    @Override
    public List<Course> getAllCourses() {
        return null;
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
