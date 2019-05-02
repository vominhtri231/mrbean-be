package school.finalproject.mrbbe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import school.finalproject.mrbbe.dto.user.TeacherDTO;
import school.finalproject.mrbbe.service.user.TeacherService;

import java.util.List;

@RestController
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    TeacherService teacherService;

    @PostMapping
    public TeacherDTO create(@RequestBody TeacherDTO TeacherDTO) {
        return teacherService.create(TeacherDTO);
    }

    @GetMapping
    public List<TeacherDTO> getAll() {
        return teacherService.getAll();
    }

    @GetMapping("/{id}")
    public TeacherDTO get(@PathVariable long id) {
        return teacherService.get(id);
    }

    @PutMapping
    public TeacherDTO update(@RequestBody TeacherDTO teacherDTO) {
        return teacherService.update(teacherDTO);
    }
}
