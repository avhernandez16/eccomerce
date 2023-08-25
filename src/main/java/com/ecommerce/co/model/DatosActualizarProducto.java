package com.ecommerce.co.model;

import jakarta.validation.constraints.NotNull;

public record DatosActualizarProducto(@NotNull Long id,String nombre, Long precio, String descripcion, Integer cantidad) {
}
