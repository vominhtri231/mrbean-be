package school.finalproject.mrbbe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import school.finalproject.mrbbe.dto.user.AdminDTO;
import school.finalproject.mrbbe.service.user.AdminService;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    AdminService adminService;

    @PostMapping
    public AdminDTO create(@RequestBody AdminDTO adminDTO) {
        return adminService.create(adminDTO);
    }

    @GetMapping("/{id}")
    public AdminDTO get(@PathVariable long id) {
        return adminService.get(id);
    }

    @PutMapping
    public AdminDTO update(@RequestBody AdminDTO adminDTO) {
        return adminService.update(adminDTO);
    }
}
