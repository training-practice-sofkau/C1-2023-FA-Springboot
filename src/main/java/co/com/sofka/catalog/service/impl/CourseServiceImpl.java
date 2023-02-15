package co.com.sofka.catalog.service.impl;


import co.com.sofka.catalog.dto.CourseDTO;
import co.com.sofka.catalog.entity.Course;
import co.com.sofka.catalog.repository.CourseRepository;
import co.com.sofka.catalog.service.ICourseService;
import co.com.sofka.catalog.utils.CustomMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.CustomEditorConfigurer;
import org.springframework.jdbc.support.CustomSQLErrorCodesTranslation;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements ICourseService {
    @Autowired
    CourseRepository courseRepository;

    @Override
    public List<CourseDTO> getAllCourses() {
        return courseRepository.findAll()
                .stream().map(CustomMapper::courseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CourseDTO getById(String courseID) {
        return CustomMapper.courseDTO(courseRepository.findById(courseID).orElse(new Course()));
    }

    @Override
    public List<CourseDTO> getByName(String name) {
        return courseRepository.findAll()
                .stream()
                .filter(course -> course.getName().equals(name))
                .map(CustomMapper::courseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<CourseDTO> getByCoach(String coach) {

        return courseRepository.findAll()
                .stream()
                .filter(course -> course.getCoach().equals(coach))
                .map(CustomMapper::courseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<CourseDTO> getByLevel(String level) {
        return courseRepository.findAll()
                .stream()
                .filter(course -> course.getLevel().equals(Integer.parseInt(level)))
                .map(CustomMapper::courseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CourseDTO saveCourse(CourseDTO courseDTO) {
        return CustomMapper.courseDTO(
                courseRepository.save(CustomMapper.course(courseDTO))
        );
    }

    @Override
    public CourseDTO editCourse(CourseDTO courseDTO, String courseID) {
        CourseDTO course = CustomMapper.courseDTO(courseRepository.findById(courseID).orElse(new Course()));

        if (course.getId() == null) {
            return course;
        }
        else {
            course.setName(courseDTO.getName());
            course.setCoach(courseDTO.getCoach());
            course.setLevel(courseDTO.getLevel());
            course.setLastUpdated(courseDTO.getLastUpdated());
            course.setStudentsDTO(courseDTO.getStudentsDTO());

            courseRepository.save(CustomMapper.course(course));

            return course;
        }
    }

    @Override
    public String deleteCourse(String courseID) {
        try {
            courseRepository.deleteById(courseID);
            return "Deleted";
        }
        catch (Exception e){
            System.out.println(e);
            return "Unsuccessful";
        }
    }
}
