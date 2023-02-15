package co.com.sofka.catalog.utils;

import co.com.sofka.catalog.dto.CourseDTO;
import co.com.sofka.catalog.dto.StudentDTO;
import co.com.sofka.catalog.entity.Course;
import co.com.sofka.catalog.entity.Student;
import org.modelmapper.ModelMapper;


public class CustomMapper{

    static ModelMapper mapper = new ModelMapper();

    public static Course course(CourseDTO courseDTO){
        //Course c = new Course();
        //c.setId(courseDTO.getId());
        //c.setName(courseDTO.getName());
        //c.setCoach(courseDTO.getCoach());
        //c.setLevel(courseDTO.getLevel());
        //c.setLastUpdated(courseDTO.getLastUpdated());
        //c.setStudentList(courseDTO.getStudentList().stream().map(CustomMapper::student).toList());


        return mapper.map(courseDTO, Course.class);

    }


    public static Student student(StudentDTO studentDTO){
        //Student s = new Student();
        //s.setId(studentDTO.getId());
        //s.setName(studentDTO.getName());
        //s.setIdNum(studentDTO.getIdNum());
        //s.setAge(studentDTO.getAge());
        //s.setMail(studentDTO.getMail());
        //s.setNumCourses(studentDTO.getNumCourses());

        return mapper.map(studentDTO, Student.class);

    }


    public static CourseDTO courseDTO(Course course){
        //CourseDTO c = new CourseDTO();
        //c.setId(course.getId());
        //c.setName(course.getName());
        //c.setCoach(course.getCoach());
        //c.setLevel(course.getLevel());
        //c.setLastUpdated(course.getLastUpdated());
        //c.setStudentList(course.getStudentList().stream().map(CustomMapper::studentDTO).toList());

        return mapper.map(course, CourseDTO.class);

    }


    public static StudentDTO studentDTO(Student student){
        //StudentDTO s = new StudentDTO();
        //s.setId(student.getId());
        //s.setName(student.getName());
        //s.setIdNum(student.getIdNum());
        //s.setAge(student.getAge());
        //s.setMail(student.getMail());
        //s.setNumCourses(student.getNumCourses());

        return mapper.map(student, StudentDTO.class);

    }
}
