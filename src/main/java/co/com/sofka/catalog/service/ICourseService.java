package co.com.sofka.catalog.service;

import co.com.sofka.catalog.dto.CourseDTO;
import co.com.sofka.catalog.entity.Course;

import java.util.List;

public interface ICourseService {
    List<CourseDTO> getAllCourses();
    List<CourseDTO> getByName(String name);
    List<CourseDTO> getByCoach(String c);
    List<CourseDTO> getByLevel(String level);
    CourseDTO saveCourse(CourseDTO courseDTO);
    CourseDTO editCourse(CourseDTO courseDTO);
    String deleteCourse(CourseDTO courseDTO);

}
