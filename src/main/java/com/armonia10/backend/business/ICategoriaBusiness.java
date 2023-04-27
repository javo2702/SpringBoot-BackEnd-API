package com.armonia10.backend.business;

import com.armonia10.backend.entity.Categoria;
import com.armonia10.backend.exception.BusinessException;
import com.armonia10.backend.exception.NotFoundException;

import java.util.List;

public interface ICategoriaBusiness {

    List<Categoria> listarCategoriasBodega(Long id) throws BusinessException, NotFoundException;
}