package com.armonia10.backend.repository;

import com.armonia10.backend.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    @Query(value = "{call categoriaBodega(:id_bodega)};", nativeQuery = true)
    List<Categoria> getCategoriasBodegas(@Param("id_bodega") Long id_bodega);
}
