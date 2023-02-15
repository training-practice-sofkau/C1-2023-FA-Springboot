package co.com.sofka.catalog.utils;

import co.com.sofka.catalog.dto.CourseDTO;
import co.com.sofka.catalog.dto.StudentDTO;
import co.com.sofka.catalog.entity.Course;
import co.com.sofka.catalog.entity.Student;

import java.time.LocalDate;
import java.util.Date;
import java.util.stream.Collectors;


public class CustomMapper{

    public static Course course(CourseDTO courseDTO){
        Course c = new Course();
        LocalDate date = LocalDate.now();
        c.setId(courseDTO.getId());
        c.setName(courseDTO.getName());
        c.setCoach(courseDTO.getCoach());
        c.setLevel(courseDTO.getLevel());
        c.setLastUpdated(date);

        return c;

    }


    public static Student student(StudentDTO studentDTO){
        Student s = new Student();
        s.setId(studentDTO.getId());
        s.setName(studentDTO.getName());
        s.setAge(studentDTO.getAge());
        s.setIdNum(studentDTO.getIdNum());
        s.setMail(studentDTO.getMail());

        return s;

    }


    public static CourseDTO courseDTO(Course course){
        CourseDTO c = new CourseDTO();
        c.setId(course.getId());
        c.setName(course.getName());
        c.setCoach(course.getCoach());
        c.setLevel(course.getLevel());

        return c;

    }


    public static StudentDTO studentDTO(Student student){
        StudentDTO s = new StudentDTO();
        s.setId(student.getId());
        s.setName(student.getName());
        s.setAge(student.getAge());
        s.setMail(student.getMail());
        s.setIdNum(student.getIdNum());

        return s;
    }
}
