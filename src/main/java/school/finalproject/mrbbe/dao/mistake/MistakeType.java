package school.finalproject.mrbbe.dao.mistake;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class MistakeType {
    @Id
    @GeneratedValue
    private long id;

    private String name;

    private String description;
}
