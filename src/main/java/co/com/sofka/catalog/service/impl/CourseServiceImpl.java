package co.com.sofka.catalog.service.impl;


import co.com.sofka.catalog.dto.CourseDTO;
import co.com.sofka.catalog.dto.StudentDTO;
import co.com.sofka.catalog.entity.Course;
import co.com.sofka.catalog.repository.CourseRepository;
import co.com.sofka.catalog.service.ICourseService;
import co.com.sofka.catalog.utils.ExceptionsHandler;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
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
                .sorted(((course1, course2)->course2.getLastUpdated().compareTo(course1.getLastUpdated())))
                .collect(Collectors.toList());
    }

    @Override
    public List<CourseDTO> getByCourseName(String name, String strategy) {
        String loweCaseName = name.toLowerCase();

        switch (strategy){
            case "exactmatch":{
                return courseRepository.findAll()
                        .stream()
                        .map(this::entityToDTO)
                        .filter(courseDTO -> courseDTO.getName().equalsIgnoreCase(loweCaseName))
                        .sorted(Comparator.comparing(CourseDTO::getLastUpdated))
                        .collect(Collectors.toList());

            }
            case "startswith":{
                return courseRepository.findAll()
                        .stream()
                        .map(this::entityToDTO)
                        .filter(courseDTO -> courseDTO.getName().toLowerCase().startsWith(loweCaseName))
                        .sorted(Comparator.comparing(CourseDTO::getLastUpdated))
                        .collect(Collectors.toList());

            }
            case "contains":{
                return courseRepository.findAll()
                        .stream()
                        .map(this::entityToDTO)
                        .filter(courseDTO -> courseDTO.getName().toLowerCase().contains(loweCaseName))
                        .sorted(Comparator.comparing(CourseDTO::getLastUpdated))
                        .collect(Collectors.toList());
            }
            default:{
                return courseRepository.findAll()
                        .stream()
                        .map(this::entityToDTO)
                        .filter(courseDTO -> courseDTO.getName().toLowerCase().contains(loweCaseName))
                        .sorted(Comparator.comparing(CourseDTO::getLastUpdated))
                        .collect(Collectors.toList());
            }
        }
    }

    @Override
    public List<CourseDTO> getByCoach(String coach, String strategy) {
        String loweCaseCoach = coach.toLowerCase();

        switch (strategy){
            case "exactmatch":{
                return courseRepository.findAll()
                        .stream()
                        .map(this::entityToDTO)
                        .filter(courseDTO -> courseDTO.getCoach().equalsIgnoreCase(loweCaseCoach))
                        .sorted(Comparator.comparing(CourseDTO::getLastUpdated))
                        .collect(Collectors.toList());
            }
            case "startswith":{
                return courseRepository.findAll()
                        .stream()
                        .map(this::entityToDTO)
                        .filter(courseDTO -> courseDTO.getCoach().toLowerCase().startsWith(loweCaseCoach))
                        .sorted(Comparator.comparing(CourseDTO::getLastUpdated))
                        .collect(Collectors.toList());
            }
            case "contains":{
                return courseRepository.findAll()
                        .stream()
                        .map(this::entityToDTO)
                        .filter(courseDTO -> courseDTO.getCoach().toLowerCase().contains(loweCaseCoach))
                        .sorted(Comparator.comparing(CourseDTO::getLastUpdated))
                        .collect(Collectors.toList());
            }
            default:{
                return courseRepository.findAll()
                        .stream()
                        .map(this::entityToDTO)
                        .filter(courseDTO -> courseDTO.getCoach().toLowerCase().contains(loweCaseCoach))
                        .sorted(Comparator.comparing(CourseDTO::getLastUpdated))
                        .collect(Collectors.toList());
            }
        }
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
        courseDTO.setLastUpdated(LocalDateTime.now());
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
        oldCourseDTO.setLastUpdated(LocalDateTime.now());
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
