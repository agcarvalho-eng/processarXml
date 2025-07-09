package com.example.xmlprocessor.repository;

import com.example.xmlprocessor.model.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional; // Importe o Optional

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    // Método para buscar um produto pelo nome.
    Optional<Produto> findByNome(String nome);
}