package com.example.PurpleGym.Repository;

import com.example.PurpleGym.Model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

        Usuario findByEmailAndSenha(String email, String senha);

        Usuario findByEmail(String email);
}
