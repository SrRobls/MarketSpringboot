package com.marketplace.apimarketplace.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioDTO {

    private Long id;
    @NotNull(message = "El usuario debe tener un nombre.")
    @NotBlank(message = "El usuario debe tener un nombre.")
    private String name;
    @NotNull(message = "El email no puede estar vacío.")
    @NotBlank(message = "El email no puede estar vacío.")
    @Email(message = "Debe ser un email válido.")
    private String email;
    @NotNull(message = "El usuario debe tener un municipio de residencia.")
    @NotBlank(message = "El usuario debe tener un municipio de residencia.")
    private String municipality;
    @NotNull(message = "El usuario debe tener un número telefónico de contacto.")
    @NotBlank(message = "El usuario debe tener un numero telefonico de contacto.")
    private String contact;
    @NotNull(message = "La contraseña no puede estar vacía.")
    @NotBlank(message = "La contraseña no puede estar vacía.")
    @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres.")
    private String password;
}
