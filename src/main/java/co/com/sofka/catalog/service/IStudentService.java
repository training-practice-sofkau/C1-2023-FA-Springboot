package co.com.sofka.catalog.service;

import co.com.sofka.catalog.dto.CourseDTO;
import co.com.sofka.catalog.dto.StudentDTO;
import co.com.sofka.catalog.entity.Course;
import co.com.sofka.catalog.entity.Student;

import java.util.List;

public interface IStudentService {
    Student dtoToEntity(StudentDTO studentDTO);
    StudentDTO entityToDTO(Student student);
    List<StudentDTO> getAllStudents();
    StudentDTO getByIdentificationNumber(String idNum);
    StudentDTO getByName(String s);
    StudentDTO saveStudent(StudentDTO studentDTO);
    StudentDTO editStudent(StudentDTO studentDTO, String studentID);
    String deleteStudent(String studentID);
}
