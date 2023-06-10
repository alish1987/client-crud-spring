package com.clients.crud.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "client")
public class Client {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "id_client")
    private Long id;

    @Column(name = "enrollment",nullable = false,length =18)
    private String enrollment;

    @Column(name = "name",nullable = false,length =50)
    private String name;

    @Column(name = "surname",nullable = false,length =30)
    private String surname;

    @Column(columnDefinition = "ENUM('ACTIVE', 'DEACTIVE', 'SUSPEND')",nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @JsonManagedReference
    @OneToMany(mappedBy = "client")
    private List<Email> email = new ArrayList<Email>();

}
