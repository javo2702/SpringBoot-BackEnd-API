package com.armonia10.backend.repository;

import com.armonia10.backend.entity.Bodega;
import com.armonia10.backend.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    @Query(value = "{call buscarCliente(:correo_b,:passw)};", nativeQuery = true)
    Cliente buscarCliente(@Param("correo_b") String correo_b, @Param("passw") String passw);
}