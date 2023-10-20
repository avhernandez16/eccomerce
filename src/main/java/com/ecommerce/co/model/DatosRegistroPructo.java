package com.ecommerce.co.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record DatosRegistroPructo(
        Long id,
        @NotBlank(message = "{urlImg.obligatorio}")
        String urlImg,
        @NotBlank(message = "{nombre.obligatorio}")
        String name,
        @NotBlank
        String descripcion,
        @NotNull
        Section section,
        @NotBlank
        String stock,
        @NotBlank
        String precio,
        @NotBlank
        String codigoEAN,

        List<Comentario> comments
) {

}
