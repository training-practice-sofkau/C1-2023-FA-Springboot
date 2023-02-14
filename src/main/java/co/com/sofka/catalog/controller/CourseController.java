package co.com.sofka.catalog.controller;

import co.com.sofka.catalog.dto.CourseDTO;
import co.com.sofka.catalog.service.impl.CourseServiceImpl;
import co.com.sofka.catalog.utils.Response;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@RequestMapping("/api")
public class CourseController {


    private CourseServiceImpl service;
    private Response response = new Response();
    private HttpStatus httpStatus;

    public CourseController(CourseServiceImpl service) {
        this.service = service;
    }

    @GetMapping("/courses")
    public ResponseEntity<Response> getCourses(){
        response.restart();
        try {
            response.data = service.getAllCourses();
            httpStatus = HttpStatus.OK;
        } catch (DataAccessException exception) {
            getErrorMessageForResponse(exception);
        } catch (Exception exception) {
            getErrorMessageInternal(exception);
        }
        return new ResponseEntity<>(response, httpStatus);
    }

    @GetMapping("/courses/name/{name}")
    public ResponseEntity<Response> getCourseByName(@PathVariable String name){
        response.restart();
        try {
            response.data = service.getByName(name);
            httpStatus = HttpStatus.OK;
        } catch (DataAccessException exception) {
            getErrorMessageForResponse(exception);
        } catch (Exception exception) {
            getErrorMessageInternal(exception);
        }
        return new ResponseEntity<>(response, httpStatus);
    }

    @GetMapping("/courses/coach/{coach}")
    public ResponseEntity<Response> getCourseByCoach(@PathVariable String coach){
        response.restart();
        try {
            response.data = service.getByCoach(coach);
            httpStatus = HttpStatus.OK;
        } catch (DataAccessException exception) {
            getErrorMessageForResponse(exception);
        } catch (Exception exception) {
            getErrorMessageInternal(exception);
        }
        return new ResponseEntity<>(response, httpStatus);
    }

    @GetMapping("/courses/level/{level}")
    public ResponseEntity<Response> getCoursesByLevel(@PathVariable String level){
        response.restart();
        try {
            response.data = service.getByLevel(level);
            httpStatus = HttpStatus.OK;
        } catch (DataAccessException exception) {
            getErrorMessageForResponse(exception);
        } catch (Exception exception) {
            getErrorMessageInternal(exception);
        }
        return new ResponseEntity<>(response, httpStatus);
    }
    @PostMapping("/courses")
    public ResponseEntity<Response> createCourse(@RequestBody CourseDTO courseDTO){
        response.restart();
        try {
            response.data = service.createCourse(courseDTO);
            httpStatus = HttpStatus.CREATED;
        } catch (DataAccessException exception) {
            getErrorMessageForResponse(exception);
        } catch (Exception exception) {
            getErrorMessageInternal(exception);
        }
        return new ResponseEntity<>(response, httpStatus);
    }

    @PutMapping("/courses/{courseID}")
    public ResponseEntity<Response> updateCourse(@RequestBody CourseDTO courseDTO, @PathVariable String courseID){
        response.restart();
        try {
            response.data = service.editCourse(courseDTO, courseID);
            httpStatus = HttpStatus.CREATED;
        } catch (DataAccessException exception) {
            getErrorMessageForResponse(exception);
        } catch (Exception exception) {
            getErrorMessageInternal(exception);
        }
        return new ResponseEntity<>(response, httpStatus);
    }

    @DeleteMapping("/courses/{courseID}")
    public ResponseEntity<Response> deleteCourse(@PathVariable String courseID){
        response.restart();
        try {
            response.data = service.deleteCourse(courseID);
            httpStatus = HttpStatus.NO_CONTENT;
        } catch (DataAccessException exception) {
            getErrorMessageForResponse(exception);
        } catch (Exception exception) {
            getErrorMessageInternal(exception);
        }
        return new ResponseEntity<>(response, httpStatus);
    }



    /**
     * DataBase error handler
     * @param exception
     */
    private void getErrorMessageInternal(Exception exception) {
        response.error = true;
        response.message = exception.getMessage();
        response.data = exception.getCause();
        httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    }

    private void getErrorMessageForResponse(DataAccessException exception) {
        response.error = true;
        if(exception.getRootCause() instanceof SQLException) {
            SQLException sqlEx = (SQLException) exception.getRootCause();
            var sqlErrorCode = sqlEx.getErrorCode();
            switch (sqlErrorCode) {
                case 1062:
                    response.message = "El dato ya está registrado";
                    break;
                case 1452:
                    response.message = "El usuario indicado no existe";
                    break;
                default:
                    response.message = exception.getMessage();
                    response.data = exception.getCause();
            }
            httpStatus = HttpStatus.BAD_REQUEST;
        } else {
            response.message = exception.getMessage();
            response.data = exception.getCause();
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }

}
