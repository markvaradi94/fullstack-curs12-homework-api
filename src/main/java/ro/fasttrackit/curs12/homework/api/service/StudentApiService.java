package ro.fasttrackit.curs12.homework.api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.fasttrackit.curs12.homework.api.application.CourseServiceClient;
import ro.fasttrackit.curs12.homework.api.application.StudentServiceClient;
import ro.fasttrackit.curs12.homework.api.model.api.CourseStudentDetails;
import ro.fasttrackit.curs12.homework.api.model.clients.Student;
import ro.fasttrackit.curs12.homework.api.model.entity.CourseStudent;
import ro.fasttrackit.curs12.homework.api.model.filters.StudentFilters;

import java.util.List;

import static java.util.stream.Collectors.toUnmodifiableList;

@Service
@RequiredArgsConstructor
public class StudentApiService {
    private final StudentServiceClient studentClient;
    private final CourseServiceClient courseClient;
    private final CourseStudentService courseStudentService;

    public List<Student> findAllStudents(StudentFilters filters) {
        return studentClient.findAllStudents(filters);
    }

    public Student findStudent(String studentId) {
        return studentClient.findStudent(studentId);
    }

    public Student addStudent(Student newStudent) {
        return studentClient.addStudent(newStudent);
    }

    public void deleteStudent(String studentId) {
        studentClient.deleteStudent(studentId);
    }

    public List<CourseStudentDetails> findCoursesForStudent(String studentId) {
        return courseStudentService.findCoursesForStudent(studentId).stream()
                .map(this::buildCourseStudentDetails)
                .collect(toUnmodifiableList());
    }

    private CourseStudentDetails buildCourseStudentDetails(CourseStudent courseStudent) {
        return new CourseStudentDetails(
                studentClient.findStudent(courseStudent.getStudentId()),
                courseClient.findCourse(courseStudent.getCourseId())
        );
    }
}
