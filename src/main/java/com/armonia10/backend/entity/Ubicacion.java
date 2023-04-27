package com.armonia10.backend.entity;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ubicacion")
public class Ubicacion implements Serializable {
    private String nombre;
    private String latitud;
    private String longitud;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idubicacion;

    public Ubicacion() {
    }

    public Ubicacion(String nombre, String latitud, String longitud, Long idubicacion) {
        this.nombre = nombre;
        this.latitud = latitud;
        this.longitud = longitud;
        this.idubicacion = idubicacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public Long getIdubicacion() {
        return idubicacion;
    }

    public void setIdubicacion(Long idubicacion) {
        this.idubicacion = idubicacion;
    }
}
