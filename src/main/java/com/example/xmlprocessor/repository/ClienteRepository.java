package com.example.xmlprocessor.repository;

import com.example.xmlprocessor.model.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

/**
 * Interface de repositório para a entidade {@link Cliente}.
 * Fornece métodos CRUD (Criar, Ler, Atualizar, Deletar) padrão
 * e consultas personalizadas para acessar os dados do cliente no banco de dados.
 * Estende {@link JpaRepository} para herdar as operações básicas de persistência.
 */
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    /**
     * Busca um cliente pelo seu endereço de e-mail.
     * Como o e-mail é uma coluna única, este método retornará no máximo um cliente.
     *
     * @param email O endereço de e-mail do cliente a ser pesquisado.
     * @return um {@link Optional} contendo o {@link Cliente} encontrado, ou um
     * {@link Optional#empty()} se nenhum cliente for encontrado com o e-mail fornecido.
     */
    Optional<Cliente> findByEmail(String email);
}