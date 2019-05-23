package school.finalproject.mrbbe.dao.mistake;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MistakeRule {
    @Id
    @GeneratedValue
    private long id;

    private String mistakeStandard;

    private int threshold;
    private int number;

    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mistake_type_id")
    private MistakeType mistakeType;
}
