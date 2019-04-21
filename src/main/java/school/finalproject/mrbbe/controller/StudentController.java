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

    @GetMapping("")
    public List<StudentDTO> getAllInClass(@RequestParam long klassId) {
        return studentService.getAllInKlass(klassId);
    }

    @PostMapping("")
    public StudentDTO create(@RequestBody StudentDTO studentDTO) {
        return studentService.create(studentDTO);
    }
}
