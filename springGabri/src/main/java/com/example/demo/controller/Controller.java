package com.example.demo.controller;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entity.User;
import com.example.demo.jwt.JwtUtil;
import com.example.demo.repository.UserRepository;





@RestController
@RequestMapping("/api/s1")
public class Controller {

	@Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {

		System.out.println("Je suis ciicic 6");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return ResponseEntity.ok("Utente registrato con successo!");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> body) {
    	String username = body.get("username");
        String password = body.get("password");

        return userRepository.findByUsername(username)
            .filter(user -> passwordEncoder.matches(password, user.getPassword()))
            .map(user -> {
                String token = JwtUtil.generateToken(user.getUsername(), user.getRole());
                return ResponseEntity.ok(Map.of("token", token));
            })
            .orElseGet(() -> ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(Map.of("error", "Credenziali errate")));
    }
    

    @GetMapping("/init")
    public String init() {
        return "API INIT libera!";
    }
    
    @GetMapping("/secure")
    public String secure() {
        return "Sei autenticato!";
    }
    
    @GetMapping("/secure2")
    public String secure2() {
        return "I'am de User!";
    }
}
