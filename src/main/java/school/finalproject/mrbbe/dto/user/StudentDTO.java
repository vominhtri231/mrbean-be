package school.finalproject.mrbbe.dto.user;

import lombok.Data;
import lombok.NoArgsConstructor;
import school.finalproject.mrbbe.dto.user.UserDTO;

import java.sql.Date;

@Data
@NoArgsConstructor
public class StudentDTO extends UserDTO {
    private String name;
    private String phoneNumber;
    private Date dateOfBirth;
    private String workspace;
    private boolean isWorker;
}
