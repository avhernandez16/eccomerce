package com.ecommerce.co.model;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

    List<Producto> findByActivoTrue();
    List<Producto> findByNameContaining(String name);
    List<Producto> findBySection(Section section);

}
