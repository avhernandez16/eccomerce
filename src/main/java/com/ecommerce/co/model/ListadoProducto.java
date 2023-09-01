package com.ecommerce.co.model;

public record ListadoProducto(Long id, String nombre, String precio, String descripcion, String cantidad, String categoria) {

    public ListadoProducto(Producto producto){
        this(producto.getId(), producto.getNombre(), producto.getCategoria().toString(), producto.getPrecio(), producto.getDescripcion(), producto.getCantidad());
    }
}
