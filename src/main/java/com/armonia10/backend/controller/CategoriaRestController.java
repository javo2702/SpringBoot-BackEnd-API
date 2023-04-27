package com.armonia10.backend.controller;


import com.armonia10.backend.business.ICategoriaBusiness;
import com.armonia10.backend.entity.Categoria;
import com.armonia10.backend.exception.BusinessException;
import com.armonia10.backend.exception.NotFoundException;
import com.armonia10.backend.responseentity.Categorias;
import com.armonia10.backend.responseentity.RespuestaCategoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/atencioncliente/v1/categorias")
public class CategoriaRestController {
    @Autowired
    private ICategoriaBusiness categoriaBusiness;

    @GetMapping("/listar/bodega/{id}")
    public ResponseEntity<RespuestaCategoria> listCategoriesBodega(@PathVariable Long id){
        RespuestaCategoria respuesta = new RespuestaCategoria();
        try{
            List<Categoria> categoriasBodega = categoriaBusiness.listarCategoriasBodega(id);
            List<Categorias> respuestaCategorias = new ArrayList<>();
            for(Categoria c:categoriasBodega){
                Categorias ca = new Categorias();
                ca.setIdcategoria(c.getIdcategoria());
                ca.setNombre(c.getNombre());
                ca.setImg_url(c.getImg_url());
                respuestaCategorias.add(ca);
            }
            if(respuestaCategorias.size()>0)
                respuesta.setMensaje("Categorias encontradas: "+respuestaCategorias.size());
            else
                respuesta.setMensaje("No se encontraron categorias");
            respuesta.setCategorias(respuestaCategorias);
            return new ResponseEntity<>(respuesta, HttpStatus.OK);
        } catch (BusinessException e){
            return new ResponseEntity<>(respuesta,HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (NotFoundException e){
            return new ResponseEntity<>(respuesta,HttpStatus.NOT_FOUND);
        }
    }
}
