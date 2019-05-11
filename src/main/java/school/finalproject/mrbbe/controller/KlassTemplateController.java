package school.finalproject.mrbbe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import school.finalproject.mrbbe.dto.klass.KlassTemplateDTO;
import school.finalproject.mrbbe.service.klass.KlassTemplateService;

import java.util.List;

@RestController
@RequestMapping("/klassTemplate")
public class KlassTemplateController {
    @Autowired
    private KlassTemplateService klassTemplateService;

    @PostMapping
    public KlassTemplateDTO create(@RequestBody KlassTemplateDTO klassTemplateDTO) {
        return klassTemplateService.createKlassTemplate(klassTemplateDTO);
    }

    @GetMapping
    public List<KlassTemplateDTO> getAll() {
        return klassTemplateService.getAll();
    }

    @PutMapping
    public KlassTemplateDTO update(@RequestBody KlassTemplateDTO klassTemplateDTO) {
        return klassTemplateService.updateKlassTemplate(klassTemplateDTO);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable long id) {
        klassTemplateService.deleteKlassTemplate(id);
        return "{}";
    }
}
