package co.com.sofka.catalog.service;


import co.com.sofka.catalog.dto.StudentDTO;
import co.com.sofka.catalog.entity.Student;

import java.util.List;

public interface IStudentService {

    //Mapping operations
    Student dtoToEntity(StudentDTO studentDTO);
    StudentDTO entityToDTO(Student student);

    //Basic operations
    List<StudentDTO> getAllStudents();
    List<StudentDTO> getAllUnsubscribedStudents();
    StudentDTO getByIdentificationNumber(String idNumber);
    StudentDTO getByName(String name);
    StudentDTO saveStudent(StudentDTO studentDTO);
    StudentDTO editStudent(StudentDTO studentDTO);
    String deleteStudent(String studentId);
}
