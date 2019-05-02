package school.finalproject.mrbbe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import school.finalproject.mrbbe.dto.user.StudentDTO;
import school.finalproject.mrbbe.service.user.StudentService;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    StudentService studentService;

    @GetMapping
    public List<StudentDTO> getAllInClass(@RequestParam long klassId) {
        return studentService.getAllInKlass(klassId);
    }

    @GetMapping("/all")
    public List<StudentDTO> getAll() {
        return studentService.getAll();
    }

    @GetMapping("/{id}")
    public StudentDTO get(@PathVariable long id) {
        return studentService.get(id);
    }

    @PostMapping
    public StudentDTO create(@RequestBody StudentDTO studentDTO) {
        return studentService.create(studentDTO);
    }

    @PutMapping
    public StudentDTO update(@RequestBody StudentDTO studentDTO) {
        return studentService.update(studentDTO);
    }
}
