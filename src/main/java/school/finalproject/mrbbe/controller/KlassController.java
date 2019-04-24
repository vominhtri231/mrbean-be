package school.finalproject.mrbbe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import school.finalproject.mrbbe.dto.KlassDTO;
import school.finalproject.mrbbe.service.KlassService;

import java.util.List;

@RestController
@RequestMapping("/klass")
public class KlassController {
    @Autowired
    private KlassService klassService;

    @PostMapping("")
    public KlassDTO save(@RequestBody KlassDTO klassDTO) {
        return klassService.create(klassDTO);
    }

    @GetMapping("")
    public List<KlassDTO> getAll() {
        return klassService.getAll();
    }

    @GetMapping("?teacher={teacherId}")
    public List<KlassDTO> getAllOfTeacher(@PathVariable long teacherId) {
        return klassService.getAllOfTeacher(teacherId);
    }

    @GetMapping("?student={studentId}")
    public List<KlassDTO> getAllOfStudent(@PathVariable long studentId) {
        return klassService.getAllOfStudent(studentId);
    }

    @GetMapping("/{klassId}")
    public KlassDTO get(@PathVariable long klassId) {
        return klassService.get(klassId);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable long id) {
        klassService.delete(id);
        return "{}";
    }
}
