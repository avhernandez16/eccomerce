package com.ecommerce.co.model;

import java.util.List;

public record DatosRespuestaProducto(Long id, String urlImg, String name, String descripcion, String string,
                                     String stock, String precio, String codigoEAN, List<Comentario> listaComentarios) {
}
