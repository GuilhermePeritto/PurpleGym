package com.example.PurpleGym.Repository;

import com.example.PurpleGym.Model.Cliente;
import com.example.PurpleGym.Model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
