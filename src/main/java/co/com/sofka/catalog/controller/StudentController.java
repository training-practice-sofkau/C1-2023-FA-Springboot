package co.com.sofka.catalog.controller;

import co.com.sofka.catalog.service.impl.StudentServiceImpl;
import co.com.sofka.catalog.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
            response.message = "Students found successfully";
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
