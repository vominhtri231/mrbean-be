package school.finalproject.mrbbe.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import school.finalproject.mrbbe.dao.user.Admin;
import school.finalproject.mrbbe.dto.user.AdminDTO;
import school.finalproject.mrbbe.mapper.AdminMapper;
import school.finalproject.mrbbe.repository.user.AdminRepository;

@Service
public class AdminService extends UserGeneralService<Admin> {
    @Autowired
    AdminRepository adminRepository;

    @Autowired
    AdminMapper adminMapper;

    public AdminDTO create(AdminDTO AdminDTO) {
        Admin savingAdmin = adminMapper.adminDtoToAdmin(AdminDTO);
        Admin preparedAdmin = prepareForCreate(savingAdmin);

        Admin savedAdmin = adminRepository.save(preparedAdmin);
        return adminMapper.adminToAdminDto(savedAdmin);
    }
}
