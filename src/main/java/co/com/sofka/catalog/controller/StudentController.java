package co.com.sofka.catalog.controller;

import co.com.sofka.catalog.dto.CourseDTO;
import co.com.sofka.catalog.dto.StudentDTO;
import co.com.sofka.catalog.entity.Student;
import co.com.sofka.catalog.repository.StudentRepository;
import co.com.sofka.catalog.service.impl.CourseServiceImpl;
import co.com.sofka.catalog.service.impl.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("api/students")
@CrossOrigin(origins = "http://localhost:4200")
public class StudentController {
    @Autowired
    StudentServiceImpl studentService;
    @Autowired
    private StudentRepository studentRepository;

    @GetMapping
    public ResponseEntity getAllStudents(){
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        List<StudentDTO> students = studentService.getAllStudents();

        if (students.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        else {
            return new ResponseEntity <List> (students, httpHeaders, HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    private ResponseEntity studentByDni(@PathVariable("id") String studentDNI){
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        List<StudentDTO> student = studentService.getByDni(studentDNI);

        if (student.isEmpty()) {
            return ResponseEntity.status(404).build();
        }
        else {
            return new ResponseEntity (student, httpHeaders, HttpStatus.OK);
        }
    }

    @GetMapping("/name/{name}")
    private ResponseEntity getByName(@PathVariable("name") String studentName) {
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        List<StudentDTO> students = studentService.getByName(studentName);

        if (students.isEmpty()) {
            return ResponseEntity.status(204).body(Collections.emptyList());
        }
        else {
            return new ResponseEntity (students, httpHeaders, HttpStatus.OK);
        }
    }

    @PostMapping
    private ResponseEntity<StudentDTO> saveStudent(@RequestBody StudentDTO studentDTO){
        StudentDTO student = studentService.saveStudent(studentDTO);
        return  student == null ?
                ResponseEntity.status(400).body(studentDTO)
                : ResponseEntity.status(201).body(student);
    }

    @PutMapping("/{id}")
    private ResponseEntity<StudentDTO> updateStudent(@PathVariable("id") String studentID,
                                                   @RequestBody StudentDTO studentDTO) {
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        StudentDTO studentUpdated = studentService.editStudent(studentDTO, studentID);

        if (studentUpdated.getId() == null) {
            return ResponseEntity.status(404).build();
        }
        else{
            return new ResponseEntity (studentUpdated, httpHeaders, HttpStatus.OK);
        }
    }

    @DeleteMapping("/{id}")
    private ResponseEntity deleteById(@PathVariable("id") String studentDNI){
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        StudentDTO student = studentService.getById(studentDNI);

        if (student.getId() == null) {
            return ResponseEntity.status(404).body("No student found with the ID provided");
        }
        else {
            String response = studentService.deleteStudent(student.getId());
            return new ResponseEntity (response, httpHeaders, HttpStatus.OK);
        }
    }
}
