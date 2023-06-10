package com.clients.crud.repository.client;

import com.clients.crud.model.Client;
import com.clients.crud.model.Email;
import com.clients.crud.model.dto.ClienteResponseDTO;
import com.clients.crud.repository.email.EmailRepository;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Component
public class ClientRepositoryImpl {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    @Lazy
    private ClientRepository clientRepository;

    @Autowired
    @Lazy
    private EmailRepository emailRepository;

    public ClientRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Optional<ClienteResponseDTO> createClienteDTO(Long id){
        String urlImage= "https://upload.wikimedia.org/wikipedia/commons/thumb/1/12/User_icon_2.svg/768px-User_icon_2.svg.png";

        ClienteResponseDTO responseDTO = new ClienteResponseDTO();
        responseDTO.setClient(clientRepository.findById(id));
        responseDTO.setUrlImage(urlImage);

        return Optional.of(responseDTO);
    }

    public void salvar(Client entity) {
        List<Email> emailList  = entity.getEmail();

        entity.setEmail(null);

        entity = clientRepository.save(entity);


        if (entity.getId() != 0) {
            if (entity != null) {
                removeOldEmails(entity, emailList);
            }

        }

        for (Email email : emailList) {
            email.setClient(entity);
            email.setId(0L);
            emailRepository.save(email);
        }
    }

    public void deletar(Long id) {
        Client entity = new Client();
        entity = clientRepository.getById(id);

        removeOldEmails(entity, entity.getEmail());
        clientRepository.deleteById(entity.getId());
    }


    private void removeOldEmails(Client entity, List<Email> emailList) {
        List<Long> ids = new ArrayList<>();

        emailList.forEach(item -> {
            if (item.getId() != null) {
                ids.add(item.getId());
            } else {
                ids.add(0L);
            }
        });

        if (emailList.isEmpty()) {
            ids.add(0L);
        }

        List<Email> odlEmailList = entityManager.createNamedQuery(Email.EMAIL_AND_CLIENT_Q, Email.class).setParameter("ids", ids).setParameter("id", entity.getId()).getResultList();

        if (odlEmailList.size() > 0) {
            odlEmailList.forEach(t -> {
                try {
                    emailRepository.delete(t);
                } catch (ServiceException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
