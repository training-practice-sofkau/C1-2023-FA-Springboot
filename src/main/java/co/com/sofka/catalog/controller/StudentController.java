package co.com.sofka.catalog.controller;

import co.com.sofka.catalog.dto.StudentDTO;
import co.com.sofka.catalog.service.impl.StudentServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
@CrossOrigin(origins = "http://localhost:4200")
public class StudentController {
    StudentServiceImpl studentService;

    public StudentController(StudentServiceImpl studentService){
        this.studentService = studentService;
    }

    @GetMapping("")
    private ResponseEntity<List<StudentDTO>> obtenerEstudiantes(){
        var result = studentService.getAllStudents();
        return result.isEmpty() ?
                ResponseEntity.status(204).body(result) :
                ResponseEntity.ok(result);
    }

    @GetMapping("/byIdNumber/{idNum}")
    private ResponseEntity<StudentDTO> obtenerPorNumeroIden(@PathVariable("idNum") String idNum){
        var result = studentService.getByIdentificationNumber(idNum);
        return result == null ?
                ResponseEntity.status(404).build() :
                ResponseEntity.ok(result);
    }

    @GetMapping("/byName/{name}")
    private ResponseEntity<List<StudentDTO>> obtenerPorNombre
            (@PathVariable("name") String name){
        var result = studentService.getByName(name);
        return result.isEmpty() ?
                ResponseEntity.status(404).build() :
                ResponseEntity.ok(result);
    }

    @GetMapping("/byCourseId/{courseId}")
    private ResponseEntity<List<StudentDTO>> obtenerPorCursoId
            (@PathVariable("courseId") String cursoId){
        var result = studentService.getByCourseId(cursoId);
        return result.isEmpty() ?
                ResponseEntity.status(404).build() :
                ResponseEntity.ok(result);
    }

    @PostMapping("")
    private ResponseEntity<StudentDTO> crearEstudiante(@RequestBody StudentDTO studentDTO){
        StudentDTO studentDTO1 = studentService.saveStudent(studentDTO);
        return studentDTO1 == null?
                ResponseEntity.status(400).body(null) :
                ResponseEntity.status(201).body(studentDTO1);
    }

    @PutMapping("")
    private ResponseEntity<StudentDTO> actualizarEstudiante(@RequestBody StudentDTO studentDTO){
        StudentDTO studentDTO1 = studentService.editStudent(studentDTO);
        return studentDTO1 == null?
                ResponseEntity.status(400).body(null) :
                ResponseEntity.status(201).body(studentDTO1);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<String> borrarEstudiante (@PathVariable("id") String studentId){
        String s = studentService.deleteStudent(studentId);
        return s == null ?
                new ResponseEntity<>("There is not course with id: " + studentId, HttpStatus.CONFLICT):
                new ResponseEntity<>(s, HttpStatus.ACCEPTED);
    }

}
