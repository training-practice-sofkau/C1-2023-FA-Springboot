package co.com.sofka.catalog.service;

import co.com.sofka.catalog.dto.StudentDTO;
import co.com.sofka.catalog.entity.Student;

import java.util.List;

public interface IStudentService {
    List<StudentDTO> getAllStudents();
    StudentDTO getByIdentificationNumber(String idNum);
    StudentDTO getByName(String s);
    StudentDTO saveStudent(Student student);
    StudentDTO editStudent(Student student);
    String deleteStudent(String idNum);
}
