package school.finalproject.mrbbe.dao.user;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import school.finalproject.mrbbe.dao.Lesson;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "role")
@Data
@NoArgsConstructor
@ToString
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(unique = true)
    private String authority;

    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "role", fetch = FetchType.LAZY)
    private List<User> user;
}
