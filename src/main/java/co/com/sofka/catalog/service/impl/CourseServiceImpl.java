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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static co.com.sofka.catalog.utils.CustomMapper.course;
import static co.com.sofka.catalog.utils.CustomMapper.courseDTO;

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
    public List<CourseDTO> getAllCourses() {

        return courseRepository
                .findAll()
                .stream()
                .map(CustomMapper::courseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<CourseDTO> getByName(String name) {
        List<CourseDTO> courseDTO = new ArrayList<>();
         courseRepository
                .findAll()
                .stream()
                .map(CustomMapper::courseDTO)
                .collect(Collectors.toList())
                .stream().forEach(i->{
                    if (i.getNameDTO().toLowerCase().startsWith(name.toLowerCase())
                            | i.getNameDTO().toLowerCase().contains(name.toLowerCase())) courseDTO.add(i);
                });
        return courseDTO;
    }

    @Override
    public List<CourseDTO> getByCoach(String c) {
        List<CourseDTO> courseDTO = new ArrayList<>();
        courseRepository
                .findAll()
                .stream()
                .map(CustomMapper::courseDTO)
                .collect(Collectors.toList())
                .stream().forEach(i->{
                    if (i.getCoachDTO().toLowerCase().startsWith(c.toLowerCase())
                            | i.getCoachDTO().toLowerCase().contains(c.toLowerCase())) courseDTO.add(i);
                });
        return courseDTO;
    }

    @Override
    public List<CourseDTO> getByLevel(Integer level) {
        List<CourseDTO> courseDTO = new ArrayList<>();
        courseRepository
                .findAll()
                .stream()
                .map(CustomMapper::courseDTO)
                .collect(Collectors.toList())
                .stream().forEach(i->{
                    if (i.getLevelDTO().equals(level)) courseDTO.add(i);
                });
        return courseDTO;
    }

    @Override
    public Optional<CourseDTO> findById(String courseId) {
        return courseRepository.findById(courseId).map(CustomMapper::courseDTO);
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
        courseDTO.setLastUpdatedDTO(LocalDate.now());
        return courseDTO(courseRepository.save(course(courseDTO)));
    }

    @Override
    public CourseDTO editCourse(CourseDTO courseDTO) {
        System.out.println("Curso al principio edit course" + courseDTO);
        Optional<CourseDTO> courseUpdate = courseRepository.findById(courseDTO.getIdDTO()).map(CustomMapper::courseDTO);
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
            courseUpdate.get().setNameDTO(courseDTO.getNameDTO());
            courseUpdate.get().setCoachDTO(courseDTO.getCoachDTO());
            courseUpdate.get().setLevelDTO(courseDTO.getLevelDTO());
            courseUpdate.get().setLastUpdatedDTO(courseDTO.getLastUpdatedDTO());
            courseUpdate.get().setStudentListDTO(courseDTO.getStudentListDTO());
        }
        //System.out.println("Curso final saveStudentMethod " + courseUpdate);
        return courseDTO(courseRepository.save(CustomMapper.course(courseUpdate.get())));
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
