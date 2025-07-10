package com.example.xmlprocessor.model.entity;

import jakarta.persistence.*;

/**
 * Representa a entidade Cliente no banco de dados.
 * Cada instância desta classe corresponde a uma linha na tabela "clientes".
 */
@Entity
@Table(name = "clientes")
public class Cliente {

    /**
     * O identificador único para o cliente. É gerado automaticamente pelo banco de dados.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * O nome do cliente. Não pode ser nulo.
     */
    @Column(nullable = false)
    private String nome;

    /**
     * O endereço de e-mail do cliente. Deve ser único e não pode ser nulo.
     */
    @Column(nullable = false, unique = true)
    private String email;

    /**
     * O endereço físico do cliente. Não pode ser nulo.
     */
    @Column(nullable = false)
    private String endereco;

    /**
     * O número de telefone do cliente. Não pode ser nulo.
     */
    @Column(nullable = false)
    private String telefone;

    // Getters e Setters

    /**
     * Obtém o ID do cliente.
     *
     * @return o ID do cliente.
     */
    public Long getId() {
        return id;
    }

    /**
     * Define o ID do cliente.
     *
     * @param id o novo ID do cliente.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtém o nome do cliente.
     *
     * @return o nome do cliente.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Define o nome do cliente.
     *
     * @param nome o novo nome do cliente.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Obtém o e-mail do cliente.
     *
     * @return o e-mail do cliente.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Define o e-mail do cliente.
     *
     * @param email o novo e-mail do cliente.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Obtém o endereço do cliente.
     *
     * @return o endereço do cliente.
     */
    public String getEndereco() {
        return endereco;
    }

    /**
     * Define o endereço do cliente.
     *
     * @param endereco o novo endereço do cliente.
     */
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    /**
     * Obtém o telefone do cliente.
     *
     * @return o telefone do cliente.
     */
    public String getTelefone() {
        return telefone;
    }

    /**
     * Define o telefone do cliente.
     *
     * @param telefone o novo telefone do cliente.
     */
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
