package school.finalproject.mrbbe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import school.finalproject.mrbbe.dto.klass.KlassDTO;
import school.finalproject.mrbbe.service.klass.KlassService;

import java.util.List;

@RestController
@RequestMapping("/klass")
public class KlassController {
    @Autowired
    private KlassService klassService;

    @PostMapping
    public KlassDTO save(@RequestBody KlassDTO klassDTO) {
        return klassService.create(klassDTO);
    }

    @GetMapping
    public List<KlassDTO> getAll() {
        return klassService.getAll();
    }

    @GetMapping("/teacher")
    public List<KlassDTO> getAllOfTeacher(@RequestParam long teacherId) {
        return klassService.getAllOfTeacher(teacherId);
    }

    @GetMapping("/student")
    public List<KlassDTO> getAllOfStudent(@RequestParam long studentId) {
        return klassService.getAllOfStudent(studentId);
    }

    @GetMapping("/{klassId}")
    public KlassDTO get(@PathVariable long klassId) {
        return klassService.get(klassId);
    }

    @PutMapping
    public KlassDTO updateKlass(@RequestBody KlassDTO klassDTO) {
        return klassService.updateKlass(klassDTO);
    }

    @PutMapping("/removeStudents")
    public KlassDTO removeStudentsInKlass(@RequestBody KlassDTO klassDTO) {
        return klassService.removeStudents(klassDTO);
    }

    @PutMapping("/addStudents")
    public KlassDTO addStudentsToKlass(@RequestBody KlassDTO klassDTO) {
        return klassService.addStudents(klassDTO);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable long id) {
        klassService.delete(id);
        return "{}";
    }
}
