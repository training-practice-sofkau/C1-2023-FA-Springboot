package co.com.sofka.catalog.service.impl;


import co.com.sofka.catalog.dto.CourseDTO;
import co.com.sofka.catalog.dto.StudentDTO;
import co.com.sofka.catalog.entity.Course;
import co.com.sofka.catalog.repository.CourseRepository;
import co.com.sofka.catalog.repository.StudentRepository;
import co.com.sofka.catalog.service.ICourseService;
import co.com.sofka.catalog.utils.CustomMapper;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static co.com.sofka.catalog.utils.CustomMapper.*;

@Service
public class CourseServiceImpl implements ICourseService {

    private StudentRepository studentRepository;
    private CourseRepository courseRepository;

    //private StudentServiceImpl studentService;

    public CourseServiceImpl(CourseRepository courseRepository,
                             StudentRepository studentRepository
                             //StudentServiceImpl studentService

                             ){
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
        //this.studentService = studentService;
    }

    @Override
    public List<CourseDTO> getAllCourses() {

        return courseRepository
                .findAll()
                .stream()
                .map(CustomMapper::courseDTO)
                .collect(Collectors.toList())
                .stream().sorted(Comparator.comparing(CourseDTO::getLastUpdatedDTO))
                .collect(Collectors.toList());
    }

    @Override
    public List<CourseDTO> getByName(String name) {
        List<CourseDTO> courseDTO = new ArrayList<>();
         courseRepository
                .findAll()
                .stream()
                .map(CustomMapper::courseDTO)
                .collect(Collectors.toList())
                .stream().forEach(i->{
                    if (i.getNameDTO().toLowerCase().startsWith(name.toLowerCase())
                            | i.getNameDTO().toLowerCase().contains(name.toLowerCase())) courseDTO.add(i);
                });
        return courseDTO.stream().sorted(Comparator.comparing(CourseDTO::getLastUpdatedDTO))
                .collect(Collectors.toList());
    }

    @Override
    public List<CourseDTO> getByCoach(String c) {
        List<CourseDTO> courseDTO = new ArrayList<>();
        courseRepository
                .findAll()
                .stream()
                .map(CustomMapper::courseDTO)
                .collect(Collectors.toList())
                .stream().forEach(i->{
                    if (i.getCoachDTO().toLowerCase().startsWith(c.toLowerCase())
                            | i.getCoachDTO().toLowerCase().contains(c.toLowerCase())) courseDTO.add(i);
                });
        return courseDTO.stream().sorted(Comparator.comparing(CourseDTO::getLastUpdatedDTO))
                .collect(Collectors.toList());
    }

    @Override
    public List<CourseDTO> getByLevel(Integer level) {
        List<CourseDTO> courseDTO = new ArrayList<>();
        courseRepository
                .findAll()
                .stream()
                .map(CustomMapper::courseDTO)
                .collect(Collectors.toList())
                .stream().forEach(i->{
                    if (i.getLevelDTO().equals(level)) courseDTO.add(i);
                });
        return courseDTO.stream().sorted(Comparator.comparing(CourseDTO::getLastUpdatedDTO))
                .collect(Collectors.toList());
    }

    // the way changing just the list without associate the student to the curso. It works, but no change a shit in DB
    /*@Override
    public CourseDTO regisStudent(String courseId, String studentId) {
        if(this.findById(courseId) == null || this.studentRepository.findById(studentId) == null) return null;
        CourseDTO targetCourseDTO = this.findById(courseId).get();
        System.out.println(targetCourseDTO);
        StudentDTO beAddedStudentDTO = this.studentRepository.findById(studentId).map(CustomMapper::studentDTO).get();
        System.out.println(beAddedStudentDTO);
        //if (targetCourseDTO == null | beAddedStudentDTO == null) return null;
        List<StudentDTO> toUpdateListStudent = targetCourseDTO.getStudentListDTO();
        if ( beAddedStudentDTO.getCourseDTO() != null ||
                toUpdateListStudent.contains(beAddedStudentDTO)){
            return null;
        }
        else {
            toUpdateListStudent.add(beAddedStudentDTO);
            course(targetCourseDTO).setStudentList(
                    toUpdateListStudent.stream().map(CustomMapper::student).collect(Collectors.toList()));
            //this.saveCourse(targetCourseDTO);
            //student(beAddedStudentDTO).setCourse((course(targetCourseDTO)));
            //studentRepository.save(student(beAddedStudentDTO));
        }

        //if (toUpdateListStudent.contains(beAddedStudentDTO)) return null;
        //else toUpdateListStudent.add(beAddedStudentDTO.get());
        //targetCourseDTO.get().setStudentListDTO(toUpdateListStudent);
        System.out.println(this.findById(courseId).get());

        return this.saveCourse(targetCourseDTO);
    }*/

    // other just associated the person in orden to update the list, but it doesn't work
    @Override
    public CourseDTO regisStudent(String courseId, String studentId) {
        StudentDTO saveStudentDTo = new StudentDTO();
        if(this.findById(courseId) == null || this.studentRepository.findById(studentId) == null) return null;
        CourseDTO targetCourseDTO = this.findById(courseId).get();
        System.out.println(targetCourseDTO);
        StudentDTO beAddedStudentDTO = this.studentRepository.findById(studentId).map(CustomMapper::studentDTO).get();
        System.out.println(beAddedStudentDTO);
        //if (targetCourseDTO == null | beAddedStudentDTO == null) return null;
        List<StudentDTO> toUpdateListStudent = targetCourseDTO.getStudentListDTO();
        if ( beAddedStudentDTO.getCourseDTO() != null ||
                toUpdateListStudent.contains(beAddedStudentDTO)){
            return null;
        }
        /*else {
            //toUpdateListStudent.add(beAddedStudentDTO);
            //targetCourseDTO.setStudentListDTO(toUpdateListStudent);
            //this.saveCourse(targetCourseDTO);
            student(beAddedStudentDTO).setCourse((course(targetCourseDTO)));
            saveStudentDTo = studentDTO(studentRepository.save(student(beAddedStudentDTO)));
            //studentRepository.save(student(beAddedStudentDTO));
            System.out.println("beAdded was change " + saveStudentDTo );
        }*/

        student(beAddedStudentDTO).setCourse((course(targetCourseDTO)));
        saveStudentDTo = studentDTO(studentRepository.save(student(beAddedStudentDTO)));
        System.out.println("beAdded was change " + saveStudentDTo );

        //if (toUpdateListStudent.contains(beAddedStudentDTO)) return null;
        //else toUpdateListStudent.add(beAddedStudentDTO.get());
        //targetCourseDTO.get().setStudentListDTO(toUpdateListStudent);

        //beAddedStudentDTO = studentDTO(studentRepository.save(student(beAddedStudentDTO)));
        //System.out.println(this.findById(courseId).get());
        //System.out.println(beAddedStudentDTO);

        return saveStudentDTo.getCourseDTO();
    }

    @Override
    public Optional<CourseDTO> findById(String courseId) {
        return courseRepository.findById(courseId).map(CustomMapper::courseDTO);
    }

    @Override
    public CourseDTO saveCourse(CourseDTO courseDTO) {
        List<StudentDTO> studentDTOS = courseDTO.getStudentListDTO();
        if (!studentDTOS.isEmpty()){
            List<Integer> studentDTONotFound = new ArrayList<>();
            studentDTOS.stream().forEach(i->{
                Optional<StudentDTO> studentDTO = studentRepository.findById(
                        i.getIdDTO()
                ).map(CustomMapper::studentDTO);
                if (studentDTO.isEmpty()) studentDTONotFound.add(1);
            });
            if (!studentDTONotFound.isEmpty()) return null;
        }
        courseDTO.setLastUpdatedDTO(LocalDate.now());
        return courseDTO(courseRepository.save(course(courseDTO)));
    }

    @Override
    public CourseDTO editCourse(CourseDTO courseDTO) {
        System.out.println("Curso al principio edit course" + courseDTO);
        Optional<CourseDTO> courseUpdate = courseRepository.findById(courseDTO.getIdDTO()).map(CustomMapper::courseDTO);
        courseUpdate.get().setNameDTO(courseDTO.getNameDTO());
        courseUpdate.get().setCoachDTO(courseDTO.getCoachDTO());
        courseUpdate.get().setLevelDTO(courseDTO.getLevelDTO());
        courseUpdate.get().setLastUpdatedDTO(LocalDate.now());
        return courseDTO(this.courseRepository.save(CustomMapper.course(courseUpdate.get())));
        /*List<StudentDTO> studentDTOS = courseDTO.getStudentListDTO();
        if (courseUpdate.isEmpty()) return null;
        if (!studentDTOS.isEmpty()){
            List<Integer> studentDTONotFound = new ArrayList<>();
            studentDTOS.stream().forEach(i->{
                Optional<StudentDTO> studentDTO =
                        studentService.findById(i.getIdDTO());
                if (studentDTO.isEmpty()) studentDTONotFound.add(1);
            });
            if (!studentDTONotFound.isEmpty()) return null;
        }
        else {
            courseUpdate.get().setNameDTO(courseDTO.getNameDTO());
            courseUpdate.get().setCoachDTO(courseDTO.getCoachDTO());
            courseUpdate.get().setLevelDTO(courseDTO.getLevelDTO());
            courseUpdate.get().setLastUpdatedDTO(LocalDate.now());
            //courseUpdate.get().setStudentListDTO(courseDTO.getStudentListDTO());
        }
        //System.out.println("Curso final saveStudentMethod " + courseUpdate);
        return courseDTO(this.courseRepository.save(CustomMapper.course(courseUpdate.get())));*/
    }

    @Override
    public String deleteCourse(String courseId) {
        Optional<CourseDTO> courseDTO = this.findById(courseId);
        if (courseDTO.isEmpty()) return null;
        else {
            courseRepository.deleteById(courseId);
            return "Course with id: " + courseId + " was deleted successfully";
        }
    }
}
