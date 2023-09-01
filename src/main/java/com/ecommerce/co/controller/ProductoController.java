package com.ecommerce.co.controller;

import com.ecommerce.co.model.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoRepository productoRepository;

    @PostMapping
    public ResponseEntity<DatosRespuestaProducto> registrarProducto(@RequestBody @Valid DatosRegistroPructo datosRegistroPructo,
                                                                    UriComponentsBuilder uriComponentsBuilder){
        System.out.println("El request es exitoso");
        System.out.println(datosRegistroPructo);
        Producto producto = productoRepository.save(new Producto(datosRegistroPructo));
        //necesito los datos desde el dto
        DatosRespuestaProducto datosRespuestaProducto = new DatosRespuestaProducto(producto.getId(), producto.getNombre(),
                producto.getPrecio(), producto.getDescripcion(), producto.getCantidad(), producto.getCategoria().toString()
                );

        //url del objeto
        URI url = uriComponentsBuilder.path("/producto/{id}").buildAndExpand(producto.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaProducto);

    }

    @GetMapping
    public ResponseEntity<List<ListadoProducto>> listadoProductos(){
        List<ListadoProducto> listaMedicos = productoRepository.findByActivoTrue().stream()
                .map(ListadoProducto::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok(listaMedicos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaProducto> retornaDatosProducto(@PathVariable Long id){
        Producto producto = productoRepository.getReferenceById(id);
        var datosProducto = new DatosRespuestaProducto(producto.getId(), producto.getNombre(),
                producto.getPrecio(), producto.getDescripcion(), producto.getCantidad(), producto.getCategoria().toString());
        return ResponseEntity.ok(datosProducto);
    }

    @PutMapping
    //cuando termina el metodo hace el commit en la bd y libera la tx
    public ResponseEntity actualizarProducto(@RequestBody @Valid DatosActualizarProducto datosActualizarProducto){

        Producto producto = productoRepository.getReferenceById(datosActualizarProducto.id());
        producto.actualizarProducto(datosActualizarProducto);

        return ResponseEntity.ok(new DatosRespuestaProducto(producto.getId(), producto.getNombre(),
                producto.getPrecio(), producto.getDescripcion(), producto.getCantidad(), producto.getCategoria().toString()));
    }

    @DeleteMapping("/{id}")

    public ResponseEntity eliminarProducto(@PathVariable Long id){
        Producto producto = productoRepository.getReferenceById(id);
        producto.desactivarProducto();
        return ResponseEntity.noContent().build();
    }

    //delete de la bd
//    public void eliminarProducto(@PathVariable Long id){
//        Producto producto = productoRepository.getReferenceById(id);
//        productoRepository.delete(producto);
//
//    }

}
