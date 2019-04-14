package school.finalproject.mrbbe.mapper;

import org.mapstruct.Mapper;
import school.finalproject.mrbbe.dao.user.Role;
import school.finalproject.mrbbe.dto.user.RoleDTO;

@Mapper
public interface RoleMapper {
    RoleDTO roleToRoleDto(Role role);

    Role roleDtoToRole(RoleDTO roleDTO);
}
