package ro.fasttrackit.curs12.homework.api.application;

import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ro.fasttrackit.curs12.homework.api.model.clients.Course;
import ro.fasttrackit.curs12.homework.api.model.clients.Student;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CourseServiceClient {
    private final static String HOSTNAME = "http://localhost:8082/courses";
    private final RestTemplate restTemplate;

    public List<Course> findAllCourses() {
        return restTemplate.exchange(
                HOSTNAME,
                HttpMethod.GET,
                HttpEntity.EMPTY,
                new ParameterizedTypeReference<List<Course>>() {
                }
        ).getBody();
    }

    public Course findCourse(String courseId) {
        return restTemplate.getForObject(
                HOSTNAME + "/" + courseId,
                Course.class
        );
    }

    public Course addCourse(Course course) {
        return restTemplate.postForObject(
                HOSTNAME,
                course,
                Course.class
        );
    }

    public void deleteCourse(String courseId) {
        restTemplate.delete(HOSTNAME + "/" + courseId);
    }
}
