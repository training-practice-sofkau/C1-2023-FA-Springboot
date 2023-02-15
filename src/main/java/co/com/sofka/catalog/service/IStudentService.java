package co.com.sofka.catalog.service;

import co.com.sofka.catalog.dto.CourseDTO;
import co.com.sofka.catalog.dto.StudentDTO;
import co.com.sofka.catalog.entity.Student;

import java.util.List;


public interface IStudentService {

    List<StudentDTO> getAll();
    StudentDTO getByIdNum(String idNum);
    StudentDTO getByName(String s);
    StudentDTO save(StudentDTO studentDTO);
    StudentDTO update(StudentDTO studentDTO);
    String delete(StudentDTO studentDTO);
}
