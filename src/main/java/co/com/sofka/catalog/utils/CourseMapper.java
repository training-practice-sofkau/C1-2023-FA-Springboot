package co.com.sofka.catalog.utils;

import co.com.sofka.catalog.dto.CourseDTO;
import co.com.sofka.catalog.entity.Course;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Component
public class CourseMapper {

    public static CourseDTO toDto(Course course){
        return new CourseDTO(
                course.getCourseId(),
                course.getName(),
                course.getCoach(),
                course.getStudentList()
                        .stream()
                        .map(StudentMapper::toDtoNoCourse)
                        .collect(Collectors.toList()),
                course.getLevel(),
                course.getLastUpdated()
        );
    }

    public static Course toEntity(CourseDTO courseDTO){
        return new Course(
                courseDTO.getId(),
                courseDTO.getName(),
                courseDTO.getCoach(),
                courseDTO.getLevel(),
                courseDTO.getLastUpdated(),
                courseDTO.getStudentListDTO()
                        .stream()
                        .map(StudentMapper::toEntityNoCourse)
                        .collect(Collectors.toList())
                );
    }

    public static CourseDTO toDtoNoStudent(Course course){
        return new CourseDTO(
                course.getCourseId(),
                course.getName(),
                course.getCoach(),
                new ArrayList<>(),
                course.getLevel(),
                course.getLastUpdated()
        );
    }

    public static Course toEntityNoStudent(CourseDTO courseDTO){
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
