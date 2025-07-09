package com.example.xmlprocessor.model.xml;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "DadosEmpresa")
public class DadosEmpresaWrapper {

    private List<ProdutoWrapper> produtos;
    private List<ClienteWrapper> clientes;

    // MUDANÇA: Adicionamos @XmlElementWrapper para identificar a tag <Produtos>
    // e @XmlElement para identificar cada <Produto> dentro dela.
    @XmlElementWrapper(name = "Produtos")
    @XmlElement(name = "Produto")
    public List<ProdutoWrapper> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<ProdutoWrapper> produtos) {
        this.produtos = produtos;
    }

    // MUDANÇA: Adicionamos @XmlElementWrapper para identificar a tag <Clientes>
    // e @XmlElement para identificar cada <Cliente> dentro dela.
    @XmlElementWrapper(name = "Clientes")
    @XmlElement(name = "Cliente")
    public List<ClienteWrapper> getClientes() {
        return clientes;
    }

    public void setClientes(List<ClienteWrapper> clientes) {
        this.clientes = clientes;
    }
}