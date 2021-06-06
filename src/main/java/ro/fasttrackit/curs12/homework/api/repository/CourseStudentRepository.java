package ro.fasttrackit.curs12.homework.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ro.fasttrackit.curs12.homework.api.model.entity.CourseStudent;

import java.util.List;

public interface CourseStudentRepository extends MongoRepository<CourseStudent, String> {
    List<CourseStudent> findAllByCourseId(String courseId);

    List<CourseStudent> findAllByStudentId(String studentId);
}
