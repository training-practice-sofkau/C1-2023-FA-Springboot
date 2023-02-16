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

    //private StudentServiceImpl studentService;

    public CourseServiceImpl(CourseRepository courseRepository,
                             StudentRepository studentRepository
                             //StudentServiceImpl studentService

                             ){
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
        //this.studentService = studentService;
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
    public CourseDTO regisStudent(String courseId, String studentId) {
        Optional<CourseDTO> targetCourseDTO = this.findById(courseId);
        System.out.println(targetCourseDTO.get());
        Optional<StudentDTO> beAddedStudentDTO = this.studentRepository.findById(studentId).map(CustomMapper::studentDTO);
        System.out.println(beAddedStudentDTO.get());
        if (targetCourseDTO == null | beAddedStudentDTO == null) return null;
        targetCourseDTO.get().registerStudentDTO(beAddedStudentDTO.get());
        System.out.println(targetCourseDTO.get());

        return courseDTO(courseRepository.save(course(targetCourseDTO.get())));
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
        courseUpdate.get().setNameDTO(courseDTO.getNameDTO());
        courseUpdate.get().setCoachDTO(courseDTO.getCoachDTO());
        courseUpdate.get().setLevelDTO(courseDTO.getLevelDTO());
        courseUpdate.get().setLastUpdatedDTO(LocalDate.now());
        return courseDTO(this.courseRepository.save(CustomMapper.course(courseUpdate.get())));
        /*List<StudentDTO> studentDTOS = courseDTO.getStudentListDTO();
        if (courseUpdate.isEmpty()) return null;
        if (!studentDTOS.isEmpty()){
            List<Integer> studentDTONotFound = new ArrayList<>();
            studentDTOS.stream().forEach(i->{
                Optional<StudentDTO> studentDTO =
                        studentService.findById(i.getIdDTO());
                if (studentDTO.isEmpty()) studentDTONotFound.add(1);
            });
            if (!studentDTONotFound.isEmpty()) return null;
        }
        else {
            courseUpdate.get().setNameDTO(courseDTO.getNameDTO());
            courseUpdate.get().setCoachDTO(courseDTO.getCoachDTO());
            courseUpdate.get().setLevelDTO(courseDTO.getLevelDTO());
            courseUpdate.get().setLastUpdatedDTO(LocalDate.now());
            //courseUpdate.get().setStudentListDTO(courseDTO.getStudentListDTO());
        }
        //System.out.println("Curso final saveStudentMethod " + courseUpdate);
        return courseDTO(this.courseRepository.save(CustomMapper.course(courseUpdate.get())));*/
    }

    @Override
    public String deleteCourse(String courseId) {
        Optional<CourseDTO> courseDTO = this.findById(courseId);
        if (courseDTO.isEmpty()) return null;
        else {
            courseRepository.deleteById(courseId);
            return "Course with id: " + courseId + " was deleted successfully";
        }
    }
}
