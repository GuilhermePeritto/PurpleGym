package com.example.PurpleGym.Repository;

import com.example.PurpleGym.Model.Modulo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModuloRepository extends JpaRepository<Modulo, Long> {
    // Adicione métodos de consulta específicos, se necessário
}