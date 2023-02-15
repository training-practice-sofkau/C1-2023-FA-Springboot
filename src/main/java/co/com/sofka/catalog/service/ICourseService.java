package co.com.sofka.catalog.service;

import co.com.sofka.catalog.dto.CourseDTO;
import co.com.sofka.catalog.entity.Course;

import java.util.List;

public interface ICourseService {
    List<Course> getAllCourses();
    List<Course> getByName(String name);
    List<Course> getByCoach(String c);
    List<Course> getByLevel(String level);

    CourseDTO saveCourse(CourseDTO courseDTO);

    CourseDTO editCourse(CourseDTO courseDTO);
    String deleteCourse(String id);

}
