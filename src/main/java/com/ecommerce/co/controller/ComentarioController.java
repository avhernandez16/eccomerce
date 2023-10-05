package com.ecommerce.co.controller;

import com.ecommerce.co.model.*;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/comments")
public class ComentarioController {
    @Autowired
    private ComentarioRepository comentarioRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @PostMapping
    public ResponseEntity<DatosRespuestaComentario> registrarComentario(
            @RequestBody @Valid DatosRegistrarComentario datosRegistrarComentario,
            UriComponentsBuilder uriComponentsBuilder) {

        // Buscar el Producto por su ID
        Producto producto = productoRepository.findById(datosRegistrarComentario.productoId())
                .orElseThrow(() -> new EntityNotFoundException("No se encontró el producto con ID: " + datosRegistrarComentario.productoId()));

        // Crear un nuevo comentario con la información proporcionada y el producto obtenido
        Comentario comentario = new Comentario(datosRegistrarComentario, producto);
        comentario = comentarioRepository.save(comentario);

        // Crear la respuesta
        DatosRespuestaComentario datosRespuestaComentario = new DatosRespuestaComentario(
                comentario.getId(), comentario.getCreateAt(), comentario.getComment(), comentario.getProducto().getId());

        // Crear la URL del objeto
        URI url = uriComponentsBuilder.path("/comments/{id}").buildAndExpand(comentario.getId()).toUri();

        return ResponseEntity.created(url).body(datosRespuestaComentario);
    }




    @GetMapping
    public ResponseEntity<List<ListadoComentario>> listadoComentario(){
        List<ListadoComentario> listadoComentario = comentarioRepository.findAll().stream().map(ListadoComentario::new).toList();

        return ResponseEntity.ok(listadoComentario);
    }


}
