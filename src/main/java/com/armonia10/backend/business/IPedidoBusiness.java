package com.armonia10.backend.business;

import com.armonia10.backend.entity.Pedido;
import com.armonia10.backend.exception.BusinessException;
import com.armonia10.backend.exception.NotFoundException;

import java.util.Date;
import java.util.List;

public interface IPedidoBusiness {
    /*List<Pedido> listAll() throws BusinessException;
    Pedido show(Long id) throws BusinessException, NotFoundException;*/
    Pedido save(Pedido pedido) throws BusinessException, NotFoundException;
    /*Pedido update(Long id, Pedido pedido) throws BusinessException, NotFoundException;
    void remove(Long id) throws BusinessException, NotFoundException;*/
    List<Pedido> getPedidosCliente(Long id) throws BusinessException, NotFoundException;
    Pedido getDetallePedidoCliente(Long id_cliente, Long id_pedido) throws BusinessException, NotFoundException;
    List<Pedido> getPedidosBodega(Long id) throws BusinessException, NotFoundException;
    List<Pedido> getVentasSemanes(String fecha_inicio, String fecha_fin, Long idBodega) throws BusinessException, NotFoundException;
    Pedido updateEstadoPedido(String nuevo_estado, Long id_pedido) throws BusinessException, NotFoundException;
}
