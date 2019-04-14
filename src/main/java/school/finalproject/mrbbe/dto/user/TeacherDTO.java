package school.finalproject.mrbbe.dto.user;

import lombok.Data;

import java.sql.Date;

@Data
public class TeacherDTO extends UserDTO {
    private String name;
    private String phoneNumber;
    private Date dateOfBirth;
}
