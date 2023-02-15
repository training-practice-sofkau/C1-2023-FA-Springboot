package co.com.sofka.catalog.service;

import co.com.sofka.catalog.dto.StudentDTO;
import co.com.sofka.catalog.entity.Student;

import java.util.List;

public interface IStudentService {
    List<StudentDTO> getAllStudents();
    StudentDTO getByIdentificationNumber(String idNum);
    StudentDTO getByName(String name);
    StudentDTO saveStudent(StudentDTO studentDTO);
    StudentDTO editStudent(StudentDTO studentDTO);
    void deleteStudent(String id);
}
