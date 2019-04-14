package school.finalproject.mrbbe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import school.finalproject.mrbbe.dto.user.RoleDTO;
import school.finalproject.mrbbe.service.user.RoleService;

import java.util.List;

@RestController
@RequestMapping("/admin/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @GetMapping("")
    public List<RoleDTO> getAllRoles() {
        return roleService.getAll();
    }
}
