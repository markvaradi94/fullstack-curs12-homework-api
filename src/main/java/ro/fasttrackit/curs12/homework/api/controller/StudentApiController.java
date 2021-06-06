package ro.fasttrackit.curs12.homework.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ro.fasttrackit.curs12.homework.api.model.api.CourseStudentDetails;
import ro.fasttrackit.curs12.homework.api.model.clients.Student;
import ro.fasttrackit.curs12.homework.api.model.filters.StudentFilters;
import ro.fasttrackit.curs12.homework.api.service.StudentApiService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/students")
@RequiredArgsConstructor
public class StudentApiController {
    private final StudentApiService apiService;

    @GetMapping()
    public List<Student> findAllStudents(StudentFilters filters) {
        return apiService.findAllStudents(filters);
    }

    @GetMapping("{studentId}")
    public Student findStudent(@PathVariable String studentId) {
        return apiService.findStudent(studentId);
    }

    @GetMapping("{studentId}/courses")
    public List<CourseStudentDetails> findCoursesForStudent(@PathVariable String studentId) {
        return apiService.findCoursesForStudent(studentId);
    }

    @PostMapping()
    public Student addStudent(@Valid @RequestBody Student student) {
        return apiService.addStudent(student);
    }

    @DeleteMapping("{studentId}")
    public void deleteStudent(@PathVariable String studentId) {
        apiService.deleteStudent(studentId);
    }
}
