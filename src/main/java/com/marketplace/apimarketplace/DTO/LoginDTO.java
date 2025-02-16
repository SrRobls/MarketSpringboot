package com.marketplace.apimarketplace.DTO;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDTO {
    @NotBlank(message = "El email no puede estar vacío.")
    @Email(message = "Debe ser un email válido.")
    private String email;
    @NotBlank(message = "La contraseña no puede estar vacía.")
    private String password;
}
