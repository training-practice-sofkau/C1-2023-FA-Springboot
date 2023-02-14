package co.com.sofka.catalog.service.impl;


import co.com.sofka.catalog.dto.CourseDTO;
import co.com.sofka.catalog.entity.Course;
import co.com.sofka.catalog.exceptions.ToDoExceptions;
import co.com.sofka.catalog.repository.CourseRepository;
import co.com.sofka.catalog.service.ICourseService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements ICourseService {

    private final ModelMapper modelMapper;
    private final CourseRepository courseRepository;

    public CourseServiceImpl(ModelMapper modelMapper, CourseRepository courseRepository) {
        this.modelMapper = modelMapper;
        this.courseRepository = courseRepository;
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
                .toList();
    }

    @Override
    public CourseDTO getByName(String name) {
        Optional<Course> response = courseRepository
                .findAll()
                .stream()
                .filter(x -> x.getName().equalsIgnoreCase(name))
                .findFirst();
        if(response.isEmpty()){
            throw new ToDoExceptions("Course not found", HttpStatus.NOT_FOUND);
        }
        return entityToDTO(response.get());
    }

    @Override
    public List<CourseDTO> getByCoach(String coach) {
        return courseRepository.findAll()
                .stream()
                .map(this::entityToDTO)
                .filter(x -> x.getCoach().toLowerCase().startsWith(coach.toLowerCase()))
                .toList();
    }

    @Override
    public List<CourseDTO> getByLevel(String level) {
        return courseRepository.findAll()
                .stream()
                .map(this::entityToDTO)
                .filter(x -> x.getLevel().equals(Integer.parseInt(level)))
                .toList();
    }

    @Override
    public CourseDTO saveCourse(CourseDTO courseDTO) {
        courseDTO.setLastUpdated(LocalDate.now());
        courseDTO.setStudentListDTO(null);
        courseRepository.save(dtoToEntity(courseDTO));
        return courseDTO;
    }

    @Override
    public CourseDTO editCourse(CourseDTO courseDTO) {
        String courseID = courseDTO.getId();
        Optional<Course> response = courseRepository.findById(courseID);
        if (response.isEmpty()) {
            throw new ToDoExceptions("Course not found", HttpStatus.NOT_FOUND);
        }
        courseRepository.save(dtoToEntity(courseDTO));
        return courseDTO;
    }

    @Override
    public String deleteCourse(String courseId) {
        Optional<Course> response = courseRepository.findById(courseId);
        if (response.isEmpty()) {
            throw new ToDoExceptions("Course not found", HttpStatus.NOT_FOUND);
        }
        courseRepository.deleteById(courseId);
        return "The course was deleted";
    }
}
