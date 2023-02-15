package co.com.sofka.catalog.service;

import co.com.sofka.catalog.dto.CourseDTO;
import co.com.sofka.catalog.entity.Course;

import java.util.List;

public interface ICourseService {
    List<CourseDTO> getAllCourses();
    CourseDTO getByName(String name);

    CourseDTO getById(String id);

    List<CourseDTO> getByCoach(String coach);
    List<CourseDTO> getByLevel(Integer level);
    CourseDTO createCourse(CourseDTO courseDTO);
    CourseDTO editCourse(CourseDTO courseDTO);
    void deleteCourse(String id);

}
