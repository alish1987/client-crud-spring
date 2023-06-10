package com.clients.crud.controller;

import com.clients.crud.model.Client;
import com.clients.crud.model.dto.ClienteResponseDTO;
import com.clients.crud.repository.client.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/api/crud")
@AllArgsConstructor
public class ClientController {
    @Autowired
    private ClientRepository clientRepository;

    @GetMapping(value = "/listAll")
    public ResponseEntity<?> list(Pageable pageable){
        return new ResponseEntity<>(clientRepository.findAll(pageable),HttpStatus.OK);
    }

    @PostMapping("/save")
    public void save(@RequestBody Client client){
        clientRepository.salvar(client);
    }
    @GetMapping("/getClient/{id}")
    public Optional<ClienteResponseDTO> getClient(@PathVariable("id")  Long id){
        return clientRepository.createClienteDTO(id);
    }
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") Long id){ clientRepository.deletar(id);}
}
