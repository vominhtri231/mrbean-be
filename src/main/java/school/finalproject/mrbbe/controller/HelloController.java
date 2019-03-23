package school.finalproject.mrbbe.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    private int counter = 0;

    @GetMapping("/hello")
    public int getIncreaseNumber() {
        return counter++;
    }
}
