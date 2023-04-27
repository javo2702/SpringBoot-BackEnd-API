package com.armonia10.backend.business;

import com.armonia10.backend.entity.Bodega;
import com.armonia10.backend.entity.Categoria;
import com.armonia10.backend.exception.BusinessException;
import com.armonia10.backend.exception.NotFoundException;
import com.armonia10.backend.repository.BodegaRepository;
import com.armonia10.backend.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class CategoriaBusiness implements ICategoriaBusiness {
    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private BodegaRepository bodegaRepository;
    @Override
    public List<Categoria> listarCategoriasBodega(Long id) throws BusinessException, NotFoundException {
        Optional<Bodega> bo;
        try{
            bo = bodegaRepository.findById(id);
        } catch (Exception e){
            throw new BusinessException(e.getMessage());
        }
        if(!bo.isPresent()){
            throw new NotFoundException("No se encontro la bodega con el id "+id);
        }
        else{
            try{
                return categoriaRepository.getCategoriasBodegas(id);
            } catch (Exception e){
                throw new BusinessException(e.getMessage());
            }
        }
    }
}
