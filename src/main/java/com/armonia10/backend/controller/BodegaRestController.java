package com.armonia10.backend.controller;

import com.armonia10.backend.business.IBodegaBusiness;
import com.armonia10.backend.entity.Bodega;
import com.armonia10.backend.exception.BusinessException;
import com.armonia10.backend.exception.NotFoundException;
import com.armonia10.backend.responseentity.BodegaUbicacion;
import com.armonia10.backend.responseentity.RespuestaBodegas;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping("/api/atencioncliente/v1/bodegas")
public class BodegaRestController {
    @Autowired
    private IBodegaBusiness bodegaBusiness;
    @GetMapping("/listarbodegas/")
    public ResponseEntity<RespuestaBodegas> listAll() {
        RespuestaBodegas rp = new RespuestaBodegas();
        try{
            List<Bodega> bodegas = bodegaBusiness.listAllBodegas();
            List<BodegaUbicacion> bodegasRespuesta = new ArrayList<>();
            for(Bodega bo: bodegas){
                BodegaUbicacion bu = new BodegaUbicacion();
                bu.setIdbodega(bo.getIdbodega());
                bu.setNombre(bo.getNombre());
                bu.setDireccion(bo.getUbicacion().getNombre());
                bu.setLatitud(bo.getUbicacion().getLatitud());
                bu.setLongitud(bo.getUbicacion().getLongitud());
                bodegasRespuesta.add(bu);
            }
            if(bodegasRespuesta.size()>0)
                rp.setMensaje("Total de bodegas: "+bodegasRespuesta.size());
            else
                rp.setMensaje("Sin bodegas disponibles");
            rp.setBodegas(bodegasRespuesta);
            return new ResponseEntity<>(rp, HttpStatus.OK);
        } catch (BusinessException e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/listarbodegas/premium")
    public ResponseEntity<RespuestaBodegas> listAllPremium() {
        RespuestaBodegas rp = new RespuestaBodegas();
        try{
            List<Bodega> bodegas = bodegaBusiness.listAllBodegasPremium();
            List<BodegaUbicacion> bodegasRespuesta = new ArrayList<>();
            for(Bodega bo: bodegas){
                BodegaUbicacion bu = new BodegaUbicacion();
                bu.setIdbodega(bo.getIdbodega());
                bu.setNombre(bo.getNombre());
                bu.setDireccion(bo.getUbicacion().getNombre());
                bu.setLatitud(bo.getUbicacion().getLatitud());
                bu.setLongitud(bo.getUbicacion().getLongitud());
                bodegasRespuesta.add(bu);
            }
            if(bodegasRespuesta.size()>0)
                rp.setMensaje("Total de bodegas: "+bodegasRespuesta.size());
            else
                rp.setMensaje("Sin bodegas disponibles");
            rp.setBodegas(bodegasRespuesta);
            return new ResponseEntity<>(rp, HttpStatus.OK);
        } catch (BusinessException e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/consultar/{id}")
    public ResponseEntity<RespuestaBodegas> show(@PathVariable Long id){
        //public ResponseEntity<Categoria> showCategory(@PathVariable Long id){
        RespuestaBodegas rp = new RespuestaBodegas();
        try{
            Bodega bodegas = bodegaBusiness.show(id);
            BodegaUbicacion bodegaRespuesta = new BodegaUbicacion(
                    bodegas.getIdbodega(),
                    bodegas.getNombre(),
                    bodegas.getUbicacion().getNombre(),
                    bodegas.getUbicacion().getLatitud(),
                    bodegas.getUbicacion().getLongitud());
            rp.setMensaje("Bodega "+bodegas.getNombre());
            rp.setBodegas(bodegaRespuesta);
            return new ResponseEntity<>(rp, HttpStatus.OK);
        } catch (BusinessException e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (NotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
