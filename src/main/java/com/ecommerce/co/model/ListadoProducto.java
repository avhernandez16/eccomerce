package com.ecommerce.co.model;


import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public record ListadoProducto(Long id, String urlImg, String name, String descripcion, String section, String stock,
                              String precio, String codigoEAN, List<ListadoComentario> listaComentarios ) {

    public ListadoProducto(Producto producto){
        this(producto.getId(), producto.getUrlImg(), producto.getName(), producto.getDescripcion(),
                producto.getSection().toString(), producto.getStock(), producto.getPrecio(),
                producto.getCodigoEAN(), transformarComentarios(producto.getListaComentarios()));
    }

    public static List<ListadoComentario> transformarComentarios(List<Comentario> listaComentarios) {
        Collections.reverse(listaComentarios);  // Invierte el orden de la lista
        return listaComentarios.stream()
                .map(comentario -> new ListadoComentario(comentario.getId(), comentario.getCreateAt(), comentario.getComment()))
                .collect(Collectors.toList());
    }
}
