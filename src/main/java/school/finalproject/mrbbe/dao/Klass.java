package school.finalproject.mrbbe.dao;

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
public class Klass {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String description;
}
