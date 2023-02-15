package co.com.sofka.catalog.utils;

import co.com.sofka.catalog.dto.CourseDTO;
import co.com.sofka.catalog.dto.StudentDTO;
import co.com.sofka.catalog.entity.Course;
import co.com.sofka.catalog.entity.CourseStudent;
import co.com.sofka.catalog.entity.Student;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class CustomMapper{

    public static Course course(CourseDTO courseDTO){
        Course c = new Course();
        c.setCourseId(courseDTO.getCourseId());
        c.setName(courseDTO.getName());
        c.setCoach(courseDTO.getCoach());
        c.setLevel(courseDTO.getLevel());
        c.setLastUpdated(LocalDate.now());
        return c;

    }


    public static Student student(StudentDTO studentDTO){
        Student s = new Student();
        s.setStudentId(studentDTO.getId());
        s.setName(studentDTO.getName());
        s.setIdNum(studentDTO.getIdNum());
        s.setAge(studentDTO.getAge());
        s.setMail(studentDTO.getMail());
        //s.setNumCourses(studentDTO.getNumCourses());

        return s;

    }


    public static CourseDTO courseDTO(Course course){
        CourseDTO c = new CourseDTO();
        c.setCourseId(course.getCourseId());
        c.setName(course.getName());
        c.setCoach(course.getCoach());
        c.setLevel(course.getLevel());
        c.setLastUpdated(course.getLastUpdated());
        List<StudentDTO> studentListDTO = new ArrayList<>();
        for(CourseStudent courseStudent : course.getStudentList()){
            studentListDTO.add(nestedStudent(courseStudent.getStudent()));
        }
        c.setStudentListDTO(studentListDTO);
        return c;
    }

    private static CourseDTO nestedCourse (Course course){
        CourseDTO c = new CourseDTO();
        c.setCourseId(course.getCourseId());
        c.setName(course.getName());
        c.setCoach(course.getCoach());
        c.setLevel(course.getLevel());
        c.setLastUpdated(course.getLastUpdated());
        return c;
    }

    public static StudentDTO studentDTO(Student student){
        StudentDTO s = new StudentDTO();
        s.setId(student.getStudentId());
        s.setName(student.getName());
        s.setIdNum(student.getIdNum());
        s.setAge(student.getAge());
        s.setMail(student.getMail());
        List<CourseDTO> courseListDTO = new ArrayList<>();
        for(CourseStudent courseStudent : student.getCourseList()){
            courseListDTO.add(nestedCourse(courseStudent.getCourse()));
        }
        s.setCourseListDTO(courseListDTO);
        s.setNumCourses(courseListDTO.size());
        return s;
    }


    private static StudentDTO nestedStudent(Student student){
        StudentDTO s = new StudentDTO();
        s.setId(student.getStudentId());
        s.setName(student.getName());
        s.setIdNum(student.getIdNum());
        s.setAge(student.getAge());
        s.setMail(student.getMail());
        return s;
    }
}
