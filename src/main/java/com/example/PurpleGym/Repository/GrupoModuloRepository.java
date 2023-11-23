package com.example.PurpleGym.Repository;

import com.example.PurpleGym.Model.GrupoModulo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GrupoModuloRepository extends JpaRepository<GrupoModulo, Long> {
    // Adicione métodos de consulta específicos, se necessário
}