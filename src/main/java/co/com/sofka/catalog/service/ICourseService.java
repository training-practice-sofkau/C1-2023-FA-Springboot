package co.com.sofka.catalog.service;

import co.com.sofka.catalog.dto.CourseDTO;
import co.com.sofka.catalog.entity.Course;

import java.util.List;

public interface ICourseService {

    Course dtoToEntity(CourseDTO courseDTO);
    CourseDTO entityToDTO(Course course);
    List<CourseDTO> getAllCourses();
    List<CourseDTO> getByCourseName(String name, String strategy);
    List<CourseDTO> getByCoach(String c, String strategy);
    List<CourseDTO> getByLevel(String level);
    CourseDTO createCourse (CourseDTO courseDTO);
    CourseDTO editCourse(CourseDTO courseDTO, String courseID);
    String deleteCourse(String courseID);

}
