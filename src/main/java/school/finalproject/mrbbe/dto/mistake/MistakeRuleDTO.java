package school.finalproject.mrbbe.dto.mistake;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MistakeRuleDTO {
    private int id;
    private String mistakeStandard;
    private MistakeTypeDTO mistakeType;
    private int threshold;
    private int number;
}
