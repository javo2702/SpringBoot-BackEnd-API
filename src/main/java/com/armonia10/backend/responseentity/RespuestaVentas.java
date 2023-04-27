package com.armonia10.backend.responseentity;

public class RespuestaVentas {
    private String mensaje;
    private Object ventas;
    public RespuestaVentas(){}

    public RespuestaVentas(String mensaje, Object ventas) {
        this.mensaje = mensaje;
        this.ventas = ventas;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Object getVentas() {
        return ventas;
    }

    public void setVentas(Object ventas) {
        this.ventas = ventas;
    }
}
