package com.armonia10.backend.repository;

import com.armonia10.backend.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    @Query(value = "{call productosBodega(:id_bodega)};", nativeQuery = true)
    List<Producto> getProductosBodega(@Param("id_bodega") Long id_bodega);

    @Query(value = "{call buscarProductoNombre(:nombreBuscar)};", nativeQuery = true)
    List<Producto> getProductosNombre(@Param("nombreBuscar") String nombreBuscar);

    @Query(value = "{call buscarProductoNombreBodega(:nombreBuscar,:id_bodega)};", nativeQuery = true)
    List<Producto> getProductosNombreBodega(@Param("nombreBuscar") String nombreBuscar,@Param("id_bodega") Long id_bodega);

    @Query(value = "{call mostrarProductosCategoria(:id_Categoria,:id_Bodega)};", nativeQuery = true)
    List<Producto> getProductosCateoria(@Param("id_Categoria") Long id_Categoria,@Param("id_Bodega") Long id_Bodega);

    @Query(value = "{call actualizarProducto(:id_producto, :p_nombre,:p_stock,:p_precio)}", nativeQuery = true)
    Producto updateProducto(@Param("id_producto") Long id_producto, @Param("p_nombre") String p_nombre,@Param("p_stock") int p_stock, @Param("p_precio") double p_precio);
}