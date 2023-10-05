package com.ecommerce.co.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosRegistrarComentario(
        @NotBlank
        String createAt,
        @NotBlank
        String comment,
        @NotNull
        Long productoId
) {

}
