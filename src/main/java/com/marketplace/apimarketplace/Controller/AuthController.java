package com.marketplace.apimarketplace.Controller;

import com.marketplace.apimarketplace.DTO.LoginDTO;
import com.marketplace.apimarketplace.DTO.TokenDTO;
import com.marketplace.apimarketplace.DTO.UsuarioDTO;
import com.marketplace.apimarketplace.Service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;


    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> register(@Valid @RequestBody UsuarioDTO user){
        return ResponseEntity.ok(authService.register(user));
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@Valid @RequestBody LoginDTO user){
        return ResponseEntity.ok(authService.login(user));
    }

    @PostMapping("/verificar_token")
    public boolean verificarToken(@RequestBody TokenDTO request){
        return authService.validateToken(request.getToken(), request.getEmail());

    }



}
