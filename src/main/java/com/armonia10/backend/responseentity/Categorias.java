package com.armonia10.backend.responseentity;

public class Categorias {
    private Long idcategoria;
    private String nombre;
    private String img_url;
    public Categorias(){

    }

    public Categorias(Long idcategoria, String nombre, String img_url) {
        this.idcategoria = idcategoria;
        this.nombre = nombre;
        this.img_url = img_url;
    }

    public Long getIdcategoria() {
        return idcategoria;
    }

    public void setIdcategoria(Long idcategoria) {
        this.idcategoria = idcategoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }
}
