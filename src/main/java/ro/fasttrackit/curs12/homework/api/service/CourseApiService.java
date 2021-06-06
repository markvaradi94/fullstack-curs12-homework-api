package ro.fasttrackit.curs12.homework.api.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import ro.fasttrackit.curs12.homework.api.application.CourseServiceClient;
import ro.fasttrackit.curs12.homework.api.application.StudentServiceClient;
import ro.fasttrackit.curs12.homework.api.model.api.CourseStudentDetails;
import ro.fasttrackit.curs12.homework.api.model.clients.Course;
import ro.fasttrackit.curs12.homework.api.model.clients.Student;
import ro.fasttrackit.curs12.homework.api.model.entity.CourseStudent;
import ro.fasttrackit.curs12.homework.api.model.filters.CourseStudentFilters;

import java.util.List;

import static java.util.stream.Collectors.toUnmodifiableList;

@Slf4j
@Service
@RequiredArgsConstructor
public class CourseApiService {
    private final CourseServiceClient courseClient;
    private final StudentServiceClient studentClient;
    private final CourseStudentService courseStudentService;

    public List<Course> findAllCourses(CourseStudentFilters filters) {
        return courseStudentService.findAllCourses(filters)
                .stream()
                .map(courseStudent -> courseClient.findCourse(courseStudent.getCourseId()))
                .collect(toUnmodifiableList());
    }

    public List<Student> findCourseStudents(String courseId) {
        return courseStudentService.findStudentsForCourse(courseId).stream()
                .map(courseStudent -> studentClient.findStudent(courseStudent.getStudentId()))
                .collect(toUnmodifiableList());
    }

    public Course findCourse(String courseId) {
        return courseClient.findCourse(courseId);
    }

    public Course addCourse(Course newCourse) {
        return courseClient.addCourse(newCourse);
    }

    public void deleteCourse(String courseId) {
        courseClient.deleteCourse(courseId);
    }

    public CourseStudentDetails addNewStudentToCourse(String courseId, Student student) {
        return courseStudentService.addStudentToCourse(courseId, student);
    }

    public CourseStudentDetails registerStudentToCourse(String courseId, String studentId) {
        return courseStudentService.registerStudentToCourse(courseId, studentId);
    }

    public List<CourseStudentDetails> findDetailedCourses(CourseStudentFilters filters) {
        return courseStudentService.findAllCourses(filters).stream()
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
