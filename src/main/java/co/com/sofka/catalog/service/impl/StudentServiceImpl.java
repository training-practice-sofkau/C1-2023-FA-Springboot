package co.com.sofka.catalog.service.impl;

import co.com.sofka.catalog.dto.StudentDTO;
import co.com.sofka.catalog.entity.Course;
import co.com.sofka.catalog.entity.Student;
import co.com.sofka.catalog.repository.CourseRepository;
import co.com.sofka.catalog.repository.StudentRepository;
import co.com.sofka.catalog.service.IStudentService;
import co.com.sofka.catalog.utils.CustomMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements IStudentService {

    private final StudentRepository studentRepository;

    private final CourseRepository courseRepository;

    @Override
    public List<StudentDTO> getAllStudents() {
       return studentRepository.findAll().stream().map(CustomMapper::studentDTO).toList();
    }

    @Override
    public StudentDTO getByIdentificationNumber(String idNum) {
        return CustomMapper.studentDTO(studentRepository.findByIdNum(idNum));
    }

    @Override
    public StudentDTO getByName(String s) {
        return CustomMapper.studentDTO(studentRepository.findByName(s));
    }

    @Override
    public StudentDTO saveStudent(StudentDTO studentDTO) {
        if(studentDTO.getCourse() !=null){
            Optional<Course> course = courseRepository.findById(studentDTO.getCourse().getId());
            if (course.isEmpty()) return null;
        }
        return CustomMapper.studentDTO(studentRepository.save(CustomMapper.student(studentDTO)));
    }

    @Override
    public StudentDTO editStudent(StudentDTO studentDTO) {
        Optional <Student> myStudent = studentRepository.findById(studentDTO.getId());
        if(myStudent.isEmpty()) return null;
        myStudent.get().setName(studentDTO.getName());
        myStudent.get().setAge(studentDTO.getAge());
        myStudent.get().setMail(studentDTO.getMail());
        myStudent.get().setIdNum(studentDTO.getIdNum());
        if(studentDTO.getCourse() !=null){
            if(studentDTO.getCourse().getId() == null) return null;
            Optional<Course> c = courseRepository.findById(studentDTO.getCourse().getId());
            if(c.isEmpty()) return null;
            myStudent.get().setCourse(c.get());
        }
        return CustomMapper.studentDTO(studentRepository.save(myStudent.get()));
    }

    @Override
    public String deleteStudent(String idNum) {
        Optional<Student> delStudent = studentRepository.findById(idNum);
        if(delStudent.isEmpty()) return null;
        studentRepository.delete(delStudent.get());
        return "Student deleted";
    }

}
