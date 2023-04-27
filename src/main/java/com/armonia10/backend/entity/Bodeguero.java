package com.armonia10.backend.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "bodeguero")
public class Bodeguero  implements Serializable {
    private String nombres;
    private String apellidos;
    private String telefono;
    private String correo;
    private String contraseña;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idbodeguero;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idbodega")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Bodega bodega;

    public Bodeguero() {
    }

    public Bodeguero(String nombres, String apellidos, String telefono, Long idbodeguero) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.idbodeguero = idbodeguero;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Long getIdbodeguero() {
        return idbodeguero;
    }

    public void setIdbodeguero(Long idbodeguero) {
        this.idbodeguero = idbodeguero;
    }

    public Bodega getBodega() {
        return bodega;
    }

    public void setBodega(Bodega bodega) {
        this.bodega = bodega;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
}
