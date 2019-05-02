package school.finalproject.mrbbe.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import school.finalproject.mrbbe.dto.user.TeacherDTO;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KlassDTO {
    private long id;
    private String name;
    private String description;
    private TeacherDTO teacher;
    private long teacherId;
    private long[] studentIds;
}
