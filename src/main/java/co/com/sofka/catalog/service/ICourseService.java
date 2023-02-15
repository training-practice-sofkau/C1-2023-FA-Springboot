package co.com.sofka.catalog.service;

import co.com.sofka.catalog.dto.CourseDTO;
import co.com.sofka.catalog.dto.StudentDTO;
import co.com.sofka.catalog.entity.Course;
import co.com.sofka.catalog.entity.Student;

import java.util.List;

public interface ICourseService {

    Course dtoToEntity (CourseDTO courseDTO);
    CourseDTO entityToDto (Course course);

    List<CourseDTO> getAllCourses();
    List<CourseDTO> getByName(String name);
    List<CourseDTO> getByCoach(String c);
    List<CourseDTO> getByLevel(String level);
    CourseDTO saveCourse(CourseDTO courseDTO);
    CourseDTO editCourse(String id, CourseDTO courseDTO);
    CourseDTO deleteCourse(String id);

}
