package com.example.PurpleGym.Repository;

import com.example.PurpleGym.Model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
