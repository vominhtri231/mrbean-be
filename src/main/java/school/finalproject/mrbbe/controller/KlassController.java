package school.finalproject.mrbbe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import school.finalproject.mrbbe.dao.Klass;
import school.finalproject.mrbbe.dto.KlassDTO;
import school.finalproject.mrbbe.service.KlassService;

import java.util.List;

@RestController
@RequestMapping("/klass")
public class KlassController {
    @Autowired
    private KlassService klassService;

    @PostMapping("")
    public Klass save(@RequestBody KlassDTO klassDTO){
        return klassService.saveKlass(klassDTO);
    }

    @GetMapping("")
    public List<KlassDTO> getAll(){
        return klassService.getAll();
    }
}
