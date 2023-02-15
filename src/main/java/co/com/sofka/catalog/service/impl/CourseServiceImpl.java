package co.com.sofka.catalog.service.impl;


import co.com.sofka.catalog.dto.CourseDTO;
import co.com.sofka.catalog.dto.StudentDTO;
import co.com.sofka.catalog.entity.Course;
import co.com.sofka.catalog.repository.CourseRepository;
import co.com.sofka.catalog.repository.StudentRepository;
import co.com.sofka.catalog.service.ICourseService;
import co.com.sofka.catalog.utils.CustomMapper;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements ICourseService {

    private StudentRepository studentRepository;
    private CourseRepository courseRepository;


    public CourseServiceImpl(StudentRepository studentRepository,
                             CourseRepository courseRepository
                             ){
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }


    @Override
    public Course course(CourseDTO courseDTO) {
        return course(courseDTO);
    }

    @Override
    public CourseDTO courseDTO(Course course) {
        return courseDTO(course);
    }

    @Override
    public List<CourseDTO> getAllCourses() {

        return courseRepository
                .findAll()
                .stream()
                .map(this::courseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<CourseDTO> getByName(String name) {
        List<CourseDTO> courseDTO = new ArrayList<>();
         courseRepository
                .findAll()
                .stream()
                .map(this::courseDTO)
                .collect(Collectors.toList())
                .stream().forEach(i->{
                    if (i.getNameDTO().startsWith(name) | i.getNameDTO().contains(name)) courseDTO.add(i);
                });
        return courseDTO;
    }

    @Override
    public List<CourseDTO> getByCoach(String c) {
        List<CourseDTO> courseDTO = new ArrayList<>();
        courseRepository
                .findAll()
                .stream()
                .map(this::courseDTO)
                .collect(Collectors.toList())
                .stream().forEach(i->{
                    if (i.getCoachDTO().startsWith(c) | i.getCoachDTO().contains(c)) courseDTO.add(i);
                });
        return courseDTO;
    }

    @Override
    public List<CourseDTO> getByLevel(Integer level) {
        List<CourseDTO> courseDTO = new ArrayList<>();
        courseRepository
                .findAll()
                .stream()
                .map(this::courseDTO)
                .collect(Collectors.toList())
                .stream().forEach(i->{
                    if (i.getLevelDTO().equals(level)) courseDTO.add(i);
                });
        return courseDTO;
    }

    @Override
    public Optional<CourseDTO> findById(String courseId) {
        return courseRepository.findById(courseId).map(this::courseDTO);
    }

    @Override
    public CourseDTO saveCourse(CourseDTO courseDTO) {
        List<StudentDTO> studentDTOS = courseDTO.getStudentListDTO();
        if (!studentDTOS.isEmpty()){
            List<Integer> studentDTONotFound = new ArrayList<>();
            studentDTOS.stream().forEach(i->{
                Optional<StudentDTO> studentDTO = studentRepository.findById(
                        i.getIdDTO()
                ).map(CustomMapper::studentDTO);
                if (studentDTO.isEmpty()) studentDTONotFound.add(1);
            });
            if (!studentDTONotFound.isEmpty()) return null;
        }
        //I need to do the other service first.
        return courseDTO(courseRepository.save(course(courseDTO)));
    }

    @Override
    public CourseDTO editCourse(CourseDTO courseDTO) {
        Optional<Course> courseUpdate = courseRepository.findById(courseDTO.getIdDTO());
        List<StudentDTO> studentDTOS = courseDTO.getStudentListDTO();
        if (courseUpdate.isEmpty()) return null;
        if (!studentDTOS.isEmpty()){
            List<Integer> studentDTONotFound = new ArrayList<>();
            studentDTOS.stream().forEach(i->{
                Optional<StudentDTO> studentDTO =
                        studentRepository.findById(i.getIdDTO()).map(CustomMapper::studentDTO);
                if (studentDTO.isEmpty()) studentDTONotFound.add(1);
            });
            if (!studentDTONotFound.isEmpty()) return null;
        }
        else {
            courseUpdate.get().setName(courseDTO.getNameDTO());
            courseUpdate.get().setCoach(courseDTO.getCoachDTO());
            courseUpdate.get().setLevel(courseDTO.getLevelDTO());
            courseUpdate.get().setLastUpdated(courseDTO.getLastUpdatedDTO());
            courseUpdate.get().setStudentList(courseDTO.getStudentListDTO().stream()
                    .map(i->CustomMapper.student(i))
                    .collect(Collectors.toList()));
        }
        return courseDTO(courseRepository.save(courseUpdate.get()));
    }

    @Override
    public String deleteCourse(String courseId) {
        Optional<CourseDTO> courseDTO = this.findById(courseId);
        if (courseDTO.isEmpty()) return null;
        else {
            courseRepository.deleteById(courseId);
            return "Artist with id: " + courseId + " was deleted successfully";
        }
    }
}
