package co.com.sofka.catalog.service;

import co.com.sofka.catalog.dto.CourseDTO;
import co.com.sofka.catalog.dto.StudentDTO;
import co.com.sofka.catalog.entity.Course;

import java.util.List;

public interface ICourseService {

    Course createCourse(CourseDTO courseDTO);
    List<Course> getAllCourses();
    CourseDTO getByName(String name);
    List<CourseDTO> getByCoach(String c);
    List<CourseDTO> getByLevel(Integer level);
    Course editCourse(CourseDTO courseDTO);
    void deleteCourse(String id);

}
