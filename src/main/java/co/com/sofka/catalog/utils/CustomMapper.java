package co.com.sofka.catalog.utils;

import co.com.sofka.catalog.dto.CourseDTO;
import co.com.sofka.catalog.dto.StudentDTO;
import co.com.sofka.catalog.entity.Course;
import co.com.sofka.catalog.entity.Student;

import java.util.stream.Collectors;


public class CustomMapper{

    public static Course course(CourseDTO courseDTO){
        Course c = new Course();
        c.setId(courseDTO.getId());
        c.setName(courseDTO.getName());
        c.setCoach(courseDTO.getCoach());
        c.setLevel(courseDTO.getLevel());
        c.setLastUpdated(courseDTO.getLastUpdated());
        c.setStudents(courseDTO.getStudentsDTO().stream().map(CustomMapper::student).collect(Collectors.toList()));

        return c;

    }


    public static Student student(StudentDTO studentDTO){
        Student s = new Student();
        s.setId(studentDTO.getId());
        s.setName(studentDTO.getName());
        s.setDni(studentDTO.getDni());
        s.setAge(studentDTO.getAge());
        s.setEmail(studentDTO.getEmail());
        s.setCourse(course(studentDTO.getCourseDTO()));

        return s;

    }


    public static CourseDTO courseDTO(Course course){
        CourseDTO c = new CourseDTO();
        c.setId(course.getId());
        c.setName(course.getName());
        c.setCoach(course.getCoach());
        c.setLevel(course.getLevel());
        c.setLastUpdated(course.getLastUpdated());

        return c;
    }


    public static StudentDTO studentDTO(Student student){
        StudentDTO s = new StudentDTO();
        s.setId(student.getId());
        s.setName(student.getName());
        s.setDni(student.getDni());
        s.setAge(student.getAge());
        s.setEmail(student.getEmail());
        s.setCourseDTO(courseDTO(student.getCourse()));

        return s;

    }
}
