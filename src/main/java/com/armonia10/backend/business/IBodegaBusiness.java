package com.armonia10.backend.business;


import com.armonia10.backend.entity.Bodega;
import com.armonia10.backend.exception.BusinessException;
import com.armonia10.backend.exception.NotFoundException;

import java.util.List;

public interface IBodegaBusiness {

    Bodega show(Long id) throws BusinessException, NotFoundException;
    List<Bodega> listAllBodegas() throws BusinessException;

    List<Bodega> listAllBodegasPremium() throws BusinessException;

}
