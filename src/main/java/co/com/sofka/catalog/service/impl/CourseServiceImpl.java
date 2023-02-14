package co.com.sofka.catalog.service.impl;


import co.com.sofka.catalog.dto.CourseDTO;
import co.com.sofka.catalog.entity.Course;
import co.com.sofka.catalog.repository.CourseRepository;
import co.com.sofka.catalog.service.ICourseService;
import co.com.sofka.catalog.utils.ExceptionsHandler;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CourseServiceImpl implements ICourseService {

    private CourseRepository courseRepository;
    private ModelMapper modelMapper;

    public CourseServiceImpl(CourseRepository courseRepository, ModelMapper modelMapper) {
        this.courseRepository = courseRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Course dtoToEntity(CourseDTO courseDTO) {
        return modelMapper.map(courseDTO, Course.class);
    }

    @Override
    public CourseDTO entityToDTO(Course course) {
        return modelMapper.map(course, CourseDTO.class);
    }
    @Override
    public List<CourseDTO> getAllCourses() {
        return courseRepository.findAll()
                .stream()
                .map(this::entityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CourseDTO getByName(String name) {
        return null;
    }

    @Override
    public List<CourseDTO> getByCoach(String c) {
        return null;
    }

    @Override
    public List<CourseDTO> getByLevel(String level) {
        return null;
    }

    @Override
    public CourseDTO createCourse(CourseDTO courseDTO){
        return entityToDTO(courseRepository.save(dtoToEntity(courseDTO)));
    }

    @Override
    public CourseDTO editCourse(CourseDTO courseDTO) {
        return null;
    }

    @Override
    public String deleteCourse(String courseID) {
        Optional<Course> response = courseRepository.findById(courseID);
        if (response.isEmpty()) {
            throw new ExceptionsHandler("Curse not found", HttpStatus.NOT_FOUND);
        }
        courseRepository.deleteById(courseID);
        return ("The course with ID: "+courseID+ " has been deleted.");
    }
}
