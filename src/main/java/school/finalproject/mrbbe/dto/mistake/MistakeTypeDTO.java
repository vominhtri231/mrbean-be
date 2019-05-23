package school.finalproject.mrbbe.dto.mistake;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MistakeTypeDTO {
    private long id;

    private String name;

    private String description;
}
