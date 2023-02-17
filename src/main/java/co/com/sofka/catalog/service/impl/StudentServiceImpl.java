package co.com.sofka.catalog.service.impl;

import co.com.sofka.catalog.dto.StudentDTO;
import co.com.sofka.catalog.entity.Course;
import co.com.sofka.catalog.entity.Student;
import co.com.sofka.catalog.exceptions.ToDoExceptions;
import co.com.sofka.catalog.repository.CourseRepository;
import co.com.sofka.catalog.repository.StudentRepository;
import co.com.sofka.catalog.service.IStudentService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements IStudentService {

    private final ModelMapper modelMapper;
    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;

    public StudentServiceImpl(ModelMapper modelMapper, StudentRepository studentRepository, CourseRepository courseRepository) {
        this.modelMapper = modelMapper;
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
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
                .toList();
    }

    @Override
    public List<StudentDTO> getAllUnsubscribedStudents() {
        return studentRepository
                .findAll()
                .stream()
                .map(this::entityToDTO)
                .filter(x -> x.getCourse() == null)
                .toList();
    }

    @Override
    public StudentDTO getByIdentificationNumber(String idNumber) {
        Optional<Student> response = studentRepository
                .findAll()
                .stream()
                .filter(x -> x.getIdNumber().equals(idNumber))
                .findFirst();
        if(response.isEmpty()){
            throw new ToDoExceptions("Student not found", HttpStatus.NOT_FOUND);
        }
        return entityToDTO(response.get());
    }

    @Override
    public StudentDTO getByName(String name) {
        Optional<Student> response = studentRepository
                .findAll()
                .stream()
                .filter(x -> x.getName().toLowerCase().startsWith(name.toLowerCase()))
                .findFirst();
        if(response.isEmpty()){
            throw new ToDoExceptions("Student not found", HttpStatus.NOT_FOUND);
        }
        return entityToDTO(response.get());
    }

    @Override
    public StudentDTO saveStudent(StudentDTO studentDTO) {
        studentRepository.save(dtoToEntity(studentDTO));
        return studentDTO;
    }

    @Override
    public StudentDTO editStudent(StudentDTO studentDTO) {
        Optional<Student> studentResponse = studentRepository.findById(studentDTO.getStudentId());
        if (studentResponse.isEmpty()) {
            throw new ToDoExceptions("Student not found", HttpStatus.NOT_FOUND);
        }
        if (studentDTO.getCourse() != null){
            Optional<Course> courseResponse = courseRepository.findById(studentDTO.getCourse().getCourseId());
            if (courseResponse.isEmpty()) {
                throw new ToDoExceptions("Course not found", HttpStatus.NOT_FOUND);
            }
        }
        studentRepository.save(dtoToEntity(studentDTO));
        return studentDTO;
    }

    @Override
    public String deleteStudent(String studentId) {
        Optional<Student> response = studentRepository.findById(studentId);
        if (response.isEmpty()) {
            throw new ToDoExceptions("Student not found", HttpStatus.NOT_FOUND);
        }
        studentRepository.deleteById(studentId);
        return "The student was deleted";
    }

}
