package co.com.sofka.catalog.service.impl;

import co.com.sofka.catalog.dto.CourseDTO;
import co.com.sofka.catalog.dto.StudentDTO;
import co.com.sofka.catalog.entity.Course;
import co.com.sofka.catalog.entity.Student;
import co.com.sofka.catalog.repository.CourseRepository;
import co.com.sofka.catalog.repository.StudentRepository;
import co.com.sofka.catalog.service.ICourseService;
import co.com.sofka.catalog.service.IStudentService;
import co.com.sofka.catalog.utils.CustomMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static co.com.sofka.catalog.utils.CustomMapper.*;

@Service
public class StudentServiceImpl implements IStudentService {

    private StudentRepository studentRepository;
    private CourseRepository courseRepository;

    private CourseServiceImpl courseService;


    public StudentServiceImpl(StudentRepository studentRepository,
                             CourseRepository courseRepository,
                              CourseServiceImpl courseService
                              ){
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
        this.courseService = courseService;

    }


    @Override
    public List<StudentDTO> getAllStudents() {

        return studentRepository
                .findAll()
                .stream()
                .map(CustomMapper::studentDTO)
                .collect(Collectors.toList());
    }

    @Override
    public StudentDTO getByIdentificationNumber(String idNum) {
        List<StudentDTO> studentDTOS = new ArrayList<>();
         studentRepository
                .findAll()
                .stream()
                .map(CustomMapper::studentDTO)
                .collect(Collectors.toList())
                 .stream().forEach(i-> {
                    if (i.getIdNumDTO().toLowerCase().equals(idNum.toLowerCase())) studentDTOS.add(i);
                 });
        return studentDTOS.isEmpty()? null:
                studentDTOS.get(0);
    }

    @Override
    public Optional<StudentDTO> findById(String studentId) {
        return studentRepository.findById(studentId).map(CustomMapper::studentDTO);
    }

    @Override
    public List<StudentDTO> getByName(String s) {
        List<StudentDTO> studentDTOS = new ArrayList<>();
        studentRepository
                .findAll()
                .stream()
                .map(CustomMapper::studentDTO)
                .collect(Collectors.toList())
                .stream().forEach(i->{
                    if (i.getNameDTO().toLowerCase().startsWith(s.toLowerCase())
                            | i.getNameDTO().toLowerCase().contains(s.toLowerCase())) studentDTOS.add(i);
                });
        return studentDTOS;
    }

    @Override
    public List<StudentDTO> getByCourseId(String courseId) {
        List<StudentDTO> studentDTOS = new ArrayList<>();
        studentRepository.findAll().stream().map(CustomMapper::studentDTO).collect(Collectors.toList()).stream().forEach(i->{
            if(i.getCourseDTO().getIdDTO().equals(courseId)) studentDTOS.add(i);
        });
        return studentDTOS;
    }

    @Override
    public StudentDTO saveStudent(StudentDTO studentDTO) {
        StudentDTO saveStudentDTo = new StudentDTO();
        System.out.println(studentDTO.getCourseDTO());
        Optional<CourseDTO> courseDTO1 = courseRepository
                .findById(studentDTO.getCourseDTO().getIdDTO())
                .map(CustomMapper::courseDTO);

        if (studentDTO.getCourseDTO() == null || courseDTO1==null) return null;
        if (courseDTO1 != null) {
            studentDTO.setCourseDTO(courseDTO1.get());
            saveStudentDTo = studentDTO(studentRepository.save(student(studentDTO)));
            courseDTO1.get().setStudentListDTO(this.getByCourseId(courseDTO1.get().getIdDTO()));
            //this.courseService.editCourse(courseDTO1.get());

        }
        System.out.println(courseDTO1);
        return saveStudentDTo;
    }

    @Override
    public StudentDTO editStudent(StudentDTO studentDTO) {
        Optional<Student> studentUpdate = studentRepository.findById(studentDTO.getIdDTO());
        Optional<Course> couseUpdate= courseRepository.findById(studentDTO.getCourseDTO().getIdDTO());
        if (studentUpdate.isEmpty()||couseUpdate.isEmpty()) return null;
        else {
            studentUpdate.get().setName(studentDTO.getNameDTO());
            studentUpdate.get().setIdNum(studentDTO.getIdNumDTO());
            studentUpdate.get().setAge(studentDTO.getAgeDTO());
            studentUpdate.get().setMail(studentDTO.getMailDTO());
            studentUpdate.get().setCourse(CustomMapper.course(studentDTO.getCourseDTO()));
        }
        return studentDTO(studentRepository.save(studentUpdate.get()));
    }

    @Override
    public String deleteStudent(String studentId) {
        Optional<StudentDTO> studentDTO = this.findById(studentId);
        if (studentDTO.isEmpty()) return null;
        else {
            studentRepository.deleteById(studentId);
            return "Student with id: " + studentId + " was deleted successfully";
        }
    }

}
