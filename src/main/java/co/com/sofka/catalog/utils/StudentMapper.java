
package co.com.sofka.catalog.utils;


import co.com.sofka.catalog.dto.StudentDTO;
import co.com.sofka.catalog.entity.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper {


    public static StudentDTO toDto(Student student){
        return new StudentDTO(
                student.getStudentId(),
                student.getName(),
                student.getIdNum(),
                student.getAge(),
                student.getMail(),
                CourseMapper.toDtoNoStudent(student.getCourse())
        );
    }

    public static Student toEntity(StudentDTO studentDTO){
        return new Student(
                studentDTO.getId(),
                studentDTO.getName(),
                studentDTO.getIdNum(),
                studentDTO.getAge(),
                studentDTO.getMail(),
                CourseMapper.toEntityNoStudent(studentDTO.getCourseDTO())
        );
    }

    public static StudentDTO toDtoNoCourse(Student student){
        return new StudentDTO(
                student.getStudentId(),
                student.getName(),
                student.getIdNum(),
                student.getAge(),
                student.getMail(),
                null
        );
    }

    public static Student toEntityNoCourse(StudentDTO studentDTO){
        return new Student(
                studentDTO.getId(),
                studentDTO.getName(),
                studentDTO.getIdNum(),
                studentDTO.getAge(),
                studentDTO.getMail(),
                null
        );
    }

}

