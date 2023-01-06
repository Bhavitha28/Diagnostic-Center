package com.online.DiagnosticCenter.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
public class SecurityController {
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private LoginAuthService service;

    @GetMapping("/")
    public String welcome() {
        return "Hello World";
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(jwtRequest.getUserName(), jwtRequest.getPassword())
            );
        }catch (Exception ex){
            throw new Exception("invalid username/password");
        }
        String jwtToken=jwtUtils.generateToken(jwtRequest.getUserName());
        return ResponseEntity.ok(new JwtResponse(jwtToken));
    }

}

