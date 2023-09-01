package com.ecommerce.co.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
public record DatosRegistroPructo(

        @NotBlank(message = "{nombre.obligatorio}")
        String nombre,
        @NotBlank
        String precio,
        @NotBlank
        String descripcion,
        @NotBlank
        String cantidad,
        @NotNull
        Categoria categoria

) {

}
