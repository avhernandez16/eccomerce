package com.ecommerce.co.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Table(name = "comments")
@Entity(name = "Comentario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Comentario {
    @Id
    private Long id;
    @Column(name = "createat")
    private String createAt;
    private String comment;
    @ManyToOne
    @JoinColumn(name = "producto_id")
    private Producto producto;

    public Comentario(DatosRegistrarComentario datosRegistrarComentario, Producto producto) {
        this.createAt = datosRegistrarComentario.createAt();
        this.comment = datosRegistrarComentario.comment();
        this.producto = producto;
    }
    public Comentario(ComentarioCreationDTO comentarioDTO, Producto producto) {
        this.createAt = comentarioDTO.createAt();
        this.comment = comentarioDTO.comment();
        this.producto = producto;
    }

}
