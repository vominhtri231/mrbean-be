package school.finalproject.mrbbe.dto.user;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class UserDTO {
    private long id;

    private String email;

    private RoleDTO role;
}
