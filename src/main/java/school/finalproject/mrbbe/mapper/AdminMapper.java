package school.finalproject.mrbbe.mapper;

import org.mapstruct.Mapper;
import school.finalproject.mrbbe.dao.user.Admin;
import school.finalproject.mrbbe.dto.user.AdminDTO;

@Mapper
public interface AdminMapper {
    Admin adminDtoToAdmin(AdminDTO adminDTO);

    AdminDTO adminToAdminDto(Admin admin);
}
