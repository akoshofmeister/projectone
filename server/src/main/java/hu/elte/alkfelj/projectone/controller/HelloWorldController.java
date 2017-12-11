package hu.elte.alkfelj.projectone.controller;

import hu.elte.alkfelj.projectone.entity.User;
import hu.elte.alkfelj.projectone.service.annotations.Role;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/hello")
public class HelloWorldController {
    @Role({User.Role.GUEST})
    @GetMapping()
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("Hello World!");
    }
}
