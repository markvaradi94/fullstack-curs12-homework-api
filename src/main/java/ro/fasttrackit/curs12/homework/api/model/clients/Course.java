package ro.fasttrackit.curs12.homework.api.model.clients;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Course {
    private String id;
    private String discipline;
    private String description;
}
