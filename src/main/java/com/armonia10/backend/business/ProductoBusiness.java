package com.armonia10.backend.business;

import com.armonia10.backend.entity.Bodega;
import com.armonia10.backend.entity.Categoria;
import com.armonia10.backend.entity.Producto;
import com.armonia10.backend.exception.BusinessException;
import com.armonia10.backend.exception.NotFoundException;
import com.armonia10.backend.repository.BodegaRepository;
import com.armonia10.backend.repository.CategoriaRepository;
import com.armonia10.backend.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoBusiness implements IProductoBusiness{
    @Autowired
    private ProductoRepository productoRepository;
    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private BodegaRepository bodegaRepository;

    @Override
    public Producto saveProduct(Producto producto) throws BusinessException{
        try{
            Optional<Categoria> cat;
            Optional<Bodega> bod;
            if(producto.categoria!=null){
                cat = categoriaRepository.findById(producto.getCategoria().getIdcategoria());
                if(!cat.isPresent()){
                    throw new NotFoundException("La categoria a la que se quiere registrar un producto no existe");
                }
                producto.setCategoria(cat.get());
                //return productoRepository.save(producto);
            }
            if(producto.getBodega()!=null){
                bod = bodegaRepository.findById(producto.getBodega().getIdbodega());
                if(!bod.isPresent()){
                    throw new NotFoundException("La bodega a la que se quiere registrar un producto no existe");
                }
                producto.setBodega(bod.get());
            }
            return productoRepository.save(producto);

        } catch (Exception e){
            throw new BusinessException(e.getMessage());
        }
    }
    @Override
    public Producto showProduct(Long id) throws BusinessException, NotFoundException {
        Optional<Producto> op;
        try{
            op = productoRepository.findById(id);
        } catch (Exception e){
            throw new BusinessException(e.getMessage());
        }
        if(!op.isPresent()){
            throw new NotFoundException("No se encontro el producto con el id "+id);
        }
        return op.get();
    }
    @Override
    public void removeProduct(Long id) throws BusinessException, NotFoundException{
        Optional<Producto> op;
        try{
            op = productoRepository.findById(id);
        } catch (Exception e){
            throw new BusinessException(e.getMessage());
        }
        if(!op.isPresent()){
            throw new NotFoundException("No se encontro el producto con el id "+id);
        }
        else{
            try {
                productoRepository.deleteById(id);
            }
            catch (Exception e){
                throw new BusinessException(e.getMessage());
            }
        }
    }

    @Override
    public List<Producto> listProductsBodega(Long id) throws BusinessException, NotFoundException {
        Optional<Bodega> op;
        try{
            op = bodegaRepository.findById(id);
        } catch (Exception e){
            throw new BusinessException(e.getMessage());
        }
        if(!op.isPresent()){
            throw new NotFoundException("No se encontro la bodega con el id "+id);
        }
        else{
            try{
                return productoRepository.getProductosBodega(id);
            } catch (Exception e){
                throw new BusinessException(e.getMessage());
            }
        }
    }

    @Override
    public List<Producto> listaProductsNombre(String nombre) throws BusinessException{
        try{
            return productoRepository.getProductosNombre(nombre);
        } catch (Exception e){
            throw new BusinessException(e.getMessage());
        }
    }

    @Override
    public List<Producto> listProductsNombreBodega(String nombre, Long id) throws BusinessException, NotFoundException {
        Optional<Bodega> op;
        try{
            op = bodegaRepository.findById(id);
        } catch (Exception e){
            throw new BusinessException(e.getMessage());
        }
        if(!op.isPresent()){
            throw new NotFoundException("No se encontro la bodega con el id "+id);
        }
        else{
            try{
                return productoRepository.getProductosNombreBodega(nombre,id);
            } catch (Exception e){
                throw new BusinessException(e.getMessage());
            }
        }
    }

    @Override
    public List<Producto> listProductsCategory(Long id1, Long id2) throws BusinessException, NotFoundException {
        Optional<Bodega> op;
        Optional<Categoria> cat;
        try{
            op = bodegaRepository.findById(id2);
        } catch (Exception e){
            throw new BusinessException(e.getMessage());
        }
        if(!op.isPresent()){
            throw new NotFoundException("No se encontro la bodega con el id "+id2);
        }
        else{
            try{
                cat = categoriaRepository.findById(id1);
            } catch (Exception e){
                throw new BusinessException(e.getMessage());
            }
            if(!cat.isPresent()){
                throw new NotFoundException("No se encontro la categoria con el id "+id2);
            } else{
                try{
                    return productoRepository.getProductosCateoria(id1,id2);
                } catch (Exception e){
                    throw new BusinessException(e.getMessage());
                }
            }

        }
    }

    @Override
    public Producto actualizarProducto(Long id, String nombre, int stock, double precio) throws BusinessException, NotFoundException {
        Optional<Producto> op;
        try{
            op = productoRepository.findById(id);
        } catch (Exception e){
            throw new NotFoundException("No se encontro el producto con el id "+id);
        }
        if(!op.isPresent()){
            throw new NotFoundException("No se encontro el producto con el id "+id);
        } else{
            try{
                return productoRepository.updateProducto(id,nombre,stock,precio);
            } catch(Exception e){
                throw new BusinessException(e.getMessage());
            }
        }
    }
}
