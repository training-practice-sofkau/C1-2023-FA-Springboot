
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
                student.getCourses().size()
        );
    }

}

