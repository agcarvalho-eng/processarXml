package com.example.xmlprocessor.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

/**
 * Representa a entidade Produto no banco de dados.
 * Cada instância desta classe corresponde a uma linha na tabela "produtos".
 */
@Entity
@Table(name = "produtos")
public class Produto {

    /**
     * O identificador único para o produto. É gerado automaticamente pelo banco de dados.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * O nome do produto. É obrigatório e não pode ser nulo ou vazio.
     */
    @NotBlank(message = "O nome do produto é obrigatório.")
    @Column(nullable = false, length = 100)
    private String nome;

    /**
     * A descrição detalhada do produto. Pode ser um texto longo.
     */
    @Column(columnDefinition = "TEXT")
    private String descricao;

    /**
     * O preço do produto. É obrigatório e deve ser um valor positivo ou zero.
     */
    @NotNull(message = "O preço do produto é obrigatório.")
    @PositiveOrZero(message = "O preço deve ser positivo ou zero.")
    @Column(nullable = false)
    private Double preco;

    /**
     * A categoria à qual o produto pertence. É obrigatória.
     */
    @NotBlank(message = "A categoria do produto é obrigatória.")
    @Column(nullable = false, length = 50)
    private String categoria;

    /**
     * A quantidade do produto disponível em estoque. É obrigatória e não pode ser negativa.
     */
    @NotNull(message = "A quantidade em estoque é obrigatória.")
    @PositiveOrZero(message = "O estoque não pode ser negativo.")
    @Column(nullable = false)
    private Integer estoque;

    // Getters e Setters

    /**
     * Obtém o ID do produto.
     *
     * @return o ID do produto.
     */
    public Long getId() {
        return id;
    }

    /**
     * Define o ID do produto.
     *
     * @param id o novo ID para o produto.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtém o nome do produto.
     *
     * @return o nome do produto.
     */
    public String getNome() {
        return nome;
    }



    /**
     * Define o nome do produto.
     *
     * @param nome o novo nome para o produto.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Obtém a descrição do produto.
     *
     * @return a descrição do produto.
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * Define a descrição do produto.
     *
     * @param descricao a nova descrição para o produto.
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * Obtém o preço do produto.
     *
     * @return o preço do produto.
     */
    public Double getPreco() {
        return preco;
    }

    /**
     * Define o preço do produto.
     *
     * @param preco o novo preço para o produto.
     */
    public void setPreco(Double preco) {
        this.preco = preco;
    }

    /**
     * Obtém a categoria do produto.
     *
     * @return a categoria do produto.
     */
    public String getCategoria() {
        return categoria;
    }

    /**
     * Define a categoria do produto.
     *
     * @param categoria a nova categoria para o produto.
     */
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    /**
     * Obtém a quantidade em estoque do produto.
     *
     * @return a quantidade em estoque.
     */
    public Integer getEstoque() {
        return estoque;
    }

    /**
     * Define a quantidade em estoque do produto.
     *
     * @param estoque a nova quantidade em estoque.
     */
    public void setEstoque(Integer estoque) {
        this.estoque = estoque;
    }
}