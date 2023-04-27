package com.armonia10.backend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "detalle_pedido")
public class DetallePedido implements Serializable {
    private int cantidad;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ManyToOne
    @JoinColumn(name = "idpedido")
    private Pedido pedido;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ManyToOne
    @JoinColumn(name = "idproducto" , nullable = false)
    private Producto producto;

    @JsonBackReference(value = "detallepedido")
    public Pedido getPedido() {
        return pedido;
    }
    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    //@JsonManagedReference
    //@JsonBackReference

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
