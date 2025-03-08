package com.marketplace.apimarketplace.DTO;

import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Getter
@Setter
public class LoteDTO {

    private Long id;

    @NotBlank(message="El titulo no puede ser vacio.")
    private String title;
    @NotBlank(message = "La descripción no puede estar vacio.")
    private String description;
    @NotNull(message = "El precio no puede estar vacio.")
    @Positive(message = "El precio debe ser positivo.")
    private Double price;
    @NotBlank(message = "La ubicacion no puede estar vacia.")
    private String location;
    @NotNull(message = "El Área no puede estar vacio.")
    @Positive(message = "El Área debe ser positivo.")
    private Double area;
    private String image;
    @NotNull(message = "El usuario que crea el lote es obligatorio.")
    private Long usuarioId;

}
