package co.com.sofka.catalog.service;

import co.com.sofka.catalog.dto.StudentDTO;

import java.util.List;

public interface IStudentService {
    StudentDTO createStudent(StudentDTO studentDTO);
    List<StudentDTO> getAllStudents();
    StudentDTO getByIdentificationNumber(String idNum);

    List<StudentDTO> getByName(String s);
    StudentDTO editStudent(StudentDTO studentDTO);
    String deleteStudent(String idNum);
}
