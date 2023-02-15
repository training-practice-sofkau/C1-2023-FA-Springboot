package co.com.sofka.catalog.service.impl;

import co.com.sofka.catalog.dto.StudentDTO;
import co.com.sofka.catalog.entity.Student;
import co.com.sofka.catalog.service.IStudentService;
import java.util.List;


public class StudentServiceImpl implements IStudentService {


    @Override
    public Student student(StudentDTO studentDTO) {
        return student(studentDTO);
    }

    @Override
    public StudentDTO studentDTO(Student student) {
        return studentDTO(student);
    }

    @Override
    public List<StudentDTO> getAllStudents() {

        return null;
    }

    @Override
    public StudentDTO getByIdentificationNumber(String idNum) {
        return null;
    }

    @Override
    public StudentDTO getByName(String s) {
        return null;
    }

    @Override
    public StudentDTO saveStudent(StudentDTO studentDTO) {
        return null;
    }

    @Override
    public StudentDTO editStudent(StudentDTO studentDTO) {
        return null;
    }

    @Override
    public String deleteStudent(String idNum) {
        return null;
    }

}
