package com.armonia10.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "bodega")
public class Bodega implements Serializable {
    private String nombre;
    private String premium;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idubicacion")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Ubicacion ubicacion;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idbodega;

    @OneToMany(mappedBy = "bodega", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    //@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    @JsonIgnore
    public Set<Pedido> pedidos = new HashSet<>();
    @OneToMany(mappedBy = "bodega", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    //@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    @JsonIgnore
    public Set<Producto> productos = new HashSet<>();

    public Bodega() {
    }

    public Bodega(String nombre, Long idbodega, String premium) {
        this.nombre = nombre;
        this.idbodega = idbodega;
        this.premium = premium;
    }
    public Bodega(String nombre, Long idbodega) {
        this.nombre = nombre;
        this.idbodega = idbodega;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }

    public Long getIdbodega() {
        return idbodega;
    }

    public void setIdbodega(Long idbodega) {
        this.idbodega = idbodega;
    }

    public String getPremium() {
        return premium;
    }

    public void setPremium(String premium) {
        this.premium = premium;
    }

    @JsonManagedReference(value = "bodega")
    public Set<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(Set<Pedido> pedidos) {
        this.pedidos = pedidos;
        for (Pedido pedido: pedidos){
            pedido.setBodega(this);
        }
    }
    @JsonManagedReference(value = "bodega2")
    public Set<Producto> getProductos() {
        return productos;
    }

    public void setProductos(Set<Producto> productos) {
        this.productos = productos;
        for (Producto producto: productos){
            producto.setBodega(this);
        }
    }
}
