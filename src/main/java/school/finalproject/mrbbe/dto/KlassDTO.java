package school.finalproject.mrbbe.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import school.finalproject.mrbbe.dto.user.TeacherDTO;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KlassDTO {
    private int id;
    private String name;
    private String description;
    private TeacherDTO teacher;
    private long teacherId;
    private long[] studentIds;
}
