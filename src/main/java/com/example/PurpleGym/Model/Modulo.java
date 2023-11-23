package com.example.PurpleGym.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "modulo")
public class Modulo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idmodulo")
    private Long idModulo;

    private String modulo;

    // Getters e setters
}
