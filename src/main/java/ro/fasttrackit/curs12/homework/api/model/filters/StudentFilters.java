package ro.fasttrackit.curs12.homework.api.model.filters;

import lombok.Value;

@Value
public class StudentFilters {
    Integer age;
    Integer minAge;
    Integer maxAge;
    String name;
    String studentId;
}
