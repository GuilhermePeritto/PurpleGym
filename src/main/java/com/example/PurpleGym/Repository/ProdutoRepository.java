package com.example.PurpleGym.Repository;

import com.example.PurpleGym.Model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    List<Produto> findByNomeContainingIgnoreCaseOrderByNomeAsc(String nome);
    List<Produto> findAllByOrderByNomeAsc();
}
