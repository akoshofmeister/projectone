package hu.elte.alkfelj.projectone.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/hello")
public class HelloWorldController {
    @GetMapping()
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("Hello World!");
    }
}
