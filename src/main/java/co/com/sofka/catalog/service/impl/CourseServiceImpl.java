package co.com.sofka.catalog.service.impl;


import co.com.sofka.catalog.dto.CourseDTO;
import co.com.sofka.catalog.entity.Course;
import co.com.sofka.catalog.repository.CourseRepository;
import co.com.sofka.catalog.service.ICourseService;
import co.com.sofka.catalog.utils.ExceptionsHandler;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
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
        return courseRepository.findAll()
                .stream()
                .map(this::entityToDTO)
                .filter(courseDTO -> courseDTO.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow();
    }

    @Override
    public List<CourseDTO> getByCoach(String coach) {
        return courseRepository.findAll()
                .stream()
                .map(this::entityToDTO)
                .filter(courseDTO -> courseDTO.getCoach().equalsIgnoreCase(coach))
                .collect(Collectors.toList());
    }

    @Override
    public List<CourseDTO> getByLevel(String level) {
        return courseRepository.findAll()
                .stream()
                .map(this::entityToDTO)
                .filter(courseDTO -> courseDTO.getLevel() == Integer.parseInt(level))
                .collect(Collectors.toList());
    }

    @Override
    public CourseDTO createCourse(CourseDTO courseDTO){
        return entityToDTO(courseRepository.save(dtoToEntity(courseDTO)));
    }

    @Override
    public CourseDTO editCourse(CourseDTO courseDTO, String courseID) {
        Optional<Course> response = courseRepository.findById(courseID);
        if (response.isEmpty()) {
            throw new ExceptionsHandler("Course not found", HttpStatus.NOT_FOUND);
        }
        CourseDTO oldCourseDTO = entityToDTO(response.get());
        oldCourseDTO.setName(courseDTO.getName());
        oldCourseDTO.setCoach(courseDTO.getCoach());
        oldCourseDTO.setLevel(courseDTO.getLevel());
        oldCourseDTO.setLastUpdated(LocalDate.now());
        oldCourseDTO.setStudentList(courseDTO.getStudentList());

        return entityToDTO(courseRepository.save(dtoToEntity(oldCourseDTO)));
    }

    @Override
    public String deleteCourse(String courseID) {
        Optional<Course> response = courseRepository.findById(courseID);
        if (response.isEmpty()) {
            throw new ExceptionsHandler("Course not found", HttpStatus.NOT_FOUND);
        }
        courseRepository.deleteById(courseID);
        return ("The course with ID: "+courseID+ " has been deleted.");
    }
}
