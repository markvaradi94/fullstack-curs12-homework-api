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
import ro.fasttrackit.curs12.homework.api.repository.CourseStudentDao;
import ro.fasttrackit.curs12.homework.api.repository.CourseStudentRepository;

import java.util.List;

import static java.util.Collections.unmodifiableList;

@Slf4j
@Service
@RequiredArgsConstructor
public class CourseStudentService {
    private final CourseStudentDao dao;
    private final CourseStudentRepository repository;
    private final CourseServiceClient courseClient;
    private final StudentServiceClient studentClient;

    public List<CourseStudent> findAllCourses(CourseStudentFilters filters) {
        return dao.findAllCourses(filters);
    }

    protected CourseStudentDetails registerStudentToCourse(String courseId, String studentId) {
        Course course = courseClient.findCourse(courseId);
        Student studentToRegister = studentClient.findStudent(studentId);
        repository.save(new CourseStudent(course.getId(), studentToRegister.getId()));
        return new CourseStudentDetails(studentToRegister, course);
    }

    protected CourseStudentDetails addStudentToCourse(String courseId, Student student) {
        Course course = courseClient.findCourse(courseId);
        Student studentToAdd = studentClient.findStudent(student.getId());
        repository.save(new CourseStudent(course.getId(), studentToAdd.getId()));
        return new CourseStudentDetails(studentToAdd, course);
    }

    protected List<CourseStudent> findStudentsForCourse(String courseId) {
        return unmodifiableList(repository.findAllByCourseId(courseId));
    }

    protected List<CourseStudent> findCoursesForStudent(String studentId) {
        return unmodifiableList(repository.findAllByStudentId(studentId));
    }

    public void deleteCourses(List<CourseStudent> courses) {
        repository.deleteAll(courses);
    }

    @RabbitListener(queues = "#{fanoutQue.name}")
    void studentRemovalListener(String studentId) {
        log.info("Received studentId " + studentId + " for removal");
        removeCoursesWithStudent(studentId);
    }

    private void removeCoursesWithStudent(String studentId) {
        List<CourseStudent> courses = findCoursesForStudent(studentId);
        deleteCourses(courses);
    }
}
