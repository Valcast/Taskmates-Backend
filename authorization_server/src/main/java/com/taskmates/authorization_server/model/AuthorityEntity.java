package com.taskmates.authorization_server.model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "authorities")
public class AuthorityEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(name = "name", nullable = false)
    private Authority name;

    public Authority getName() {
        return name;
    }

}

