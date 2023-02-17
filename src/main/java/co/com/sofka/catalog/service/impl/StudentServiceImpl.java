package co.com.sofka.catalog.service.impl;

import co.com.sofka.catalog.dto.StudentDTO;
import co.com.sofka.catalog.entity.Course;
import co.com.sofka.catalog.entity.Student;
import co.com.sofka.catalog.repository.CourseRepository;
import co.com.sofka.catalog.repository.StudentRepository;
import co.com.sofka.catalog.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.com.sofka.catalog.utils.CustomMapper;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements IStudentService {

    @Autowired
    StudentRepository studentRepository;
    @Autowired
    private CourseRepository courseRepository;

    @Override
    public List<StudentDTO> getAllStudents() {
       return studentRepository.findAll().stream().map(CustomMapper::studentDTO).toList();
    }

    @Override
    public StudentDTO getByIdentificationNumber(String idNum) {

        return CustomMapper.studentDTO(studentRepository.findByIdNum(idNum));
    }

    @Override
    public List<StudentDTO> getByName(String s) {

        return studentRepository.findByNameContaining(s).stream().map(CustomMapper::studentDTO).toList();
    }

    @Override
    public StudentDTO saveStudent(StudentDTO studentDTO) {
        if(studentDTO.getCourse() != null){
            Optional<Course> course = courseRepository.findById(studentDTO.getCourse().getId());
            if (course.isEmpty()) return null;
        }
        return CustomMapper.studentDTO(studentRepository.save(CustomMapper.student(studentDTO)));
    }

    @Override
    public StudentDTO editStudent(StudentDTO studentDTO) {
        Optional <Student> student = studentRepository.findById(studentDTO.getId());
        if (student.isEmpty()) return null;
        student.get().setName(studentDTO.getName());
        student.get().setAge(studentDTO.getAge());
        student.get().setMail(studentDTO.getMail());
        student.get().setIdNum(studentDTO.getIdNum());
        if(studentDTO.getCourse() != null){
          if(studentDTO.getCourse().getId() == null) return null;
            Optional<Course> course = courseRepository.findById(studentDTO.getCourse().getId());
            if(course.isEmpty()) return null;
            student.get().setCourse(course.get());
        }
        return CustomMapper.studentDTO(studentRepository.save(CustomMapper.student(studentDTO)));
    }

    @Override
    public String deleteStudent(String idNum) {
        Optional<Student> d = studentRepository.findById(idNum);
        if(d.isEmpty())return null;

        else {
            studentRepository.deleteById(idNum);
            return "Student deleted";
        }
    }

}
