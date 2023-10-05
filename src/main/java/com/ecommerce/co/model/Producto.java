package com.ecommerce.co.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.LinkedList;
import java.util.List;

@Table(name = "productos")
@Entity(name = "Producto")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "urlimg")
    private String urlImg;
    private String name;
    private String descripcion;
    @Enumerated(EnumType.STRING)
    private Section section;
    private String stock;
    private String precio;
    private String codigoEAN;
    private Boolean activo;
    @JsonIgnore
    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comentario> listaComentarios;


    public Producto(DatosRegistroPructo datosRegistroPructo) {
        this.activo = true;
        this.urlImg = datosRegistroPructo.urlImg();
        this.name = datosRegistroPructo.name();
        this.descripcion = datosRegistroPructo.descripcion();
        this.section = datosRegistroPructo.section();
        this.stock = datosRegistroPructo.stock();
        this.precio = datosRegistroPructo.precio();
        this.codigoEAN = datosRegistroPructo.codigoEAN();
        this.listaComentarios = datosRegistroPructo.listaComentarios();
    }

    public void actualizarProducto(DatosActualizarProducto datosActualizarProducto) {
        if (datosActualizarProducto.urlImg()!=null){
            this.urlImg = datosActualizarProducto.urlImg();
        }
        if (datosActualizarProducto.name()!=null){
            this.name = datosActualizarProducto.name();
        }
        if (datosActualizarProducto.descripcion()!= null){
            this.descripcion = datosActualizarProducto.descripcion();
        }
        if (datosActualizarProducto.stock()!= null){
            this.stock = datosActualizarProducto.stock();
        }
        if (datosActualizarProducto.precio()!= null){
            this.precio = datosActualizarProducto.precio();
        }
        if (datosActualizarProducto.codigoEAN()!= null){
            this.codigoEAN = datosActualizarProducto.codigoEAN();
        }

    }

    public void desactivarProducto() {
        this.activo = false;
    }


}
