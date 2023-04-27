package com.armonia10.backend.repository;

import com.armonia10.backend.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    @Query(value = "{call pedidosCliente(:id_cliente)};", nativeQuery = true)
    List<Pedido> getPedidosCliente(@Param("id_cliente") Long id_cliente);

    @Query(value = "{call detallePedidoCliente(:id_cliente,:id_pedido)};", nativeQuery = true)
    Pedido getDetallePedidoCliente(@Param("id_cliente") Long id_cliente, @Param("id_pedido") Long id_pedido);

    @Query(value = "{call pedidosBodega(:id_bodega)};", nativeQuery = true)
    List<Pedido> getPedidosBodega(@Param("id_bodega") Long id_bodega);

    @Query(value = "{call mostrarVentas(:fecha_inicio,:fecha_fin,:idBodega)};", nativeQuery = true)
    List<Pedido> getVentasSemanales(@Param("fecha_inicio") String fecha_inicio, @Param("fecha_fin") String fecha_fin, @Param("idBodega") Long idBodega);

    @Query(value = "{call actualizarEstadoPedido(:nuevo_estado,:id_pedido)};", nativeQuery = true)
    Pedido updateEstadoPedido(@Param("nuevo_estado") String nuevo_estado, @Param("id_pedido") Long id_pedido);
}