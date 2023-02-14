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
        c.setStudentList(courseDTO.getStudentListDTO().stream().map(CustomMapper::student).collect(Collectors.toList()));

        return c;

    }


    public static Student student(StudentDTO studentDTO){
        Student s = new Student();
        s.setId(studentDTO.getId());
        s.setName(studentDTO.getName());
        s.setIdentificationNum(studentDTO.getIdentificationNum());
        s.setAge(studentDTO.getAge());
        s.setMail(studentDTO.getMail());
        s.setNumCourses(studentDTO.getNumCourses());

        return s;

    }


    public static CourseDTO courseDTO(Course course){
        CourseDTO c = new CourseDTO();
        c.setId(course.getId());
        c.setName(course.getName());
        c.setCoach(course.getCoach());
        c.setLevel(course.getLevel());
        c.setLastUpdated(course.getLastUpdated());
        c.setStudentListDTO(course.getStudentList().stream().map(CustomMapper::studentDTO).collect(Collectors.toList()));

        return c;

    }


    public static StudentDTO studentDTO(Student student){
        StudentDTO s = new StudentDTO();
        s.setId(student.getId());
        s.setName(student.getName());
        s.setIdentificationNum(student.getIdentificationNum());
        s.setAge(student.getAge());
        s.setMail(student.getMail());
        s.setNumCourses(student.getNumCourses());

        return s;

    }
}
