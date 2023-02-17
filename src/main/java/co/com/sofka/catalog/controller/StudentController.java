package co.com.sofka.catalog.controller;

import co.com.sofka.catalog.dto.StudentDTO;
import co.com.sofka.catalog.service.impl.StudentServiceImpl;
import co.com.sofka.catalog.utils.Response;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@RequestMapping("/api")
public class StudentController {

    private StudentServiceImpl service;
    private Response response = new Response();
    private HttpStatus httpStatus;

    public StudentController(StudentServiceImpl service) {
        this.service = service;
    }


    @GetMapping("/students")
    public ResponseEntity<Response> getStudents(){
        response.restart();
        try {
            response.data = service.getAllStudents();
            httpStatus = HttpStatus.OK;
        } catch (DataAccessException exception) {
            getErrorMessageForResponse(exception);
        } catch (Exception exception) {
            getErrorMessageInternal(exception);
        }
        return new ResponseEntity<>(response, httpStatus);
    }

    @GetMapping("/students/name/{strategy}/{name}")
    public ResponseEntity<Response> getStudentsByName(@PathVariable String name, @PathVariable String strategy){
        response.restart();
        try {
            response.data = service.getByName(name, strategy);
            httpStatus = HttpStatus.OK;
        } catch (DataAccessException exception) {
            getErrorMessageForResponse(exception);
        } catch (Exception exception) {
            getErrorMessageInternal(exception);
        }
        return new ResponseEntity<>(response, httpStatus);
    }

    @GetMapping("/students/mail/{strategy}/{mail}")
    public ResponseEntity<Response> getStudentsByMail(@PathVariable String mail, @PathVariable String strategy){
        response.restart();
        try {
            response.data = service.getByMail(mail, strategy);
            httpStatus = HttpStatus.OK;
        } catch (DataAccessException exception) {
            getErrorMessageForResponse(exception);
        } catch (Exception exception) {
            getErrorMessageInternal(exception);
        }
        return new ResponseEntity<>(response, httpStatus);
    }
    @GetMapping("/students/idNum/{idNum}")
    public ResponseEntity<Response> getStudentByIDNumber(@PathVariable String idNum){
        response.restart();
        try {
            response.data = service.getByIdentificationNumber(idNum);
            httpStatus = HttpStatus.OK;
        } catch (DataAccessException exception) {
            getErrorMessageForResponse(exception);
        } catch (Exception exception) {
            getErrorMessageInternal(exception);
        }
        return new ResponseEntity<>(response, httpStatus);
    }

    @GetMapping("/students/age/{age}")
    public ResponseEntity<Response> getStudentByAge(@PathVariable Integer age){
        response.restart();
        try {
            response.data = service.getByAge(age);
            httpStatus = HttpStatus.OK;
        } catch (DataAccessException exception) {
            getErrorMessageForResponse(exception);
        } catch (Exception exception) {
            getErrorMessageInternal(exception);
        }
        return new ResponseEntity<>(response, httpStatus);
    }
    @PostMapping("/students")
    public ResponseEntity<Response> createStudent(@RequestBody StudentDTO studentDTO){
        response.restart();
        try {
            response.data = service.saveStudent(studentDTO);
            httpStatus = HttpStatus.CREATED;
        } catch (DataAccessException exception) {
            getErrorMessageForResponse(exception);
        } catch (Exception exception) {
            getErrorMessageInternal(exception);
        }
        return new ResponseEntity<>(response, httpStatus);
    }

    @PutMapping("/students/{studentID}")
    public ResponseEntity<Response> updateStudent(@RequestBody StudentDTO studentDTO, @PathVariable String studentID){
        response.restart();
        try {
            response.data = service.editStudent(studentDTO, studentID);
            httpStatus = HttpStatus.CREATED;
        } catch (DataAccessException exception) {
            getErrorMessageForResponse(exception);
        } catch (Exception exception) {
            getErrorMessageInternal(exception);
        }
        return new ResponseEntity<>(response, httpStatus);
    }

    @DeleteMapping("/students/{studentID}")
    public ResponseEntity<Response> deleteStudent(@PathVariable String studentID){
        response.restart();
        try {
            response.data = service.deleteStudent(studentID);
            httpStatus = HttpStatus.CREATED;
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
