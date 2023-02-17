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
import org.springframework.boot.Banner;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class StudentServiceImpl implements IStudentService {

    private StudentRepository studentRepository;
    private CourseRepository courseRepository;
    private ModelMapper modelMapper;

    public StudentServiceImpl(StudentRepository studentRepository, CourseRepository courseRepository, ModelMapper modelMapper) {
        this.studentRepository = studentRepository;
        this.modelMapper = modelMapper;
        this.courseRepository=courseRepository;
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
                .sorted(Comparator.comparing(StudentDTO::getName))
                .collect(Collectors.toList());
    }

    @Override
    public StudentDTO getByIdentificationNumber(String idNum) {
        return studentRepository.findAll()
                .stream()
                .map(this::entityToDTO)
                .filter(studentDTO -> studentDTO.getIdNum().equalsIgnoreCase(idNum))
                .findFirst()
                .orElseThrow();
    }

    @Override
    public List<StudentDTO> getByName(String name, String strategy) {
        String loweCaseName = name.toLowerCase();

        switch (strategy){
            case "exactmatch":{
                return studentRepository.findAll()
                        .stream()
                        .map(this::entityToDTO)
                        .filter(studentDTO -> studentDTO.getName().equalsIgnoreCase(loweCaseName))
                        .sorted(Comparator.comparing(StudentDTO::getName))
                        .collect(Collectors.toList());

            }
            case "startswith":{
                return studentRepository.findAll()
                        .stream()
                        .map(this::entityToDTO)
                        .filter(studentDTO -> studentDTO.getName().toLowerCase().startsWith(loweCaseName))
                        .sorted(Comparator.comparing(StudentDTO::getName))
                        .collect(Collectors.toList());

            }
            case "contains":{
                return studentRepository.findAll()
                        .stream()
                        .map(this::entityToDTO)
                        .filter(studentDTO -> studentDTO.getName().toLowerCase().contains(loweCaseName))
                        .sorted(Comparator.comparing(StudentDTO::getName))
                        .collect(Collectors.toList());

            }
            default:{
                return studentRepository.findAll()
                        .stream()
                        .map(this::entityToDTO)
                        .filter(studentDTO -> studentDTO.getName().toLowerCase().contains(loweCaseName))
                        .sorted(Comparator.comparing(StudentDTO::getName))
                        .collect(Collectors.toList());
            }
        }
    }

    @Override
    public List<StudentDTO> getByMail(String mail, String strategy) {
        String loweCaseMail = mail.toLowerCase();

        switch (strategy){
            case "exactmatch":{
                return studentRepository.findAll()
                        .stream()
                        .map(this::entityToDTO)
                        .filter(studentDTO -> studentDTO.getMail().equalsIgnoreCase(loweCaseMail))
                        .sorted(Comparator.comparing(StudentDTO::getMail))
                        .collect(Collectors.toList());

            }
            case "startswith":{
                return studentRepository.findAll()
                        .stream()
                        .map(this::entityToDTO)
                        .filter(studentDTO -> studentDTO.getMail().toLowerCase().startsWith(loweCaseMail))
                        .sorted(Comparator.comparing(StudentDTO::getMail))
                        .collect(Collectors.toList());

            }
            case "contains":{
                return studentRepository.findAll()
                        .stream()
                        .map(this::entityToDTO)
                        .filter(studentDTO -> studentDTO.getMail().toLowerCase().contains(loweCaseMail))
                        .sorted(Comparator.comparing(StudentDTO::getMail))
                        .collect(Collectors.toList());
            }
            default:{
                return studentRepository.findAll()
                        .stream()
                        .map(this::entityToDTO)
                        .filter(studentDTO -> studentDTO.getMail().toLowerCase().contains(loweCaseMail))
                        .sorted(Comparator.comparing(StudentDTO::getMail))
                        .collect(Collectors.toList());
            }
        }
    }
    @Override
    public List<StudentDTO> getByAge(Integer age) {
        return studentRepository.findAll()
                .stream()
                .map(this::entityToDTO)
                .filter(studentDTO -> studentDTO.getAge()==age)
                .sorted(Comparator.comparing(StudentDTO::getName))
                .collect(Collectors.toList());
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
        if  (studentDTO.getCourse() != null){
            Optional<Course> responseC = courseRepository.findById(studentDTO.getCourse().getCourseId());
            if (responseC.isEmpty()){
                throw new ExceptionsHandler("Course not found", HttpStatus.NOT_FOUND);
            }
        }
        StudentDTO oldStudentDTO = entityToDTO(response.get());
        oldStudentDTO.setName(studentDTO.getName());
        oldStudentDTO.setAge(studentDTO.getAge());
        oldStudentDTO.setIdNum(studentDTO.getIdNum());
        oldStudentDTO.setMail(studentDTO.getMail());
        oldStudentDTO.setCourse(studentDTO.getCourse());

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
