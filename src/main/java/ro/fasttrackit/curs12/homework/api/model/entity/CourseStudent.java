package ro.fasttrackit.curs12.homework.api.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.Random;

@Data
@Builder
@Document(collection = "course_students")
@NoArgsConstructor
@AllArgsConstructor
public class CourseStudent {
    @Id
    private String id = ObjectId.get().toHexString();

    @NotNull
    private String courseId;

    @NotNull
    private String studentId;

    private Double grade = 0.0;

    public CourseStudent(String courseId, String studentId) {
        this.courseId = courseId;
        this.studentId = studentId;
    }

    public CourseStudent(String courseId, String studentId, Double grade) {
        this.courseId = courseId;
        this.studentId = studentId;
        this.grade = grade;
    }
}
