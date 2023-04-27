package com.armonia10.backend.responseentity;

public class RespuestaBodeguero {
    private String mensaje;
    private Object bodeguero;
    public RespuestaBodeguero(){}

    public RespuestaBodeguero(String mensaje, Object bodeguero) {
        this.mensaje = mensaje;
        this.bodeguero = bodeguero;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Object getBodeguero() {
        return bodeguero;
    }

    public void setBodeguero(Object bodeguero) {
        this.bodeguero = bodeguero;
    }
}
