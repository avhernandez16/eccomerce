package com.ecommerce.co.model;

public record ListadoComentario(Long id, String createAt, String comment) {

    public ListadoComentario(Comentario comentario){
        this(comentario.getId(), comentario.getCreateAt(), comentario.getComment());
    }
}
