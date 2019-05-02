package school.finalproject.mrbbe.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
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

    public AdminDTO update(AdminDTO adminDTO) {
        Admin updatingAdmin = find(adminDTO.getId());

        updatingAdmin.setName(adminDTO.getName());
        updatingAdmin.setEmail(adminDTO.getEmail());

        Admin updatedAdmin = adminRepository.save(updatingAdmin);
        return adminMapper.adminToAdminDto(updatedAdmin);
    }

    public AdminDTO get(long id) {
        Admin foundAdmin = find(id);
        return adminMapper.adminToAdminDto(foundAdmin);
    }

    private Admin find(long id) {
        return adminRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Admin is not found!"));
    }
}
