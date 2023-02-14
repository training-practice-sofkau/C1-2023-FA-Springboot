package co.com.sofka.catalog.controller;

import co.com.sofka.catalog.dto.StudentDTO;
import co.com.sofka.catalog.service.impl.StudentServiceImpl;
import co.com.sofka.catalog.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@RestController
@RequestMapping("/api/v1/sofka_catalog/student")
@CrossOrigin
public class StudentController {

    private Response response = new Response();
    private HttpStatus httpStatus = HttpStatus.OK;

    @Autowired
    private StudentServiceImpl studentService;

    @GetMapping("/")
    private ResponseEntity<Response> getAll(){
        response.restart();
        try {
            response.data = studentService.getAllStudents();
            response.message = "Students found successfully.";
            httpStatus = HttpStatus.OK;
        }catch (Exception e){
            getErrorMessage(e);
        }
        return new ResponseEntity<>(response, httpStatus);
    }

    @GetMapping("/id_num/{idNum}")
    private ResponseEntity<Response> getStudentByIdNum(
            @PathVariable(value = "idNum") String idNum
    ){
        response.restart();
        try {
            response.data = studentService.getByIdentificationNumber(idNum);
            response.message = "Student with identification number " + idNum + " found successfully.";
            httpStatus = HttpStatus.OK;
        }catch (Exception e){
            getErrorMessage(e);
        }
        return new ResponseEntity<>(response, httpStatus);
    }

    @GetMapping("/name/{name}")
    private ResponseEntity<Response> getStudentByIdName(
            @PathVariable(value = "name") String name
    ){
        response.restart();
        try {
            response.data = studentService.getByName(name);
            response.message = "Student " + name + " found successfully.";
            httpStatus = HttpStatus.OK;
        }catch (Exception e){
            getErrorMessage(e);
        }
        return new ResponseEntity<>(response, httpStatus);
    }

    @PostMapping("/")
    private ResponseEntity<Response> saveStudent(
            @RequestBody StudentDTO studentDTO
    ){
        response.restart();
        try {
            response.data = studentService.saveStudent(studentDTO);
            response.message = "Student saved successfully.";
            httpStatus = HttpStatus.CREATED;
        }catch (Exception e){
            getErrorMessage(e);
        }
        return new ResponseEntity<>(response, httpStatus);
    }

    @PutMapping("/edit")
    private ResponseEntity<Response> editStudent(
            @RequestBody StudentDTO studentDTO
    ){
        response.restart();
        try {
            response.data = studentService.editStudent(studentDTO);
            response.message = "Student updated successfully.";
            httpStatus = HttpStatus.CREATED;
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
