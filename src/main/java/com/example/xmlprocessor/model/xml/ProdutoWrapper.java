package com.example.xmlprocessor.model.xml;

import jakarta.xml.bind.annotation.XmlElement;

import java.math.BigDecimal;

/**
 * Classe wrapper (invólucro) para o mapeamento de dados de um produto a partir de um arquivo XML.
 * É usada pelo JAXB para deserializar os elementos XML de um produto em um objeto Java.
 * Esta classe funciona como um Objeto de Transferência de Dados (DTO) e não é uma entidade de banco de dados.
 */
public class ProdutoWrapper {
    /**
     * O ID do produto.
     */
    private Long id;
    /**
     * O nome do produto.
     */
    private String nome;
    /**
     * A descrição do produto.
     */
    private String descricao;
    /**
     * O preço do produto.
     */
    private Double preco;
    /**
     * A categoria do produto.
     */
    private String categoria;
    /**
     * A quantidade em estoque do produto.
     */
    private Integer estoque;

    /**
     * Obtém o ID do produto. Mapeado para o elemento XML 'id'.
     *
     * @return O ID do produto.
     */
    @XmlElement
    public Long getId() {
        return id;
    }

    /**
     * Define o ID do produto.
     *
     * @param id O novo ID para o produto.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtém o nome do produto. Mapeado para o elemento XML 'nome'.
     *
     * @return O nome do produto.
     */
    @XmlElement
    public String getNome() {
        return nome;
    }

    /**
     * Define o nome do produto.
     *
     * @param nome O novo nome para o produto.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Obtém a descrição do produto. Mapeado para o elemento XML 'descricao'.
     *
     * @return A descrição do produto.
     */
    @XmlElement
    public String getDescricao() {
        return descricao;
    }

    /**
     * Define a descrição do produto.
     *
     * @param descricao A nova descrição para o produto.
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * Obtém o preço do produto. Mapeado para o elemento XML 'preco'.
     *
     * @return O preço do produto.
     */
    @XmlElement
    public Double getPreco() {
        return preco;
    }

    /**
     * Define o preço do produto.
     *
     * @param preco O novo preço para o produto.
     */
    public void setPreco(Double preco) {
        this.preco = preco;
    }

    /**
     * Obtém a categoria do produto. Mapeado para o elemento XML 'categoria'.
     *
     * @return A categoria do produto.
     */
    @XmlElement
    public String getCategoria() {
        return categoria;
    }

    /**
     * Define a categoria do produto.
     *
     * @param categoria A nova categoria para o produto.
     */
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    /**
     * Obtém a quantidade em estoque do produto. Mapeado para o elemento XML 'estoque'.
     *
     * @return A quantidade em estoque.
     */
    @XmlElement
    public Integer getEstoque() {
        return estoque;
    }

    /**
     * Define a quantidade em estoque do produto.
     *
     * @param estoque A nova quantidade em estoque.
     */
    public void setEstoque(Integer estoque) {
        this.estoque = estoque;
    }
}