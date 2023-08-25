package com.ecommerce.co.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "productos")
@Entity(name = "Producto")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private Long precio;
    private String descripcion;
    private Integer cantidad;
    private Boolean activo;
    @Enumerated(EnumType.STRING)
    private Categoria categoria;

    public Producto(DatosRegistroPructo datosRegistroPructo) {
        this.activo = true;
        this.nombre= datosRegistroPructo.nombre();
        this.precio = datosRegistroPructo.precio();
        this.descripcion = datosRegistroPructo.descripcion();
        this.cantidad = datosRegistroPructo.cantidad();
        this.categoria = datosRegistroPructo.categoria();
    }

    public void actualizarProducto(DatosActualizarProducto datosActualizarProducto) {
        if (datosActualizarProducto.nombre()!=null){
            this.nombre= datosActualizarProducto.nombre();
        }
        if (datosActualizarProducto.precio()!= null){
            this.precio = datosActualizarProducto.precio();
        }
        if (datosActualizarProducto.descripcion()!= null){
            this.descripcion = datosActualizarProducto.descripcion();
        }
        if (datosActualizarProducto.cantidad()!= null){
            this.cantidad = datosActualizarProducto.cantidad();
        }
    }

    public void desactivarProducto() {
        this.activo = false;
    }
}
