package com.example.xmlprocessor.model.xml;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Classe wrapper que representa o elemento raiz <DadosEmpresa> em um arquivo XML.
 * Esta classe é o ponto de entrada para o processo de unmarshalling (desserialização)
 * do XML e contém listas de produtos e clientes.
 */
@XmlRootElement(name = "DadosEmpresa")
public class DadosEmpresaWrapper {

    /**
     * Uma lista de wrappers de produtos extraídos do XML.
     */
    private List<ProdutoWrapper> produtos;
    /**
     * Uma lista de wrappers de clientes extraídos do XML.
     */
    private List<ClienteWrapper> clientes;

    /**
     * Obtém a lista de produtos.
     * A anotação {@code @XmlElementWrapper(name = "Produtos")} indica que a lista de produtos
     * no XML está contida dentro de uma tag <Produtos>.
     * A anotação {@code @XmlElement(name = "Produto")} especifica que cada item na lista
     * corresponde a uma tag <Produto>.
     *
     * @return a lista de {@link ProdutoWrapper}.
     */
    @XmlElementWrapper(name = "Produtos")
    @XmlElement(name = "Produto")
    public List<ProdutoWrapper> getProdutos() {
        return produtos;
    }

    /**
     * Define a lista de produtos.
     *
     * @param produtos a nova lista de {@link ProdutoWrapper}.
     */
    public void setProdutos(List<ProdutoWrapper> produtos) {
        this.produtos = produtos;
    }

    /**
     * Obtém a lista de clientes.
     * A anotação {@code @XmlElementWrapper(name = "Clientes")} indica que a lista de clientes
     * no XML está contida dentro de uma tag <Clientes>.
     * A anotação {@code @XmlElement(name = "Cliente")} especifica que cada item na lista
     * corresponde a uma tag <Cliente>.
     *
     * @return a lista de {@link ClienteWrapper}.
     */
    @XmlElementWrapper(name = "Clientes")
    @XmlElement(name = "Cliente")
    public List<ClienteWrapper> getClientes() {
        return clientes;
    }

    /**
     * Define a lista de clientes.
     *
     * @param clientes a nova lista de {@link ClienteWrapper}.
     */
    public void setClientes(List<ClienteWrapper> clientes) {
        this.clientes = clientes;
    }
}