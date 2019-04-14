package school.finalproject.mrbbe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import school.finalproject.mrbbe.dto.user.StudentDTO;
import school.finalproject.mrbbe.service.user.StudentService;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    StudentService studentService;

    @GetMapping("/{klass_id}")
    public void getAll(@PathVariable int klassId) {

    }

    @PostMapping("")
    public StudentDTO create(@RequestBody StudentDTO studentDTO) {
        return studentService.create(studentDTO);
    }
}
