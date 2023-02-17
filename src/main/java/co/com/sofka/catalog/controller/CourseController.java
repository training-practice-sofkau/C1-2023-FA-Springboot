package co.com.sofka.catalog.controller;

import co.com.sofka.catalog.dto.CourseDTO;
import co.com.sofka.catalog.dto.StudentDTO;
import co.com.sofka.catalog.repository.CourseRepository;
import co.com.sofka.catalog.service.impl.CourseServiceImpl;
import co.com.sofka.catalog.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/sofka_catalog/course")
@CrossOrigin
public class CourseController {

    private Response response = new Response();
    private HttpStatus httpStatus = HttpStatus.OK;

    @Autowired
    private CourseServiceImpl courseService;


    @GetMapping("/")
    private ResponseEntity<Response> getAll(){
        response.restart();
        try {
            response.data = courseService.getAllCourses();
            response.message = "Courses found successfully.";
            httpStatus = HttpStatus.OK;
        }catch (Exception e){
            getErrorMessage(e);
        }
        return new ResponseEntity<>(response, httpStatus);
    }

    @GetMapping("/{id}")
    private ResponseEntity<Response> getById(
            @PathVariable(value = "id") String id
    ){
        response.restart();
        try {
            response.data = courseService.getCoursesById(id);
            response.message = "Course found successfully.";
            httpStatus = HttpStatus.OK;
        }catch (Exception e){
            getErrorMessage(e);
        }
        return new ResponseEntity<>(response, httpStatus);
    }

    @GetMapping("/name/{name}")
    private ResponseEntity<Response> getCourseByIdName(
            @PathVariable(value = "name") String name
    ){
        response.restart();
        try {
            response.data = courseService.getByName(name);
            response.message = "Course " + name + " found successfully.";
            httpStatus = HttpStatus.OK;
        }catch (Exception e){
            getErrorMessage(e);
        }
        return new ResponseEntity<>(response, httpStatus);
    }

    @GetMapping("/coach/{coach}")
    private ResponseEntity<Response> getCourseByIdCoach(
            @PathVariable(value = "coach") String coach
    ){
        response.restart();
        try {
            response.data = courseService.getByCoach(coach);
            response.message = "Courses of coach " + coach + " found successfully.";
            httpStatus = HttpStatus.OK;
        }catch (Exception e){
            getErrorMessage(e);
        }
        return new ResponseEntity<>(response, httpStatus);
    }

    @GetMapping("/level/{level}")
    private ResponseEntity<Response> getCourseByLevel(
            @PathVariable(value = "level") String level
    ){
        response.restart();
        try {
            response.data = courseService.getByLevel(level);
            response.message = "Courses of level " + level + " found successfully.";
            httpStatus = HttpStatus.OK;
        }catch (Exception e){
            getErrorMessage(e);
        }
        return new ResponseEntity<>(response, httpStatus);
    }

    @PostMapping("/")
    private ResponseEntity<Response> saveCourse(
            @RequestBody CourseDTO courseDTO
    ){
        response.restart();
        try {
            response.data = courseService.saveCourse(courseDTO);
            response.message = "Course saved successfully.";
            httpStatus = HttpStatus.CREATED;
        }catch (Exception e){
            getErrorMessage(e);
        }
        return new ResponseEntity<>(response, httpStatus);
    }

    @PutMapping("/edit")
    private ResponseEntity<Response> editCourse(
            @RequestBody CourseDTO courseDTO
    ){
        response.restart();
        try {
            response.data = courseService.editCourse(courseDTO);
            response.message = "Course updated successfully.";
            httpStatus = HttpStatus.OK;
        }catch (Exception e){
            getErrorMessage(e);
        }
        return new ResponseEntity<>(response, httpStatus);
    }

    @DeleteMapping ("/delete")
    private ResponseEntity<Response> deleteStudent(
            @RequestBody CourseDTO courseDTO
    ){
        response.restart();
        try {
            response.data = courseService.deleteCourse(courseDTO);
            response.message = "Course " + response.data + " deleted successfully.";
            httpStatus = HttpStatus.OK;
        }catch (Exception e){
            getErrorMessage(e);
        }
        return new ResponseEntity<>(response, httpStatus);
    }

    private void getErrorMessage(Exception e) {
        response.error = true;
        response.message = e.getMessage();
        response.data = e.getCause();
        httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    }
}
