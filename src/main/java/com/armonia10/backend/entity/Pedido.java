package com.armonia10.backend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import javax.persistence.*;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "pedidos")
public class Pedido implements Serializable {
    private Date fecha;
    private double monto_total;
    private String estado;
    private String tipo_entrega;
    private String metodo_pago;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    //@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idcliente", nullable = false)
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    //@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idbodega", nullable = false)
    public Bodega bodega;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idpedido;
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<DetallePedido> detallesPedido;
    public Pedido() {
    }

    public Pedido(Date fecha, double monto_total, String estado, String tipo_entrega, String metodo_pago, Cliente cliente, Long idpedido) {
        this.fecha = fecha;
        this.monto_total = monto_total;
        this.estado = estado;
        this.tipo_entrega = tipo_entrega;
        this.metodo_pago = metodo_pago;
        this.cliente = cliente;
        this.idpedido = idpedido;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        this.fecha = sdf.parse(fecha);
    }

    public double getMonto_total() {
        return monto_total;
    }

    public void setMonto_total(double monto_total) {
        this.monto_total = monto_total;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTipo_entrega() {
        return tipo_entrega;
    }

    public void setTipo_entrega(String tipo_entrega) {
        this.tipo_entrega = tipo_entrega;
    }

    public String getMetodo_pago() {
        return metodo_pago;
    }

    public void setMetodo_pago(String metodo_pago) {
        this.metodo_pago = metodo_pago;
    }

    @JsonBackReference(value = "cliente")
    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @JsonBackReference(value = "bodega")
    public Bodega getBodega() {
        return bodega;
    }

    public void setBodega(Bodega bodega) {
        this.bodega = bodega;
    }

    public Long getIdpedido() {
        return idpedido;
    }

    public void setIdpedido(Long idpedido) {
        this.idpedido = idpedido;
    }

    @JsonManagedReference(value = "detallepedido")
    public Set<DetallePedido> getDetallesPedido() {
        return detallesPedido;
    }
    public void setDetallesPedido(Set<DetallePedido> detallesPedido) {
        this.detallesPedido = detallesPedido;
        for(DetallePedido dt: detallesPedido){
            dt.setPedido(this);
        }
    }
}
