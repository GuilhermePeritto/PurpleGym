package com.example.PurpleGym.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "grupomodulo")
public class GrupoModulo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idGrupoModulo;

    @ManyToOne
    @JoinColumn(name = "idgrupo")
    private Grupo grupo;

    @ManyToOne
    @JoinColumn(name = "idmodulo")
    private Modulo modulo;

    // Getters e setters
}
