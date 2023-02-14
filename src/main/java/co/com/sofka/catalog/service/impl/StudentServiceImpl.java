package co.com.sofka.catalog.service.impl;

import co.com.sofka.catalog.dto.CourseDTO;
import co.com.sofka.catalog.dto.StudentDTO;
import co.com.sofka.catalog.entity.Course;
import co.com.sofka.catalog.entity.Student;
import co.com.sofka.catalog.repository.CourseRepository;
import co.com.sofka.catalog.repository.StudentRepository;
import co.com.sofka.catalog.service.IStudentService;
import co.com.sofka.catalog.utils.ExceptionsHandler;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class StudentServiceImpl implements IStudentService {

    private StudentRepository studentRepository;
    private ModelMapper modelMapper;

    public StudentServiceImpl(StudentRepository studentRepository, ModelMapper modelMapper) {
        this.studentRepository = studentRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Student dtoToEntity(StudentDTO studentDTO) {
        return modelMapper.map(studentDTO, Student.class);
    }

    @Override
    public StudentDTO entityToDTO(Student student) {
        return modelMapper.map(student, StudentDTO.class);
    }
    @Override
    public List<StudentDTO> getAllStudents() {

        return studentRepository.findAll()
                .stream()
                .map(this::entityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public StudentDTO getByIdentificationNumber(String idNum) {
        return studentRepository.findAll()
                .stream()
                .map(this::entityToDTO)
                .filter(studentDTO -> studentDTO.getName().equalsIgnoreCase(idNum))
                .findFirst()
                .orElseThrow();
    }

    @Override
    public StudentDTO getByName(String name) {
        return studentRepository.findAll()
                .stream()
                .map(this::entityToDTO)
                .filter(studentDTO -> studentDTO.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow();
    }

    @Override
    public StudentDTO saveStudent(StudentDTO studentDTO) {
        return entityToDTO(studentRepository.save(dtoToEntity(studentDTO)));
    }

    @Override
    public StudentDTO editStudent(StudentDTO studentDTO, String studentID) {
        Optional<Student> response = studentRepository.findById(studentID);
        if (response.isEmpty()) {
            throw new ExceptionsHandler("Student not found", HttpStatus.NOT_FOUND);
        }
        StudentDTO oldStudentDTO = entityToDTO(response.get());
        oldStudentDTO.setName(studentDTO.getName());
        oldStudentDTO.setAge(studentDTO.getAge());
        oldStudentDTO.setIdNum(studentDTO.getIdNum());
        oldStudentDTO.setMail(studentDTO.getMail());
        oldStudentDTO.setNumCourses(studentDTO.getNumCourses());

        return entityToDTO(studentRepository.save(dtoToEntity(oldStudentDTO)));
    }

    @Override
    public String deleteStudent(String studentID) {
        Optional<Student> response = studentRepository.findById(studentID);
        if (response.isEmpty()) {
            throw new ExceptionsHandler("Student not found", HttpStatus.NOT_FOUND);
        }
        studentRepository.deleteById(studentID);
        return ("The student with ID: "+studentID+ " has been deleted.");

    }

}
