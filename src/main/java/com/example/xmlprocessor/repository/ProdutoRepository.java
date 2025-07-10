package com.example.xmlprocessor.repository;

import com.example.xmlprocessor.model.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

/**
 * Interface de repositório para a entidade {@link Produto}.
 * Estende {@link JpaRepository}, fornecendo um conjunto completo de operações CRUD
 * para a entidade Produto, bem como métodos de consulta personalizados.
 */
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    /**
     * Busca um produto pelo seu nome.
     *
     * @param nome O nome do produto a ser pesquisado no banco de dados.
     * @return um {@link Optional} contendo o {@link Produto} se um com o nome
     * exato for encontrado, ou um {@link Optional#empty()} caso contrário.
     */
    Optional<Produto> findByNome(String nome);
}