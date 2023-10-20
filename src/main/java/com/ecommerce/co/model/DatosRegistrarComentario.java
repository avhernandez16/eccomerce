package com.ecommerce.co.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosRegistrarComentario(
        Long id,
        @NotBlank
        String createAt,
        @NotBlank
        String comment,

        Long productoId
) {

}
