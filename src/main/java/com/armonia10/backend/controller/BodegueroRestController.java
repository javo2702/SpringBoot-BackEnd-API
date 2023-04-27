package com.armonia10.backend.controller;


import com.armonia10.backend.business.IBodegueroBusiness;
import com.armonia10.backend.entity.Bodeguero;
import com.armonia10.backend.exception.BusinessException;
import com.armonia10.backend.exception.NotFoundException;
import com.armonia10.backend.responseentity.BodegueroEncontrado;
import com.armonia10.backend.responseentity.RespuestaBodeguero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/gestioninventario/v1/bodegueros")
public class BodegueroRestController {
    @Autowired
    private IBodegueroBusiness bodegueroBusiness;
    @GetMapping("/buscarbodeguero")
    public ResponseEntity<RespuestaBodeguero> buscarCliente(/*@RequestBody Cliente cliente*/@RequestParam String correo, @RequestParam String pass) {
        //public ResponseEntity<List<Categoria>> listAll(){
        RespuestaBodeguero respuesta = new RespuestaBodeguero();
        respuesta.setMensaje("Error al buscar bodeguero, revise correo y contraseña");
        try{
            Bodeguero bodegueroEncontrado = bodegueroBusiness.buscarBodeguero(correo,pass);
            if(bodegueroEncontrado!=null){
                respuesta.setMensaje("Bodeguero Encontrado");
                BodegueroEncontrado cli = new BodegueroEncontrado();
                cli.setIdbodeguero(bodegueroEncontrado.getIdbodeguero());
                cli.setApellidos(bodegueroEncontrado.getApellidos());
                cli.setNombres(bodegueroEncontrado.getNombres());
                cli.setTelefono(bodegueroEncontrado.getTelefono());
                cli.setCorreo(bodegueroEncontrado.getCorreo());
                cli.setIdbodega(bodegueroEncontrado.getBodega().getIdbodega());
                cli.setContraseña("");
                respuesta.setBodeguero(cli);
            }
            return new ResponseEntity<>(respuesta, HttpStatus.OK);
        } catch (BusinessException | NotFoundException e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/consultar/{id}")
    public ResponseEntity<RespuestaBodeguero> show(@PathVariable Long id){
        //public ResponseEntity<Categoria> showCategory(@PathVariable Long id){
        RespuestaBodeguero respuesta = new RespuestaBodeguero();
        respuesta.setMensaje("Error al obtener bodeguero");
        try{
            Bodeguero bodeguero = bodegueroBusiness.show(id);
            BodegueroEncontrado cli = new BodegueroEncontrado();
            cli.setIdbodeguero(bodeguero.getIdbodeguero());
            cli.setApellidos(bodeguero.getApellidos());
            cli.setNombres(bodeguero.getNombres());
            cli.setTelefono(bodeguero.getTelefono());
            cli.setCorreo(bodeguero.getCorreo());
            cli.setIdbodega(bodeguero.getBodega().getIdbodega());
            cli.setContraseña("");
            respuesta.setBodeguero(cli);
            respuesta.setMensaje("Bodeguero encontrado con id: "+id);
            return new ResponseEntity<>(respuesta,
                    HttpStatus.OK);
        } catch (BusinessException e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
