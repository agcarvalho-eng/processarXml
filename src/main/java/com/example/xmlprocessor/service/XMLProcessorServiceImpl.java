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

/**
 * Implementação do serviço {@link XMLProcessorService}.
 * Contém a lógica de negócio para processar arquivos XML, incluindo a análise (parsing),
 * conversão de dados, validação e persistência no banco de dados, evitando duplicatas.
 */
@Service
public class XMLProcessorServiceImpl implements XMLProcessorService {

    private final ProdutoRepository produtoRepository;
    private final ClienteRepository clienteRepository;

    /**
     * Construtor para injeção de dependências dos repositórios.
     *
     * @param produtoRepository Repositório para operações de dados com Produtos.
     * @param clienteRepository Repositório para operações de dados com Clientes.
     */
    public XMLProcessorServiceImpl(ProdutoRepository produtoRepository,
                                   ClienteRepository clienteRepository) {
        this.produtoRepository = produtoRepository;
        this.clienteRepository = clienteRepository;
    }

    /**
     * Orquestra o processamento completo de um arquivo XML. Este método é transacional.
     * Ele lê o arquivo, verifica a existência de produtos e clientes para evitar duplicatas,
     * converte os dados para entidades e os salva no banco de dados.
     *
     * @param file O arquivo XML enviado pelo usuário.
     * @throws JAXBException Se houver um erro na desserialização do XML.
     * @throws IOException Se ocorrer um erro de I/O ao ler o arquivo.
     */
    @Override
    @Transactional
    public void processXMLFile(MultipartFile file) throws JAXBException, IOException {
        DadosEmpresaWrapper dadosEmpresa = parseXML(file);

        // Processa e salva novos produtos, evitando duplicatas por nome.
        List<ProdutoWrapper> produtosWrapper = dadosEmpresa.getProdutos();
        if (produtosWrapper != null && !produtosWrapper.isEmpty()) {
            List<Produto> novosProdutos = new ArrayList<>();
            for (ProdutoWrapper wrapper : produtosWrapper) {
                if (produtoRepository.findByNome(wrapper.getNome()).isEmpty()) {
                    Produto produto = converterParaProdutoEntity(wrapper);
                    validarProduto(produto); // Valida os dados do produto.
                    novosProdutos.add(produto);
                }
            }
            if (!novosProdutos.isEmpty()) {
                saveTodosProdutos(novosProdutos);
            }
        }

        // Processa e salva novos clientes, evitando duplicatas por e-mail.
        List<ClienteWrapper> clientesWrapper = dadosEmpresa.getClientes();
        if (clientesWrapper != null && !clientesWrapper.isEmpty()) {
            List<Cliente> novosClientes = new ArrayList<>();
            for (ClienteWrapper wrapper : clientesWrapper) {
                if (clienteRepository.findByEmail(wrapper.getEmail()).isEmpty()) {
                    novosClientes.add(converterParaClienteEntity(wrapper));
                }
            }
            if (!novosClientes.isEmpty()) {
                saveTodosClientes(novosClientes);
            }
        }
    }

    /**
     * Realiza validações de regras de negócio em uma entidade Produto antes da persistência.
     *
     * @param produto A entidade Produto a ser validada.
     * @throws DataIntegrityViolationException se alguma regra de negócio for violada.
     */
    private void validarProduto(Produto produto) {
        if (produto.getCategoria() == null || produto.getCategoria().trim().isEmpty()) {
            throw new DataIntegrityViolationException("Categoria do produto não pode ser nula ou vazia");
        }
        if (produto.getPreco() == null || produto.getPreco() < 0) {
            throw new DataIntegrityViolationException("Preço do produto inválido");
        }
    }

    /**
     * Converte um objeto {@link ProdutoWrapper} (DTO do XML) para uma entidade {@link Produto}.
     *
     * @param wrapper O objeto com dados do XML.
     * @return A entidade Produto pronta para ser persistida.
     */
    private Produto converterParaProdutoEntity(ProdutoWrapper wrapper) {
        Produto produto = new Produto();
        produto.setNome(wrapper.getNome());
        produto.setDescricao(wrapper.getDescricao());
        produto.setPreco(wrapper.getPreco());
        produto.setCategoria(wrapper.getCategoria());
        produto.setEstoque(wrapper.getEstoque());
        return produto;
    }

    /**
     * Converte um objeto {@link ClienteWrapper} (DTO do XML) para uma entidade {@link Cliente}.
     *
     * @param wrapper O objeto com dados do XML.
     * @return A entidade Cliente pronta para ser persistida.
     */
    private Cliente converterParaClienteEntity(ClienteWrapper wrapper) {
        Cliente cliente = new Cliente();
        cliente.setNome(wrapper.getNome());
        cliente.setEmail(wrapper.getEmail());
        cliente.setEndereco(wrapper.getEndereco());
        cliente.setTelefone(wrapper.getTelefone());
        return cliente;
    }

    /**
     * Analisa (parse) um arquivo XML usando JAXB para convertê-lo em um objeto {@link DadosEmpresaWrapper}.
     *
     * @param file O arquivo XML a ser analisado.
     * @return O objeto wrapper contendo os dados do XML.
     * @throws JAXBException Se houver um erro na desserialização do XML.
     * @throws IOException Se ocorrer um erro de I/O ao ler o stream do arquivo.
     */
    @Override
    public DadosEmpresaWrapper parseXML(MultipartFile file) throws JAXBException, IOException {
        JAXBContext context = JAXBContext.newInstance(DadosEmpresaWrapper.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (InputStream inputStream = file.getInputStream()) {
            return (DadosEmpresaWrapper) unmarshaller.unmarshal(inputStream);
        }
    }

    /**
     * Salva uma lista de entidades {@link Produto} no banco de dados de uma só vez.
     *
     * @param produtos A lista de produtos a serem salvos.
     * @return A lista de produtos salvos, com seus IDs gerados.
     */
    @Override
    public List<Produto> saveTodosProdutos(List<Produto> produtos) {
        return produtoRepository.saveAll(produtos);
    }

    /**
     * Salva uma lista de entidades {@link Cliente} no banco de dados de uma só vez.
     *
     * @param clientes A lista de clientes a serem salvos.
     * @return A lista de clientes salvos, com seus IDs gerados.
     */
    @Override
    public List<Cliente> saveTodosClientes(List<Cliente> clientes) {
        return clienteRepository.saveAll(clientes);
    }
}