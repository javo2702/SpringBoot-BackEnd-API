package com.armonia10.backend.responseentity;

public class RespuestaProductos {
    private String mensaje;
    private Object productos;

    public RespuestaProductos() {
    }

    public RespuestaProductos(String mensaje, Object productos) {
        this.mensaje = mensaje;
        this.productos = productos;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Object getProductos() {
        return productos;
    }

    public void setProductos(Object productos) {
        this.productos = productos;
    }
}