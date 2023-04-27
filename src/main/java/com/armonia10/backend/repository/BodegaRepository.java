package com.armonia10.backend.repository;


import com.armonia10.backend.entity.Bodega;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BodegaRepository extends JpaRepository<Bodega, Long> {

    @Query(value = "{call listarBodegas()};", nativeQuery = true)
    List<Bodega> getBodegas();

    @Query(value = "{call listarBodegasPremium()};", nativeQuery = true)
    List<Bodega> getBodegasPremium();
}
