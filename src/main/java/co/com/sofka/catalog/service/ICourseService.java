package co.com.sofka.catalog.service;

import co.com.sofka.catalog.dto.CourseDTO;
import co.com.sofka.catalog.entity.Course;

import java.util.List;
import java.util.Optional;

public interface ICourseService {


    List<CourseDTO> getAllCourses();
    List<CourseDTO> getByName(String name);
    List<CourseDTO> getByCoach(String c);
    List<CourseDTO> getByLevel(Integer level);

    CourseDTO regisStudent(String courseId, String studentId);
    Optional<CourseDTO> findById(String courseId);

    CourseDTO saveCourse(CourseDTO courseDTO);
    CourseDTO editCourse(CourseDTO courseDTO);
    String deleteCourse(String courseId);

}
