package com.marketplace.apimarketplace.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TokenDTO {
    @NotNull(message = "El token no puede ser nulo")
    @NotBlank(message = "El token no puede estar vacio")
    private String token;
    @NotNull(message = "El email no debe ser nulo.")
    @NotBlank(message = "El email no debe estar vacio.")
    private String email;
}
