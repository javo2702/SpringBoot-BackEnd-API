package com.armonia10.backend.business;

import com.armonia10.backend.entity.Cliente;
import com.armonia10.backend.exception.BusinessException;
import com.armonia10.backend.exception.NotFoundException;

import java.util.List;

public interface IClienteBusiness {
    /*List<Cliente> listAll() throws BusinessException;

    Cliente save(Cliente cliente) throws BusinessException;
    Cliente update(Long id, Cliente cliente) throws BusinessException, NotFoundException;
    void remove(Long id) throws BusinessException, NotFoundException; */
    Cliente show(Long id) throws BusinessException, NotFoundException;
    Cliente buscarCliente(String correo, String pass) throws BusinessException, NotFoundException;
}
