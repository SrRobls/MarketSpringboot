package com.marketplace.apimarketplace.Service;

import com.marketplace.apimarketplace.DTO.LoginDTO;
import com.marketplace.apimarketplace.DTO.UsuarioDTO;
import com.marketplace.apimarketplace.Exception.InvalidCredentialsException;
import com.marketplace.apimarketplace.Exception.UserAlreadyExistsException;
import com.marketplace.apimarketplace.Model.UsuarioModel;
import com.marketplace.apimarketplace.Repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public Map<String, Object> register(UsuarioDTO request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException("El correo ya está en uso.");
        }

        UsuarioModel user = new UsuarioModel();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setMunicipality(request.getMunicipality());
        user.setContact(request.getContact());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        userRepository.save(user);
        String token = jwtService.generateToken(user.getEmail());

        // Construimos la respuesta con los datos del usuario y el token
        Map<String, Object> response = new HashMap<>();
        response.put("id", user.getId());
        response.put("name", user.getName());
        response.put("email", user.getEmail());
        response.put("token", token);

        return response;
    }

    public Map<String, Object> login(LoginDTO request) {
        UsuarioModel user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new InvalidCredentialsException("Usuario no encontrado."));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException("Credenciales incorrectas");
        }

        String token = jwtService.generateToken(user.getEmail());

        // Construimos la respuesta con los datos del usuario y el token
        Map<String, Object> response = new HashMap<>();
        response.put("id", user.getId());
        response.put("name", user.getName());
        response.put("email", user.getEmail());
        response.put("token", token);

        return response;
    }

    public boolean validateToken(String token, String userEmail) {
        return jwtService.isTokenValid(token, userEmail);
    }
}
