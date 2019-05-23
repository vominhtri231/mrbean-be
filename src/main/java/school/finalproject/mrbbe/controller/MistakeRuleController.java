package school.finalproject.mrbbe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import school.finalproject.mrbbe.dto.mistake.MistakeRuleDTO;
import school.finalproject.mrbbe.service.mistake.MistakeRuleService;

import java.util.List;

@RestController
@RequestMapping("/mistakeRule")
public class MistakeRuleController {
    @Autowired
    private MistakeRuleService mistakeRuleService;

    @GetMapping
    public List<MistakeRuleDTO> getAll() {
        return mistakeRuleService.getAll();
    }

    @PostMapping
    public MistakeRuleDTO create(@RequestBody MistakeRuleDTO mistakeRuleDTO) {
        return mistakeRuleService.create(mistakeRuleDTO);
    }

    @PutMapping
    public MistakeRuleDTO update(@RequestBody MistakeRuleDTO mistakeRuleDTO) {
        return mistakeRuleService.update(mistakeRuleDTO);
    }

    @DeleteMapping("{id}")
    public String update(@PathVariable long id) {
        mistakeRuleService.delete(id);
        return "{}";
    }
}
