package ro.fasttrackit.curs12.homework.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ro.fasttrackit.curs12.homework.api.model.api.CourseStudentDetails;
import ro.fasttrackit.curs12.homework.api.model.clients.Course;
import ro.fasttrackit.curs12.homework.api.model.clients.Student;
import ro.fasttrackit.curs12.homework.api.model.filters.CourseStudentFilters;
import ro.fasttrackit.curs12.homework.api.service.CourseApiService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/courses")
@RequiredArgsConstructor
public class CourseApiController {
    private final CourseApiService apiService;

    @GetMapping()
    public List<Course> findAllCourses(CourseStudentFilters filters) {
        return apiService.findAllCourses(filters);
    }

    @GetMapping("details")
    public List<CourseStudentDetails> findDetailedCourses(CourseStudentFilters filters) {
        return apiService.findDetailedCourses(filters);
    }

    @GetMapping("{courseId}")
    public Course findCourse(@PathVariable String courseId) {
        return apiService.findCourse(courseId);
    }

    @GetMapping("{courseId}/students")
    public List<Student> findCourseStudents(@PathVariable String courseId) {
        return apiService.findCourseStudents(courseId);
    }

    @PostMapping("{courseId}/students/{studentId}")
    public CourseStudentDetails registerStudentToCourse(@PathVariable String courseId, @PathVariable String studentId) {
        return apiService.registerStudentToCourse(courseId, studentId);
    }

    @PostMapping("{courseId}")
    public CourseStudentDetails addNewStudentToCourse(@PathVariable String courseId, @Valid @RequestBody Student student) {
        return apiService.addNewStudentToCourse(courseId, student);
    }

    @DeleteMapping("{courseId}")
    public void deleteCourse(@PathVariable String courseId) {
        apiService.deleteCourse(courseId);
    }
}
