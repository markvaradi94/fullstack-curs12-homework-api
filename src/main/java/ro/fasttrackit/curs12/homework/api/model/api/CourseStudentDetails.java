package ro.fasttrackit.curs12.homework.api.model.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.fasttrackit.curs12.homework.api.model.clients.Course;
import ro.fasttrackit.curs12.homework.api.model.clients.Student;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseStudentDetails {
    private Student student;
    private Course course;
    private Double grade;

    public CourseStudentDetails(Student student, Course course) {
        this.student = student;
        this.course = course;
    }
}
