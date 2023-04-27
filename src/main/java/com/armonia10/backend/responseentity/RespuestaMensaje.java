package com.armonia10.backend.responseentity;

public class RespuestaMensaje {
    String mensaje;
    public RespuestaMensaje(){}

    public RespuestaMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
