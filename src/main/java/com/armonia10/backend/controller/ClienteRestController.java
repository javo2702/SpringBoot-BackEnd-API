package com.armonia10.backend.controller;

import com.armonia10.backend.business.IClienteBusiness;
import com.armonia10.backend.entity.Cliente;
import com.armonia10.backend.exception.BusinessException;
import com.armonia10.backend.exception.NotFoundException;
import com.armonia10.backend.responseentity.ClienteEncontrado;
import com.armonia10.backend.responseentity.RespuestaCliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/atencioncliente/v1/clientes")
public class ClienteRestController {
    @Autowired
    private IClienteBusiness clienteBusiness;

    @PostMapping("/buscarcliente")
    public ResponseEntity<RespuestaCliente> buscarCliente(/*@RequestBody Cliente cliente*/@RequestParam String correo, @RequestParam String pass) {
        //public ResponseEntity<List<Categoria>> listAll(){
        RespuestaCliente respuesta = new RespuestaCliente();
        respuesta.setMensaje("Error al buscar cliente, revise correo y contraseña");
        try{
            Cliente clienteEncontrado = clienteBusiness.buscarCliente(correo,pass);
            if(clienteEncontrado!=null){
                respuesta.setMensaje("Cliente Encontrado");
                ClienteEncontrado cli = new ClienteEncontrado();
                cli.setIdcliente(clienteEncontrado.getIdcliente());
                cli.setApellidos(clienteEncontrado.getApellidos());
                cli.setNombres(clienteEncontrado.getNombres());
                cli.setTelefono(clienteEncontrado.getTelefono());
                cli.setDireccion(clienteEncontrado.getDireccion());
                cli.setCorreo(clienteEncontrado.getCorreo());
                cli.setContraseña("");
                respuesta.setCliente(cli);
            }else{
                respuesta.setMensaje("Cliente No Encontrado");
                ClienteEncontrado cli = new ClienteEncontrado();
                cli.setIdcliente(-1L);
                cli.setApellidos("");
                cli.setNombres("");
                cli.setTelefono("");
                cli.setDireccion("");
                cli.setCorreo("");
                cli.setContraseña("");
                respuesta.setCliente(cli);
            }
            return new ResponseEntity<>(respuesta, HttpStatus.OK);
        } catch (BusinessException | NotFoundException e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/consultar/{id}")
    public ResponseEntity<RespuestaCliente> show(@PathVariable Long id){
        //public ResponseEntity<Categoria> showCategory(@PathVariable Long id){
        RespuestaCliente respuesta = new RespuestaCliente();
        respuesta.setMensaje("Error al obtener cliente");
        try{
            Cliente cliente = clienteBusiness.show(id);
            ClienteEncontrado cli = new ClienteEncontrado();
            cli.setIdcliente(cliente.getIdcliente());
            cli.setApellidos(cliente.getApellidos());
            cli.setNombres(cliente.getNombres());
            cli.setTelefono(cliente.getTelefono());
            cli.setDireccion(cliente.getDireccion());
            cli.setCorreo(cliente.getCorreo());
            cli.setContraseña("");
            respuesta.setMensaje("Cliente encontrado con id: "+id);
            respuesta.setCliente(cli);
            return new ResponseEntity<>(respuesta,
                    HttpStatus.OK);
        } catch (BusinessException e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
