package co.com.sofka.catalog.service;

import co.com.sofka.catalog.dto.StudentDTO;
import co.com.sofka.catalog.entity.Student;

import java.util.List;

public interface IStudentService {
    Student createStudent(StudentDTO studentDTO);
    List<Student> getAllStudents();
    StudentDTO getByIdentificationNumber(String idNum);

    List<StudentDTO> getByName(String s);
    StudentDTO editStudent(StudentDTO studentDTO);
    void deleteStudent(String idNum);
}
