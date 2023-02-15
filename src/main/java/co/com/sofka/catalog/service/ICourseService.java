package co.com.sofka.catalog.service;

import co.com.sofka.catalog.dto.CourseDTO;

import java.util.List;

public interface ICourseService {
    List<CourseDTO> getAll();
    List<CourseDTO> getByName(String name);
    List<CourseDTO> getByCoach(String coach);
    List<CourseDTO> getByLevel(String level);
    CourseDTO save(CourseDTO courseDTO);
    CourseDTO update(CourseDTO courseDTO);
    String delete(CourseDTO courseDTO);

}
