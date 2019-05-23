package school.finalproject.mrbbe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import school.finalproject.mrbbe.dto.mistake.MistakeTypeDTO;
import school.finalproject.mrbbe.service.mistake.MistakeTypeService;

import java.util.List;

@RestController
@RequestMapping("/mistakeType")
public class MistakeTypeController {
    @Autowired
    private MistakeTypeService mistakeTypeService;

    @GetMapping
    public List<MistakeTypeDTO> getAll() {
        return mistakeTypeService.getAllMistakeType();
    }

    @PostMapping
    public MistakeTypeDTO create(@RequestBody MistakeTypeDTO mistakeTypeDTO) {
        return mistakeTypeService.createMistakeType(mistakeTypeDTO);
    }

    @PutMapping
    public MistakeTypeDTO update(@RequestBody MistakeTypeDTO mistakeTypeDTO) {
        return mistakeTypeService.updateMistakeType(mistakeTypeDTO);
    }

    @DeleteMapping("/{id}")
    public String deleteMistake(@PathVariable long id) {
        mistakeTypeService.deleteMistakeType(id);
        return "{}";
    }
}
