package com.armonia10.backend.controller;

import com.armonia10.backend.business.IProductoBusiness;
import com.armonia10.backend.entity.Producto;
import com.armonia10.backend.exception.BusinessException;
import com.armonia10.backend.exception.NotFoundException;
import com.armonia10.backend.responseentity.RespuestaMensaje;
import com.armonia10.backend.responseentity.RespuestaProductos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/atencioncliente/v1/productos")
public class ProductoRestController {
    @Autowired
    private IProductoBusiness productoBusiness;



    @PostMapping("/registrar")
    public ResponseEntity<RespuestaProductos> saveProduct(@RequestBody Producto producto){
        RespuestaProductos respuesta = new RespuestaProductos();
        respuesta.setMensaje("Error al guardar producto");
        try{
            Producto productoGuardado = productoBusiness.saveProduct(producto);
            respuesta.setMensaje("Producto guardado de manera exitosa");
            respuesta.setProductos(productoGuardado);
            return new ResponseEntity<>(respuesta, HttpStatus.CREATED);
        }catch (BusinessException e){
            return new ResponseEntity<>(respuesta,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/consultar/{id}")
    public ResponseEntity<RespuestaProductos> showProduct(@PathVariable Long id){
        //public ResponseEntity<Producto> showProduct(@PathVariable Long id){
        RespuestaProductos respuesta = new RespuestaProductos();
        respuesta.setMensaje("Producto con id: "+id+" no se encuentra registrado");
        try{
            Producto producto = productoBusiness.showProduct(id);
            respuesta.setMensaje("Producto con id: "+id);
            respuesta.setProductos(producto);
            return new ResponseEntity<>(respuesta,
                    HttpStatus.OK);
        } catch (BusinessException e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(respuesta,HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/listarproductos/bodega/{id}")
    public ResponseEntity<RespuestaProductos> listarProductosBodega(@PathVariable Long id) {
        try{
            List<?> productos = productoBusiness.listProductsBodega(id);
            RespuestaProductos respuesta = new RespuestaProductos("Productos por Bodega",productos);
            return new ResponseEntity<>(respuesta, HttpStatus.OK);
        } catch (BusinessException e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/buscar")
    public ResponseEntity<RespuestaProductos> listarProductosNombre(@RequestParam String nombre) {
        RespuestaProductos respuesta = new RespuestaProductos();
        try{
            List<?> productos = productoBusiness.listaProductsNombre(nombre);
            if (productos.size()>0)
                respuesta.setMensaje("Se encontraron "+productos.size()+" productos");
            else
                respuesta.setMensaje("No se encontraron coincidencias");
            respuesta.setProductos(productos);
            return new ResponseEntity<>(respuesta, HttpStatus.OK);
        } catch (BusinessException e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (NotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    @GetMapping("/consultar/bodega/{id}/buscar")
    public ResponseEntity<RespuestaProductos> listarProductosNombreBodega(@PathVariable Long id, @RequestParam String nombre) {
        RespuestaProductos respuesta = new RespuestaProductos();
        try{
            List<?> productos = productoBusiness.listProductsNombreBodega(nombre,id);
            if (productos.size()>0)
                respuesta.setMensaje("Se encontraron "+productos.size()+" productos");
            else
                respuesta.setMensaje("No se encontraron coincidencias");
            respuesta.setProductos(productos);
            return new ResponseEntity<>(respuesta, HttpStatus.OK);
        } catch (BusinessException e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/consultar/bodega/{id2}/categoria/{id1}")
    public ResponseEntity<RespuestaProductos> listarProductosCategoria(@PathVariable Long id1, @PathVariable Long id2) {
        RespuestaProductos respuesta = new RespuestaProductos();
        try{
            List<Producto> productos = productoBusiness.listProductsCategory(id1,id2);
            if (productos.size()>0)
                respuesta.setMensaje("Se encontraron "+productos.size()+" productos de la categoria " + productos.get(0).categoria.getNombre());
            else
                respuesta.setMensaje("La categoria no presenta productos registrados");
            respuesta.setProductos(productos);
            return new ResponseEntity<>(respuesta, HttpStatus.OK);
        } catch (BusinessException e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("actualizar/{id}")
    public ResponseEntity<RespuestaProductos> actualizarProducto(@PathVariable Long id, @RequestBody Producto producto){
        RespuestaProductos respuesta = new RespuestaProductos();
        try{
            Producto productoupdate = productoBusiness.actualizarProducto(id,producto.getNombre(),producto.getStock(),producto.getPrecio());

            respuesta.setMensaje("Producto Actualizado de manera exitosa");
            respuesta.setProductos(productoBusiness.showProduct(id));
            return new ResponseEntity<>(respuesta, HttpStatus.OK);
        } catch (BusinessException e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<RespuestaMensaje> deleteProduct(@PathVariable Long id){
        try{
            productoBusiness.removeProduct(id);
            return new ResponseEntity<>(new RespuestaMensaje("Producto Eliminado de manera Exitosa"),HttpStatus.OK);
        } catch (BusinessException e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (NotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
