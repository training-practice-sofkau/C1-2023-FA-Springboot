package co.com.sofka.catalog.service;

import co.com.sofka.catalog.dto.CourseDTO;
import co.com.sofka.catalog.dto.StudentDTO;
import co.com.sofka.catalog.entity.Student;

import java.util.List;


public interface IStudentService {

    List<StudentDTO> getAll();
    List<StudentDTO> getByIdNum(String idNum);
    List<StudentDTO> getByName(String s);
    StudentDTO save(StudentDTO studentDTO);
    StudentDTO update(StudentDTO studentDTO);
    Boolean delete(StudentDTO studentDTO);
}
