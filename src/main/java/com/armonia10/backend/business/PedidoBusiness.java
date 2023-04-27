package com.armonia10.backend.business;

import com.armonia10.backend.entity.*;
import com.armonia10.backend.exception.BusinessException;
import com.armonia10.backend.exception.NotFoundException;
import com.armonia10.backend.repository.BodegaRepository;
import com.armonia10.backend.repository.ClienteRepository;
import com.armonia10.backend.repository.PedidoRepository;
import com.armonia10.backend.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoBusiness implements IPedidoBusiness{
    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private BodegaRepository bodegaRepository;
    @Autowired
    private IProductoBusiness productoBusiness;

    @Override
    public List<Pedido> getPedidosCliente(Long id) throws BusinessException, NotFoundException {
        Optional<Cliente> cli;

        try{
            cli = clienteRepository.findById(id);
        } catch (Exception e){
            throw new BusinessException(e.getMessage());
        }
        if(!cli.isPresent()){
            throw new NotFoundException("No se encontro el cliente con el id "+id);
        }else{
            try{
                return pedidoRepository.getPedidosCliente(id);
            }catch (Exception e){
                throw new BusinessException(e.getMessage());
            }
        }
    }

    @Override
    public Pedido getDetallePedidoCliente(Long id_cliente, Long id_pedido) throws BusinessException, NotFoundException {
        Optional<Cliente> cli;
        Optional<Pedido> ped;
        try{
            cli = clienteRepository.findById(id_cliente);
        } catch (Exception e){
            throw new BusinessException(e.getMessage());
        }
        if(!cli.isPresent()){
            throw new NotFoundException("No se encontro el cliente con el id "+id_cliente);
        }else{
            try{
                ped = pedidoRepository.findById(id_pedido);
            } catch (Exception e) {
                throw new BusinessException(e.getMessage());
            }
            if(!ped.isPresent()){
                throw new NotFoundException("No se encontro el pedido con el id "+id_pedido);
            }else{
                try{
                    return pedidoRepository.getDetallePedidoCliente(id_cliente,id_pedido);
                }catch (Exception e){
                    throw new BusinessException(e.getMessage());
                }
            }
        }
    }

    @Override
    public List<Pedido> getPedidosBodega(Long id) throws BusinessException, NotFoundException {
        Optional<Bodega> bo;
        try{
            bo = bodegaRepository.findById(id);
        } catch (Exception e){
            throw new BusinessException(e.getMessage());
        }
        if(!bo.isPresent()){
            throw new NotFoundException("No se encontro la bodega con el id "+id);
        }else{
            try{
                return pedidoRepository.getPedidosBodega(id);
            }catch (Exception e){
                throw new BusinessException(e.getMessage());
            }
        }
    }

    @Override
    public List<Pedido> getVentasSemanes(String fecha_inicio, String fecha_fin, Long idBodega) throws BusinessException, NotFoundException {
        Optional<Bodega> bo;
        try{
            bo = bodegaRepository.findById(idBodega);
        } catch (Exception e){
            throw new BusinessException(e.getMessage());
        }
        if(!bo.isPresent()){
            throw new NotFoundException("No se encontro la bodega con el id "+idBodega);
        }else{
            try{
                System.out.println("CONSULTANDOOOOOOOOOOOOOOOOOOOOOO: "+fecha_inicio);
                return pedidoRepository.getVentasSemanales(fecha_inicio,fecha_fin,idBodega);
            }catch (Exception e){
                System.out.println(e.getMessage());
                throw new BusinessException(e.getMessage());
            }
        }
    }
    @Override
    public Pedido save(Pedido pedido) throws BusinessException, NotFoundException {
        Optional<Cliente> cli;
        Optional<Producto> op;
        Optional<Bodega> bd;
        try{
            cli = clienteRepository.findById(pedido.getCliente().getIdcliente());
            bd = bodegaRepository.findById(pedido.getBodega().getIdbodega());
        } catch (Exception e){
            System.out.println("Cliente no encontrado");
            throw new BusinessException(e.getMessage());
        }
        if(!cli.isPresent() && !bd.isPresent()){
            throw new NotFoundException("No se encontro el cliente o la bodega");
        }
        else{
            for (DetallePedido dt : pedido.getDetallesPedido()){
                op = productoRepository.findById(dt.getProducto().getIdProducto());
                if (!op.isPresent()){
                    throw new NotFoundException("No se encontro el producto con el id "+dt.getProducto().getIdProducto());
                } else{
                    if(dt.getCantidad()>op.get().getStock()){
                        throw new BusinessException("No suficiente stock para el producto con id "+op.get().getIdProducto());
                    }
                    else{
                        Producto pupdate = op.get();
                        pupdate.setStock(op.get().getStock()-dt.getCantidad());
                        //productoBusiness.updateProducto(dt.getProducto().getIdProducto(), pupdate);
                    }
                    dt.setPedido(pedido);
                    dt.setProducto(op.get());
                }
            }
            pedido.setCliente(cli.get());
            pedido.setBodega(bd.get());
            return pedidoRepository.save(pedido);
        }
    }
    @Override
    public Pedido updateEstadoPedido(String nuevo_estado, Long id_pedido) throws BusinessException, NotFoundException {
        Optional<Pedido> ped;
        try{
            ped = pedidoRepository.findById(id_pedido);
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
        if(!ped.isPresent()){
            throw new NotFoundException("No se encontro el pedido con el id "+id_pedido);
        }else{
            try{
                return pedidoRepository.updateEstadoPedido(nuevo_estado,id_pedido);
            }catch (Exception e){
                throw new BusinessException(e.getMessage());
            }
        }
    }
}
