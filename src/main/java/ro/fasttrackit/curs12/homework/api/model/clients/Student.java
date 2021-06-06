package ro.fasttrackit.curs12.homework.api.model.clients;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    private String id = ObjectId.get().toHexString();
    private String name;
    private Integer age;
}
