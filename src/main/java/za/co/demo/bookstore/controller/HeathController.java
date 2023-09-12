package za.co.demo.bookstore.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HeathController {
    @GetMapping("/")
    public String health() {
        return "success";
    }
}
