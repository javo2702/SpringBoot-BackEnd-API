package com.armonia10.backend.responseentity;

public class RespuestaBodegas {
    private String mensaje;
    private Object bodegas;

    public RespuestaBodegas() {
    }

    public RespuestaBodegas(String mensaje, Object bodegas) {
        this.mensaje = mensaje;
        this.bodegas = bodegas;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Object getBodegas() {
        return bodegas;
    }

    public void setBodegas(Object bodegas) {
        this.bodegas = bodegas;
    }
}
