package co.com.sofka.catalog.utils;

import co.com.sofka.catalog.dto.CourseDTO;
import co.com.sofka.catalog.entity.Course;

import java.util.ArrayList;

public class CourseMapper {

    public CourseDTO toDto(Course course){
        return new CourseDTO(
                course.getCourseId(),
                course.getName(),
                course.getCoach(),
                new ArrayList<>(),
                course.getLevel(),
                course.getLastUpdated()
        );
    }

    public Course toEntity(CourseDTO courseDTO){
        return new Course(
                courseDTO.getId(),
                courseDTO.getName(),
                courseDTO.getCoach(),
                courseDTO.getLevel(),
                courseDTO.getLastUpdated(),
                new ArrayList<>()
        );
    }
}
