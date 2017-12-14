package hu.elte.alkfelj.projectone.controller;

import hu.elte.alkfelj.projectone.entity.User;
import hu.elte.alkfelj.projectone.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import hu.elte.alkfelj.projectone.service.annotations.Role;
import hu.elte.alkfelj.projectone.service.exceptions.UserNotValidException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static hu.elte.alkfelj.projectone.entity.User.Role.ADMIN;
import static hu.elte.alkfelj.projectone.entity.User.Role.GUEST;
import static hu.elte.alkfelj.projectone.entity.User.Role.USER;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Role({USER, ADMIN})
    @GetMapping()
    public ResponseEntity<User> user() {
        if (userService.isLoggedIn()) {
            return ResponseEntity.ok(userService.getUser());
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> userById(@PathVariable("id") int userId) {
        return ResponseEntity.ok(userService.getUserById(userId));
    }

    @Role({GUEST})
    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody User user) {
        try {
            return ResponseEntity.ok(userService.login(user));
        } catch (UserNotValidException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/logout")
    public ResponseEntity logout(@RequestBody User user) {
        this.userService.setUser(null);
        return ResponseEntity.ok().build();
    }

    @Role({GUEST})
    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        return ResponseEntity.ok(userService.register(user));
    }
}