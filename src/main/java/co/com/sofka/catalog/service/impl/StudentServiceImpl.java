package co.com.sofka.catalog.service.impl;

import co.com.sofka.catalog.dto.StudentDTO;
import co.com.sofka.catalog.entity.Student;
import co.com.sofka.catalog.repository.StudentRepository;
import co.com.sofka.catalog.service.IStudentService;
import co.com.sofka.catalog.utils.CourseMapper;
import co.com.sofka.catalog.utils.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements IStudentService {


    @Autowired
    StudentRepository studentRepository;

    @Override
    public List<StudentDTO> getAllStudents() {
        return studentRepository.findAll()
                .stream()
                .map(student -> {
                    if (student.getCourse() != null){
                        return StudentMapper.toDto(student);
                    }
                    return StudentMapper.toDtoNoCourse(student);
                })
                .collect(Collectors.toList());
    }

    @Override
    public StudentDTO getById(String id) {
        Student student = studentRepository.findById(id).orElse(null);
        assert student != null;
        if(student.getCourse() != null){
            return StudentMapper.toDto(student);
        }
        return StudentMapper.toDtoNoCourse(student);
    }

    @Override
    public StudentDTO getByIdentificationNumber(String idNum) {
        return StudentMapper.toDto(studentRepository.findByIdNum(idNum));
    }

    @Override
    public StudentDTO getByName(String name) {
        return StudentMapper.toDto(studentRepository.findByName(name));
    }

    @Override
    public StudentDTO saveStudent(StudentDTO studentDTO) {
        Student student = StudentMapper.toEntityNoCourse(studentDTO);
        return StudentMapper.toDtoNoCourse(studentRepository.save(student));
    }

    @Override
    public StudentDTO editStudent(StudentDTO studentDTO) {
        Student edited = studentRepository.findById(studentDTO.getId()).orElse(null);
        if(edited != null){
            edited.setName(studentDTO.getName());
            edited.setIdNum(studentDTO.getIdNum());
            edited.setMail(studentDTO.getMail());
            edited.setAge(studentDTO.getAge());
            if(studentDTO.getCourseDTO() != null){
                edited.setCourse(CourseMapper.toEntityNoStudent(studentDTO.getCourseDTO()));
                return StudentMapper.toDto(studentRepository.save(edited));
            }
            return StudentMapper.toDtoNoCourse(studentRepository.save(edited));
        }
        return null;
    }

    @Override
    public void deleteStudent(String id) {
        studentRepository.deleteById(id);
    }
}
