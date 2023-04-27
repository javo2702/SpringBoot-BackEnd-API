package com.armonia10.backend.business;


import com.armonia10.backend.entity.Bodega;
import com.armonia10.backend.exception.BusinessException;
import com.armonia10.backend.exception.NotFoundException;
import com.armonia10.backend.repository.BodegaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BodegaBusiness implements IBodegaBusiness{
    @Autowired
    private BodegaRepository bodegaRepository;
    @Override
    public Bodega show(Long id) throws BusinessException, NotFoundException {
        Optional<Bodega> op;
        try{
            op = bodegaRepository.findById(id);
        } catch (Exception e){
            throw new BusinessException(e.getMessage());
        }
        if(!op.isPresent()){
            throw new NotFoundException("No se encontro la bodega con el id "+id);
        }
        return op.get();
    }
    @Override
    public List<Bodega> listAllBodegas() throws BusinessException {
        try{
            return bodegaRepository.getBodegas();
        } catch (Exception e){
            throw new BusinessException(e.getMessage());
        }
    }

    @Override
    public List<Bodega> listAllBodegasPremium() throws BusinessException {
        try{
            return bodegaRepository.getBodegasPremium();
        } catch (Exception e){
            throw new BusinessException(e.getMessage());
        }
    }
}
