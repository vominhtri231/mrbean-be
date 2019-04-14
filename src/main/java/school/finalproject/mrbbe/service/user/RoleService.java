package school.finalproject.mrbbe.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import school.finalproject.mrbbe.dto.user.RoleDTO;
import school.finalproject.mrbbe.mapper.RoleMapper;
import school.finalproject.mrbbe.repository.user.RoleRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private RoleMapper roleMapper;

    public List<RoleDTO> getAll() {
        return roleRepository.findAll()
                .stream()
                .map(role -> roleMapper.roleToRoleDto(role))
                .collect(Collectors.toList());
    }
}
