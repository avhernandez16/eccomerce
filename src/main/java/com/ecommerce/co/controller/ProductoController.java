package com.ecommerce.co.controller;

import com.ecommerce.co.model.*;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import static com.ecommerce.co.model.ListadoProducto.transformarComentarios;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoRepository productoRepository;
    @Autowired
    private ComentarioRepository comentarioRepository;


    @PostMapping
    public ResponseEntity<DatosRespuestaProducto> registrarProducto(@RequestBody @Valid DatosRegistroPructo datosRegistroPructo,
                                                                    UriComponentsBuilder uriComponentsBuilder){
        System.out.println("El request es exitoso");
        System.out.println(datosRegistroPructo);
        Producto producto = productoRepository.save(new Producto(datosRegistroPructo));
        //necesito los datos desde el dto
        DatosRespuestaProducto datosRespuestaProducto = new DatosRespuestaProducto(producto.getId(), producto.getUrlImg(), producto.getName(),
                producto.getDescripcion(), producto.getSection().toString(), producto.getStock(), producto.getPrecio(),producto.getCodigoEAN(),
                producto.getComments()
        );

        //url del objeto
        URI url = uriComponentsBuilder.path("/producto/{id}").buildAndExpand(producto.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaProducto);

    }


    @GetMapping
    public ResponseEntity<List<ListadoProducto>> listadoProductos(){
        List<ListadoProducto> listadoProductos = productoRepository.findByActivoTrue().stream()
                .map(ListadoProducto::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok(listadoProductos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ListadoProducto> retornaDatosProducto(@PathVariable Long id){
        Producto producto = productoRepository.getReferenceById(id);
        var comentariosTransformados = transformarComentarios(producto.getComments());
        var datosProducto = new ListadoProducto(producto.getId(), producto.getUrlImg(), producto.getName(),
                producto.getDescripcion(), producto.getSection().toString(), producto.getStock(), producto.getPrecio(), producto.getCodigoEAN(), comentariosTransformados);
        return ResponseEntity.ok(datosProducto);
    }


    @PutMapping
    @Transactional
    //cuando termina el metodo hace el commit en la bd y libera la tx
    public ResponseEntity actualizarProducto(@RequestBody @Valid DatosActualizarProducto datosActualizarProducto){

        Producto producto = productoRepository.getReferenceById(datosActualizarProducto.id());
        producto.actualizarProducto(datosActualizarProducto);

        var comentariosTransformados = transformarComentarios(producto.getComments());
        var datosProducto = new ListadoProducto(producto.getId(), producto.getUrlImg(), producto.getName(),
                producto.getDescripcion(), producto.getSection().toString(), producto.getStock(), producto.getPrecio(), producto.getCodigoEAN(), comentariosTransformados);
        return ResponseEntity.ok(datosProducto);
    }


    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DatosRespuestaComentario> actualizarComentario(@RequestBody @Valid DatosActualizarProducto datosActualizarProducto,
                                                                         UriComponentsBuilder uriComponentsBuilder                                               ,
                                               @PathVariable Long id) {
        Producto producto = productoRepository.getReferenceById(id);

        // Crear un nuevo comentario con la información proporcionada y el producto obtenido
        DatosRegistrarComentario datosRegistrarComentario = new DatosRegistrarComentario(producto.getComments().get(0).getCreateAt(),
                producto.getComments().get(0).getComment(), id);
        Comentario comentario = new Comentario(datosRegistrarComentario, producto);
        comentario = comentarioRepository.save(comentario);

        // Crear la respuesta
        DatosRespuestaComentario datosRespuestaComentario = new DatosRespuestaComentario(
                comentario.getId(), comentario.getCreateAt(), comentario.getComment(), comentario.getProducto().getId());

        // Crear la URL del objeto
        URI url = uriComponentsBuilder.path("/comments/{id}").buildAndExpand(comentario.getId()).toUri();

        return ResponseEntity.created(url).body(datosRespuestaComentario);




//        // Verificar si la lista de comentarios no está vacía
//        if (!producto.getComments().isEmpty()) {
//            // Tomar el primer comentario de la lista
////            Comentario comentario = producto.getComments().get(0);
////            comentario.setCreateAt(producto.getComments().get(0).getCreateAt());
////            comentario.setComment(producto.getComments().get(0).getComment());
////            comentario.setProducto(producto);
//
//            Comentario comentario1 = comentarioRepository.save(new Comentario(datosRegistrarComentario, producto));
//
//        }
//
//        var comentariosTransformados = transformarComentarios(producto.getComments());
//        var datosProducto = new ListadoProducto(producto.getId(), producto.getUrlImg(), producto.getName(),
//                producto.getDescripcion(), producto.getSection().toString(), producto.getStock(), producto.getPrecio(), producto.getCodigoEAN(), comentariosTransformados);
//        return ResponseEntity.ok(datosProducto);
    }





    @DeleteMapping("/{id}")
    @Transactional
//    public ResponseEntity eliminarProducto(@PathVariable Long id){
//        Producto producto = productoRepository.getReferenceById(id);
//        producto.desactivarProducto();
//        return ResponseEntity.noContent().build();
//    }

   // delete de la bd
    public void eliminarProducto(@PathVariable Long id){
        Producto producto = productoRepository.getReferenceById(id);
        productoRepository.delete(producto);

    }

    @GetMapping("/buscar")
    public ResponseEntity<List<ListadoProducto>> buscarProductosPorNombre(@RequestParam("name") String name) {
        List<Producto> productosEncontrados = productoRepository.findByNameContaining(name);

        if (productosEncontrados.isEmpty()) {
            throw new EntityNotFoundException("No se encontró ningún producto con el nombre: " + name);
        }

        // Transforma los productos encontrados en ListadoProducto
        List<ListadoProducto> listadoProductos = productosEncontrados.stream()
                .map(ListadoProducto::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok(listadoProductos);
    }

    @GetMapping("/seccion")
    public ResponseEntity<List<ListadoProducto>> buscarProductosPorSeccion(@RequestParam("section") Section section) {
        List<Producto> productosEncontrados = productoRepository.findBySection(section);

        // Transforma los productos encontrados en ListadoProducto
        List<ListadoProducto> listadoProductos = productosEncontrados.stream()
                .map(ListadoProducto::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok(listadoProductos);
    }

}
