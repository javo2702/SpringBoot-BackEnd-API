package com.armonia10.backend.responseentity;

public class BodegueroEncontrado {
    private String nombres;
    private String apellidos;
    private String telefono;
    private String correo;
    private String contraseña;
    private Long idbodeguero;
    private Long idbodega;

    public BodegueroEncontrado() {
    }

    public BodegueroEncontrado(String nombres, String apellidos, String telefono, String correo, String contraseña, Long idbodeguero, Long idbodega) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.correo = correo;
        this.contraseña = contraseña;
        this.idbodeguero = idbodeguero;
        this.idbodega = idbodega;
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

    public Long getIdbodeguero() {
        return idbodeguero;
    }

    public void setIdbodeguero(Long idbodeguero) {
        this.idbodeguero = idbodeguero;
    }

    public Long getIdbodega() {
        return idbodega;
    }

    public void setIdbodega(Long idbodega) {
        this.idbodega = idbodega;
    }
}
