package com.clients.crud.repository.cliente;

import com.clients.crud.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Client, Long>,ClienteRepositoryCustom{
}
