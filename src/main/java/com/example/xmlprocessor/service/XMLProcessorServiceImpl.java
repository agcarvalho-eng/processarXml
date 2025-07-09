package com.example.xmlprocessor.service;

import com.example.xmlprocessor.model.entity.Cliente;
import com.example.xmlprocessor.model.entity.Produto;
import com.example.xmlprocessor.model.xml.ClienteWrapper;
import com.example.xmlprocessor.model.xml.DadosEmpresaWrapper;
import com.example.xmlprocessor.model.xml.ProdutoWrapper;
import com.example.xmlprocessor.repository.ClienteRepository;
import com.example.xmlprocessor.repository.ProdutoRepository;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class XMLProcessorServiceImpl implements XMLProcessorService {

    private final ProdutoRepository produtoRepository;
    private final ClienteRepository clienteRepository;

    public XMLProcessorServiceImpl(ProdutoRepository produtoRepository,
                                   ClienteRepository clienteRepository) {
        this.produtoRepository = produtoRepository;
        this.clienteRepository = clienteRepository;
    }

    @Override
    @Transactional
    public void processXMLFile(MultipartFile file) throws JAXBException, IOException {
        DadosEmpresaWrapper dadosEmpresa = parseXML(file);

        // MUDANÇA: Lógica de verificação de duplicatas para produtos
        List<ProdutoWrapper> produtosWrapper = dadosEmpresa.getProdutos();
        if (produtosWrapper != null && !produtosWrapper.isEmpty()) {
            List<Produto> novosProdutos = new ArrayList<>();
            for (ProdutoWrapper wrapper : produtosWrapper) {
                // Verifica se um produto com o mesmo nome já existe
                if (produtoRepository.findByNome(wrapper.getNome()).isEmpty()) {
                    Produto produto = convertToProdutoEntity(wrapper);
                    validateProduto(produto);
                    novosProdutos.add(produto);
                }
            }
            if (!novosProdutos.isEmpty()) {
                saveAllProdutos(novosProdutos);
            }
        }

        // MUDANÇA: Lógica de verificação de duplicatas para clientes
        List<ClienteWrapper> clientesWrapper = dadosEmpresa.getClientes();
        if (clientesWrapper != null && !clientesWrapper.isEmpty()) {
            List<Cliente> novosClientes = new ArrayList<>();
            for (ClienteWrapper wrapper : clientesWrapper) {
                // Verifica se um cliente com o mesmo e-mail já existe
                if (clienteRepository.findByEmail(wrapper.getEmail()).isEmpty()) {
                    novosClientes.add(convertToClienteEntity(wrapper));
                }
            }
            if (!novosClientes.isEmpty()) {
                saveAllClientes(novosClientes);
            }
        }
    }

    private void validateProduto(Produto produto) {
        if (produto.getCategoria() == null || produto.getCategoria().trim().isEmpty()) {
            throw new DataIntegrityViolationException("Categoria do produto não pode ser nula ou vazia");
        }
        if (produto.getPreco() == null || produto.getPreco() < 0) {
            throw new DataIntegrityViolationException("Preço do produto inválido");
        }
    }

    private Produto convertToProdutoEntity(ProdutoWrapper wrapper) {
        Produto produto = new Produto();
        produto.setNome(wrapper.getNome());
        produto.setDescricao(wrapper.getDescricao());
        produto.setPreco(wrapper.getPreco());
        produto.setCategoria(wrapper.getCategoria());
        produto.setEstoque(wrapper.getEstoque());
        return produto;
    }

    private Cliente convertToClienteEntity(ClienteWrapper wrapper) {
        Cliente cliente = new Cliente();
        cliente.setNome(wrapper.getNome());
        cliente.setEmail(wrapper.getEmail());
        cliente.setEndereco(wrapper.getEndereco());
        cliente.setTelefone(wrapper.getTelefone());
        return cliente;
    }

    @Override
    public DadosEmpresaWrapper parseXML(MultipartFile file) throws JAXBException, IOException {
        JAXBContext context = JAXBContext.newInstance(DadosEmpresaWrapper.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (InputStream inputStream = file.getInputStream()) {
            return (DadosEmpresaWrapper) unmarshaller.unmarshal(inputStream);
        }
    }

    @Override
    public List<Produto> saveAllProdutos(List<Produto> produtos) {
        return produtoRepository.saveAll(produtos);
    }

    @Override
    public List<Cliente> saveAllClientes(List<Cliente> clientes) {
        return clienteRepository.saveAll(clientes);
    }
}