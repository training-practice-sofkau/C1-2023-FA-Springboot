package co.com.sofka.catalog.service;

import co.com.sofka.catalog.dto.StudentDTO;

import java.util.List;

public interface IStudentService {
    List<StudentDTO> getAllStudents();
    List<StudentDTO> getByDni(String dni);

    List<StudentDTO> getByName(String name);
    StudentDTO saveStudent(StudentDTO studentDTO);
    StudentDTO editStudent(StudentDTO studentDTO, String studentID);
    String deleteStudent(String idNum);
}
