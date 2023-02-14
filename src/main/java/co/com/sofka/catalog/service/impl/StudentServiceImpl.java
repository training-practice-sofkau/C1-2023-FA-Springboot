package co.com.sofka.catalog.service.impl;

import co.com.sofka.catalog.dto.StudentDTO;
import co.com.sofka.catalog.repository.StudentRepository;
import co.com.sofka.catalog.service.IStudentService;
import co.com.sofka.catalog.utils.CustomMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements IStudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public List<StudentDTO> getAllStudents() {
       return studentRepository.findAll().stream()
               .map(CustomMapper::studentDTO)
               .toList();
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
