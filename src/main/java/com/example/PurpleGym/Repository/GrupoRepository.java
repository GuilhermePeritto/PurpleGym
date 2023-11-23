package com.example.PurpleGym.Repository;

import com.example.PurpleGym.Model.Grupo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GrupoRepository extends JpaRepository<Grupo, Long> {
    // Adicione métodos de consulta específicos, se necessário
}