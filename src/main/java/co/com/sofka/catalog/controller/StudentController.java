package co.com.sofka.catalog.controller;

import co.com.sofka.catalog.dto.StudentDTO;
import co.com.sofka.catalog.service.impl.StudentServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/students")
@CrossOrigin
public class StudentController {

    private final StudentServiceImpl studentService;

    @GetMapping
    public ResponseEntity getStudents(){
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @PostMapping
    public ResponseEntity saveStudent(@RequestBody StudentDTO studentDTO){
        StudentDTO studentDTO1 = studentService.saveStudent(studentDTO);
        return studentDTO1 == null ? ResponseEntity.badRequest().body("Wrong params sent for student o The course you are trying to register doesn't exist") : ResponseEntity.ok().body(studentDTO1);
    }

    @DeleteMapping(value = "/delete")
    public ResponseEntity deleteStudent(@RequestParam String studentId){
        String c = studentService.deleteStudent(studentId);
        return c == null ? ResponseEntity.status(400).body("Course is not in our system") : ResponseEntity.ok().body(c);
    }

    @PutMapping
    public ResponseEntity updateCourse(@RequestBody StudentDTO studentDTO){
        StudentDTO studentDTO1 = studentService.editStudent(studentDTO);
        return studentDTO1 == null ? ResponseEntity.badRequest().body("Wrong params sent for student o The course you are trying to register doesn't exist") : ResponseEntity.ok().body(studentDTO1);
    }

    @GetMapping(value = "/nonpunctualn")
    public ResponseEntity getStudentsByName(@RequestParam String name){
        return ResponseEntity.ok(studentService.getByName(name));
    }

    @GetMapping(value = "/punctualn")
    public ResponseEntity getStudentByIdNum(@RequestParam String idNum){
        StudentDTO studentDTO = studentService.getByIdentificationNumber(idNum);
        return studentDTO == null ? ResponseEntity.badRequest().body("There is not a student with this id") : ResponseEntity.ok().body(studentDTO);

    }

}
