package co.com.sofka.catalog.controller;

import co.com.sofka.catalog.dto.StudentDTO;
import co.com.sofka.catalog.service.impl.StudentServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/students")
@CrossOrigin(origins = "http://localhost:4200/")
public class StudentController {

    private final StudentServiceImpl studentServiceImpl;

    public StudentController(StudentServiceImpl studentServiceImpl) {
        this.studentServiceImpl = studentServiceImpl;
    }

    @GetMapping
    public ResponseEntity<List<StudentDTO>> getAllStudents(){
        return new ResponseEntity<>(studentServiceImpl.getAllStudents(), HttpStatus.OK);
    }

    @GetMapping("/unsubscribed")
    public ResponseEntity<List<StudentDTO>> getAllUnsubscribedStudents(){
        return new ResponseEntity<>(studentServiceImpl.getAllUnsubscribedStudents(), HttpStatus.OK);
    }

    @GetMapping("/idNumber/{idNumber}")
    public ResponseEntity<StudentDTO> getByIdentificationNumber(@PathVariable("idNumber") String idNumber){
        return new ResponseEntity<>(studentServiceImpl.getByIdentificationNumber(idNumber), HttpStatus.OK);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<StudentDTO> getByName(@PathVariable("name") String name) {
        return new ResponseEntity<>(studentServiceImpl.getByName(name), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<StudentDTO> saveStudent(@RequestBody StudentDTO studentDTO) {
        studentServiceImpl.saveStudent(studentDTO);
        return new ResponseEntity<>(studentDTO, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<StudentDTO> editStudent(@RequestBody StudentDTO studentDTO) {
        studentServiceImpl.editStudent(studentDTO);
        return new ResponseEntity<>(studentDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable("id") String studentId) {
        return new ResponseEntity<>(studentServiceImpl.deleteStudent(studentId), HttpStatus.OK);
    }
}
