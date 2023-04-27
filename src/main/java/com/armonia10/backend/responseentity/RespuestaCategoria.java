package com.armonia10.backend.responseentity;

public class RespuestaCategoria {
    private String mensaje;
    private Object categorias;
    public RespuestaCategoria(){

    }
    public RespuestaCategoria(String mensaje, Object categorias) {
        this.mensaje = mensaje;
        this.categorias = categorias;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Object getCategorias() {
        return categorias;
    }

    public void setCategorias(Object categorias) {
        this.categorias = categorias;
    }
}
