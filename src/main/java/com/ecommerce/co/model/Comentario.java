package com.ecommerce.co.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "comments")
@Entity(name = "Comentario")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Comentario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
}
