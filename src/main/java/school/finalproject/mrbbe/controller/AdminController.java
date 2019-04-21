package school.finalproject.mrbbe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import school.finalproject.mrbbe.dto.user.AdminDTO;
import school.finalproject.mrbbe.service.user.AdminService;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    AdminService adminService;

    @PostMapping("")
    public AdminDTO create(@RequestBody AdminDTO adminDTO) {
        return adminService.create(adminDTO);
    }
}
