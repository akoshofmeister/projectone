package hu.elte.alkfelj.projectone.controller;

import hu.elte.alkfelj.projectone.entity.ApplicationUser;
import hu.elte.alkfelj.projectone.repository.ApplicationUserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class UserController {

    private ApplicationUserRepository applicationUserRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserController(ApplicationUserRepository applicationUserRepository,
                          BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.applicationUserRepository = applicationUserRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @PostMapping("/register")
    public ResponseEntity<ApplicationUser> register(@RequestBody ApplicationUser user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        if(applicationUserRepository.findByUsername(user.getUsername()) == null) {
            applicationUserRepository.save(user);
            return ResponseEntity.ok(applicationUserRepository.findByUsername(user.getUsername()));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}