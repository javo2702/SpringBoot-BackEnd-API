package com.armonia10.backend.business;

import com.armonia10.backend.entity.Cliente;
import com.armonia10.backend.exception.BusinessException;
import com.armonia10.backend.exception.NotFoundException;
import com.armonia10.backend.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class ClienteBusiness implements IClienteBusiness{
    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public Cliente buscarCliente(String correo, String pass) throws BusinessException, NotFoundException {
        try{
            return clienteRepository.buscarCliente(correo,pass);
        }catch (Exception e){
            throw new BusinessException(e.getMessage());
        }
    }
    @Override
    public Cliente show(Long id) throws BusinessException, NotFoundException {
        Optional<Cliente> op;
        try{
            op = clienteRepository.findById(id);
        } catch (Exception e){
            throw new BusinessException(e.getMessage());
        }
        if(!op.isPresent()){
            throw new NotFoundException("No se encontro el cliente con el id "+id);
        }
        return op.get();
    }

}
