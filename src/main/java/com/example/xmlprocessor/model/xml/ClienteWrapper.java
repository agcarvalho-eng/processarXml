package com.example.xmlprocessor.model.xml;

import jakarta.xml.bind.annotation.XmlElement;

/**
 * Classe wrapper (invólucro) para o mapeamento de dados de um cliente a partir de um arquivo XML.
 * Utilizada pelo JAXB para deserializar os elementos XML de um cliente em um objeto Java.
 * Esta classe não é uma entidade de banco de dados, mas sim um objeto de transferência de dados (DTO).
 */
public class ClienteWrapper {
    /**
     * O ID do cliente.
     */
    private Long id;
    /**
     * O nome do cliente.
     */
    private String nome;
    /**
     * O email do cliente.
     */
    private String email;
    /**
     * O endereço do cliente.
     */
    private String endereco;
    /**
     * O telefone do cliente.
     */
    private String telefone;

    /**
     * Obtém o ID do cliente. Mapeado para o elemento XML 'id'.
     *
     * @return O ID do cliente.
     */
    @XmlElement
    public Long getId() {
        return id;
    }

    /**
     * Define o ID do cliente.
     *
     * @param id O novo ID do cliente.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtém o nome do cliente. Mapeado para o elemento XML 'nome'.
     *
     * @return O nome do cliente.
     */
    @XmlElement
    public String getNome() {
        return nome;
    }

    /**
     * Define o nome do cliente.
     *
     * @param nome O novo nome do cliente.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Obtém o email do cliente. Mapeado para o elemento XML 'email'.
     *
     * @return O email do cliente.
     */
    @XmlElement
    public String getEmail() {
        return email;
    }

    /**
     * Define o email do cliente.
     *
     * @param email O novo email do cliente.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Obtém o endereço do cliente. Mapeado para o elemento XML 'endereco'.
     *
     * @return O endereço do cliente.
     */
    @XmlElement
    public String getEndereco() {
        return endereco;
    }

    /**
     * Define o endereço do cliente.
     *
     * @param endereco O novo endereço do cliente.
     */
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    /**
     * Obtém o telefone do cliente. Mapeado para o elemento XML 'telefone'.
     *
     * @return O telefone do cliente.
     */
    @XmlElement
    public String getTelefone() {
        return telefone;
    }

    /**
     * Define o telefone do cliente.
     *
     * @param telefone O novo telefone do cliente.
     */
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}