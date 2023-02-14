package co.com.sofka.catalog.service;

import co.com.sofka.catalog.dto.StudentDTO;
import co.com.sofka.catalog.entity.Student;

import java.util.List;

public interface IStudentService {

    Student dtoToEntity (StudentDTO studentDTO);
    StudentDTO entityToDto (Student student);

    List<StudentDTO> getAllStudents();
    StudentDTO getByIdentificationNumber(String idNum);

    List<StudentDTO> getByName(String s);
    StudentDTO saveStudent(StudentDTO studentDTO);
    StudentDTO editStudent(String idNum, StudentDTO studentDTO);
    StudentDTO deleteStudent(String idNum);
}
