package com.ecommerce.co.model;

import jakarta.validation.constraints.NotNull;

public record DatosActualizarProducto( Long id,String urlImg, String name, String descripcion,String stock, String precio, String codigoEAN) {
}
