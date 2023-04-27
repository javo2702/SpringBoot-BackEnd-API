package com.armonia10.backend.responseentity;

public class BodegaUbicacion {
    private Long idbodega;
    private String nombre;
    private String direccion;
    private String latitud;
    private String longitud;

    public BodegaUbicacion() {
    }

    public BodegaUbicacion(Long idbodega, String nombre, String direccion, String latitud, String longitud) {
        this.idbodega = idbodega;
        this.nombre = nombre;
        this.direccion = direccion;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public Long getIdbodega() {
        return idbodega;
    }

    public void setIdbodega(Long idbodega) {
        this.idbodega = idbodega;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }
}
