package co.com.sofka.catalog.service;

import co.com.sofka.catalog.dto.CourseDTO;
import co.com.sofka.catalog.entity.Course;

import java.util.List;

public interface ICourseService {
    List<CourseDTO> getAllCourses();
    CourseDTO getByName(String name);
    List<CourseDTO> getByCoach(String coach);
    List<CourseDTO> getByLevel(String level);
    CourseDTO editCourse(Course course);
    String deleteCourse(Course course);

}
