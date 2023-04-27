package com.armonia10.backend.responseentity;

public class RespuestaPedido {
    private String mensaje;
    private Object pedidos;
    public RespuestaPedido(){}

    public RespuestaPedido(String mensaje, Object pedidos) {
        this.mensaje = mensaje;
        this.pedidos = pedidos;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Object getPedidos() {
        return pedidos;
    }

    public void setPedidos(Object pedidos) {
        this.pedidos = pedidos;
    }
}
