package co.com.sofka.catalog.service;

import co.com.sofka.catalog.dto.StudentDTO;
import co.com.sofka.catalog.entity.Student;

import java.util.List;

public interface IStudentService {
    List<StudentDTO> getAllStudents();
    StudentDTO getByIdentificationNumber(String idNum);

    List<StudentDTO> getByName(String s);
    StudentDTO saveStudent(StudentDTO studentDTO);
    StudentDTO editStudent(StudentDTO studentDTO);
    String deleteStudent(String idNum);
}
