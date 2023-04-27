package com.armonia10.backend.responseentity;

public class RespuestaCliente {
    private String mensaje;
    private Object cliente;
    public RespuestaCliente(){}

    public RespuestaCliente(String mensaje, Object cliente) {
        this.mensaje = mensaje;
        this.cliente = cliente;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Object getCliente() {
        return cliente;
    }

    public void setCliente(Object cliente) {
        this.cliente = cliente;
    }
}
