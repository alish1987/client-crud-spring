package com.clients.crud.repository.cliente;

import com.clients.crud.model.Client;
import com.clients.crud.model.dto.ClienteResponseDTO;

import java.util.Optional;

public interface ClienteRepositoryCustom {
    public void salvar(Client product);
    public void deletar(Long entity);
    public Optional<ClienteResponseDTO> createClienteDTO(Long id);
}
