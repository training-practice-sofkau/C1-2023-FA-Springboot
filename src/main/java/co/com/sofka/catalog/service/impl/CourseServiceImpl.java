package co.com.sofka.catalog.service.impl;


import co.com.sofka.catalog.dto.CourseDTO;
import co.com.sofka.catalog.entity.Course;
import co.com.sofka.catalog.repository.CourseRepository;
import co.com.sofka.catalog.service.ICourseService;
import co.com.sofka.catalog.utils.CustomMapper;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements ICourseService {
    private final CourseRepository courseRepository;

    @Override
    public List<CourseDTO> getAllCourses() {
        return courseRepository.findAll().stream().map(CustomMapper::courseDTO).toList();
    }

    @Override
    public CourseDTO getByName(String name) {
        return CustomMapper.courseDTO(courseRepository.findByName(name));
    }

    @Override
    public List<CourseDTO> getByCoach(String c) {
        return courseRepository.findAllByCoach(c).stream().map(CustomMapper::courseDTO).toList();
    }

    @Override
    public List<CourseDTO> getByLevel(int level) {
        return courseRepository.findAllByLevel(level).stream().map(CustomMapper::courseDTO).toList();
    }

    @Override
    public CourseDTO editCourse(CourseDTO courseDTO) {
        Optional <Course> myCourse = courseRepository.findById(courseDTO.getId());
        if(myCourse.isEmpty()) return null;
        myCourse.get().setCoach(courseDTO.getCoach());
        myCourse.get().setName(courseDTO.getName());
        myCourse.get().setLevel(courseDTO.getLevel());
        return CustomMapper.courseDTO(courseRepository.save(myCourse.get()));
    }

    @Override
    public String deleteCourse(String courseId) {
        Optional <Course> delCourse = courseRepository.findById(courseId);
        if(delCourse.isEmpty()) return null;
        courseRepository.delete(delCourse.get());
        return "Course deleted";
    }

    @Override
    public CourseDTO saveCourse(CourseDTO courseDTO) {
        return CustomMapper.courseDTO(courseRepository.save(CustomMapper.course(courseDTO)));
    }

}
