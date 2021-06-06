package ro.fasttrackit.curs12.homework.api.application;

import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import ro.fasttrackit.curs12.homework.api.model.clients.Student;
import ro.fasttrackit.curs12.homework.api.model.filters.StudentFilters;

import java.util.List;

import static java.util.Optional.ofNullable;

@Component
@RequiredArgsConstructor
public class StudentServiceClient {
    private final static String HOSTNAME = "http://localhost:8081/students";
    private final RestTemplate restTemplate;

    public List<Student> findAllStudents(StudentFilters filters) {
        UriComponentsBuilder builder = buildQueryParams(filters);

        return restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                HttpEntity.EMPTY,
                new ParameterizedTypeReference<List<Student>>() {
                }
        ).getBody();
    }

    public Student findStudent(String studentId) {
        return restTemplate.getForObject(
                HOSTNAME + "/" + studentId,
                Student.class
        );
    }

    public Student addStudent(Student student) {
        return restTemplate.postForObject(
                HOSTNAME,
                student,
                Student.class
        );
    }

    public void deleteStudent(String studentId) {
        restTemplate.delete(HOSTNAME + "/" + studentId);
    }

    private UriComponentsBuilder buildQueryParams(StudentFilters filters) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(HOSTNAME);

        ofNullable(filters.getName())
                .ifPresent(name -> builder.queryParam("name", name));
        ofNullable(filters.getAge())
                .ifPresent(age -> builder.queryParam("age", age));
        ofNullable(filters.getMinAge())
                .ifPresent(minAge -> builder.queryParam("minAge", minAge));
        ofNullable(filters.getMaxAge())
                .ifPresent(maxAge -> builder.queryParam("maxAge", maxAge));
        ofNullable(filters.getStudentId())
                .ifPresent(studentId -> builder.queryParam("studentId", studentId));
        return builder;
    }
}
