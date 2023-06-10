package com.clients.crud.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "email")
@NamedQueries({
        @NamedQuery(name = Email.EMAIL_AND_CLIENT_Q, query = "SELECT e FROM Email e"
                + " JOIN e.client c" + " WHERE c.id not in (:ids)" + " AND c.id = :id") })

public class Email {

    public static final String EMAIL_AND_CLIENT_Q = "emailandclient.q";

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "id_email")

    private Long id;

    @Column(name = "category",nullable = false,length =20)
    private String category;

    @Column(name = "name",nullable = false,length =20)
    private String name;

    @Column(name = "email",nullable = false,length =30)
    private String email;

    @ManyToOne()
    @JoinColumn(name = "id_client")
    @JsonBackReference
    private Client client;

}