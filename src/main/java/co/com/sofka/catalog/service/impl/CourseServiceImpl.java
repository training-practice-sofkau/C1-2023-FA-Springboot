package co.com.sofka.catalog.service.impl;


import co.com.sofka.catalog.dto.CourseDTO;
import co.com.sofka.catalog.entity.Course;
import co.com.sofka.catalog.repository.CourseRepository;
import co.com.sofka.catalog.service.ICourseService;
import co.com.sofka.catalog.utils.CourseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements ICourseService {

    @Autowired
    CourseRepository courseRepository;


    @Override
    public List<CourseDTO> getAllCourses() {
        return courseRepository.findAll()
                .stream()
                .map(CourseMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public CourseDTO getByName(String name) {
        return CourseMapper.toDto(courseRepository.findByName(name));
    }

    @Override
    public CourseDTO getById(String id) {
        return CourseMapper.toDto(courseRepository.findById(id).orElse(new Course()));
    }

    @Override
    public List<CourseDTO> getByCoach(String coach) {
        return courseRepository.findAllByCoach(coach)
                .stream()
                .map(CourseMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<CourseDTO> getByLevel(Integer level) {
        return courseRepository.findAllByLevel(level)
                .stream()
                .map(CourseMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public CourseDTO createCourse(CourseDTO courseDTO) {
        Course course = CourseMapper.toEntityNoStudent(courseDTO);
        course.setLastUpdated(LocalDate.now());
        return CourseMapper.toDtoNoStudent(courseRepository.save(course));
    }

    @Override
    public CourseDTO editCourse(CourseDTO courseDTO) {
        Course course = CourseMapper.toEntityNoStudent(courseDTO);
        Course edited = courseRepository.findById(course.getCourseId()).orElse(null);
        if(edited != null){
            edited.setName(course.getName());
            edited.setCoach(course.getCoach());
            edited.setLevel(course.getLevel());
            edited.setLastUpdated(LocalDate.now());
            return CourseMapper.toDto(courseRepository.save(edited));
        }
        return null;
    }

    @Override
    public void deleteCourse(String id) {
        courseRepository.deleteById(id);
    }
}
