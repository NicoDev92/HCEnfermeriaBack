package com.nicode.gestionenfermeria.web.controller;


import com.nicode.gestionenfermeria.service.UserSecurityService;
import com.nicode.gestionenfermeria.service.dto.LoginDto;
import com.nicode.gestionenfermeria.web.config.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.GrantedAuthority;


import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final UserSecurityService userSecurityService;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, JwtUtils jwtUtils, UserSecurityService userSecurityService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
        this.userSecurityService = userSecurityService;
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody LoginDto loginDto){
        UsernamePasswordAuthenticationToken login = new UsernamePasswordAuthenticationToken(loginDto.getUsername(),
                                                                                            loginDto.getPassword());

        Authentication auth = this.authenticationManager.authenticate(login);

        UserDetails userDetails = this.userSecurityService.loadUserByUsername(loginDto.getUsername());
        String[] roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).toArray(String[]::new);


        String jwt = this.jwtUtils.create(loginDto.getUsername());

        Map<String, Object> response = new HashMap<>();
        response.put("token", jwt);
        response.put("user", auth.getPrincipal());
        response.put("role", roles);


        return ResponseEntity.ok().body(response);
    }
}
