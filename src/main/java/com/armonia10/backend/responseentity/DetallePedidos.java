package com.armonia10.backend.responseentity;


import com.armonia10.backend.entity.Producto;

public class DetallePedidos {
    private int cantidad;
    private String pedido;
    private Producto producto;

    public DetallePedidos(){}

    public DetallePedidos(int cantidad, String pedido, Producto producto) {
        this.cantidad = cantidad;
        this.pedido = pedido;
        this.producto = producto;
    }

    public String getPedido() {
        return pedido;
    }
    public void setPedido(String pedido) {
        this.pedido = pedido;
    }

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
