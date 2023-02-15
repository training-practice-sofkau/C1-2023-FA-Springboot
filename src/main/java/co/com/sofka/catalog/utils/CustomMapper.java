package co.com.sofka.catalog.utils;

import co.com.sofka.catalog.dto.CourseDTO;
import co.com.sofka.catalog.dto.StudentDTO;
import co.com.sofka.catalog.entity.Course;
import co.com.sofka.catalog.entity.Student;

import java.util.stream.Collectors;


public class CustomMapper{

    public static Course course(CourseDTO courseDTO){
        Course c = new Course();
        c.setId(courseDTO.getIdDTO());
        c.setName(courseDTO.getNameDTO());
        c.setCoach(courseDTO.getCoachDTO());
        c.setLevel(courseDTO.getLevelDTO());
        c.setLastUpdated(courseDTO.getLastUpdatedDTO());
        //c.setStudentList(courseDTO.getStudentListDTO().stream().map(CustomMapper::student).collect(Collectors.toList()));

        return c;

    }


    public static Student student(StudentDTO studentDTO){
        Student s = new Student();
        s.setId(studentDTO.getIdDTO());
        s.setName(studentDTO.getNameDTO());
        s.setIdNum(studentDTO.getIdNumDTO());
        s.setAge(studentDTO.getAgeDTO());
        s.setMail(studentDTO.getMailDTO());
        s.setCourse(studentDTO.getCourseDTO());
        return s;

    }


    public static CourseDTO courseDTO(Course course){
        CourseDTO c = new CourseDTO();
        c.setIdDTO(course.getId());
        c.setNameDTO(course.getName());
        c.setCoachDTO(course.getCoach());
        c.setLevelDTO(course.getLevel());
        c.setLastUpdatedDTO(course.getLastUpdated());
        c.setStudentListDTO(course.getStudentList().stream().map(CustomMapper::studentDTO).collect(Collectors.toList()));

        return c;

    }


    public static StudentDTO studentDTO(Student student){
        StudentDTO s = new StudentDTO();
        s.setIdDTO(student.getId());
        s.setNameDTO(student.getName());
        s.setIdNumDTO(student.getIdNum());
        s.setAgeDTO(student.getAge());
        s.setMailDTO(student.getMail());
        s.setCourseDTO(student.getCourse());

        return s;

    }
}
