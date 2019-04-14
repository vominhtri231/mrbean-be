package school.finalproject.mrbbe.mapper;

import org.mapstruct.Mapper;
import school.finalproject.mrbbe.dao.user.User;
import school.finalproject.mrbbe.dto.user.UserDTO;

@Mapper(uses = { RoleMapper.class })
public interface UserMapper {
    UserDTO userToUserDto(User user);

    User userDtoToUser(UserDTO userDTO);
}
