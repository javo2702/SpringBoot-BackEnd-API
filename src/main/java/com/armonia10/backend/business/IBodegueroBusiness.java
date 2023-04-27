package com.armonia10.backend.business;


import com.armonia10.backend.entity.Bodeguero;
import com.armonia10.backend.exception.BusinessException;
import com.armonia10.backend.exception.NotFoundException;

import java.util.List;

public interface IBodegueroBusiness {
    Bodeguero show(Long id) throws BusinessException, NotFoundException;
    Bodeguero buscarBodeguero(String correo, String pass) throws BusinessException, NotFoundException;
}
