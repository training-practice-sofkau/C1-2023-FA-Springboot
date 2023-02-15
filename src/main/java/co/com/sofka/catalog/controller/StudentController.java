package co.com.sofka.catalog.controller;

import co.com.sofka.catalog.dto.StudentDTO;
import co.com.sofka.catalog.service.impl.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/students")
public class StudentController {


    @Autowired
    StudentServiceImpl studentService;

    @GetMapping()
    public ResponseEntity<List<StudentDTO>> getAll(){
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @GetMapping("/byidnum/{idnum}")
    public ResponseEntity<StudentDTO> getByIdNum(@PathVariable("idnum") String idnum){
        return ResponseEntity.ok(studentService.getByIdentificationNumber(idnum));
    }

    @GetMapping("/byname/{name}")
    public ResponseEntity<StudentDTO> getByName(@PathVariable("name") String name){
        return ResponseEntity.ok(studentService.getByName(name));
    }

    @PostMapping()
    public ResponseEntity<StudentDTO> createStudent(@RequestBody StudentDTO studentDTO){
        StudentDTO result = studentService.saveStudent(studentDTO);
        return result != null ? ResponseEntity.ok(result) : ResponseEntity.noContent().build();
    }

    @PutMapping()
    public ResponseEntity<StudentDTO> editStudent(@RequestBody StudentDTO studentDTO){
        StudentDTO result = studentService.getById(studentDTO.getId());
        if(result.getName() != null){
            return ResponseEntity.ok(studentService.editStudent(studentDTO));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("{id}")
    public void deleteStudent(@PathVariable("id") String id){
        studentService.deleteStudent(id);
    }
}
