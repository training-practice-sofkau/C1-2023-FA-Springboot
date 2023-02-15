
package co.com.sofka.catalog.utils;


import co.com.sofka.catalog.dto.StudentDTO;
import co.com.sofka.catalog.entity.Student;

public class StudentMapper {

    public StudentDTO toDto(Student student){
        return new StudentDTO(
                student.getStudentId(),
                student.getName(),
                student.getIdNum(),
                student.getAge(),
                student.getMail(),
                null
        );
    }

    public Student toEntity(StudentDTO studentDTO){
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

