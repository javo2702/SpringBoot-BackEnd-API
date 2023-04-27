package com.armonia10.backend.business;

import com.armonia10.backend.entity.Bodega;
import com.armonia10.backend.entity.Bodeguero;
import com.armonia10.backend.exception.BusinessException;
import com.armonia10.backend.exception.NotFoundException;
import com.armonia10.backend.repository.BodegaRepository;
import com.armonia10.backend.repository.BodegueroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class BodegueroBusiness implements IBodegueroBusiness{
    @Autowired
    private BodegueroRepository bodegueroRepository;
    @Autowired
    private BodegaRepository bodegaRepository;
    @Override
    public Bodeguero show(Long id) throws BusinessException, NotFoundException {
        Optional<Bodeguero> op;
        try{
            op = bodegueroRepository.findById(id);
        } catch (Exception e){
            throw new BusinessException(e.getMessage());
        }
        if(!op.isPresent()){
            throw new NotFoundException("No se encontro el bodeguero con el id "+id);
        }
        return op.get();
    }

    @Override
    public Bodeguero buscarBodeguero(String correo, String pass) throws BusinessException, NotFoundException {
        try{
            return bodegueroRepository.buscarBodeguero(correo,pass);
        }catch (Exception e){
            throw new BusinessException(e.getMessage());
        }
    }
}
