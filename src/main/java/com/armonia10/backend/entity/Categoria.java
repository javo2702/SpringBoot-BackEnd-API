package com.armonia10.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "categorias")
public class Categoria implements Serializable {
    private String nombre;
    private String img_url;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idcategoria;

    @OneToMany(mappedBy = "categoria", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    //@OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Producto> productos;

    public Categoria(String nombre, String img_url, Long idcategoria) {
        this.nombre = nombre;
        this.img_url = img_url;
        this.idcategoria = idcategoria;
    }
    public Categoria(){

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

    public Long getIdcategoria() {
        return idcategoria;
    }

    public void setIdcategoria(Long idcategoria) {
        this.idcategoria = idcategoria;
    }
    @JsonManagedReference(value = "productoscate")
    public Set<Producto> getProductos() {
        return productos;
    }

    public void setProductos(Set<Producto> productos) {

        this.productos = productos;
        for (Producto producto: productos){
            producto.setCategoria(this);
        }
    }
}