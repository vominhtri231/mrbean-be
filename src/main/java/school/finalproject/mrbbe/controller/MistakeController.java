package school.finalproject.mrbbe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import school.finalproject.mrbbe.dto.mistake.MistakeDTO;
import school.finalproject.mrbbe.service.mistake.MistakeService;

import java.util.List;

@RestController
@RequestMapping("/mistake")
public class MistakeController {
    @Autowired
    private MistakeService mistakeService;

    @GetMapping
    public List<MistakeDTO> getAllOfKlass(@RequestParam long klassId) {
        return mistakeService.getAllOfKlass(klassId);
    }

    @GetMapping("/student")
    public List<MistakeDTO> getAllOfKlassAndStudent(@RequestParam long klassId,@RequestParam long studentId) {
        return mistakeService.getAllOfKlassAndStudent(klassId,studentId);
    }

    @PostMapping
    public MistakeDTO create(@RequestBody MistakeDTO mistakeDTO) {
        return mistakeService.create(mistakeDTO);
    }

    @PutMapping
    public MistakeDTO update(@RequestBody MistakeDTO mistakeDTO) {
        return mistakeService.update(mistakeDTO);
    }

    @DeleteMapping("/{id}")
    public String deleteMistake(@PathVariable long id) {
        mistakeService.delete(id);
        return "{}";
    }
}
