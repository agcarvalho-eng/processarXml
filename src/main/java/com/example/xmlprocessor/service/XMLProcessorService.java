package com.example.xmlprocessor.service;

import com.example.xmlprocessor.model.entity.Cliente;
import com.example.xmlprocessor.model.entity.Produto;
import com.example.xmlprocessor.model.xml.DadosEmpresaWrapper;
import org.springframework.web.multipart.MultipartFile;

import jakarta.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;

/**
 * Interface que define o contrato para o serviço de processamento de arquivos XML.
 * Descreve as operações de alto nível para ler, analisar (parse) e persistir
 * os dados contidos em um arquivo XML.
 */
public interface XMLProcessorService {

    /**
     * Processa um arquivo XML completo. Orquestra a análise do XML
     * e a subsequente persistência dos dados de produtos e clientes no banco de dados.
     *
     * @param file O arquivo XML enviado como {@link MultipartFile}.
     * @throws JAXBException Se ocorrer um erro durante a desserialização do XML (unmarshalling).
     * @throws IOException Se ocorrer um erro de I/O ao ler o arquivo.
     */
    void processXMLFile(MultipartFile file) throws JAXBException, IOException;

    /**
     * Analisa (parse) um arquivo XML e o converte em um objeto {@link DadosEmpresaWrapper}.
     * Este método é responsável pela desserialização (unmarshalling) do conteúdo do arquivo.
     *
     * @param file O arquivo XML a ser analisado.
     * @return Um objeto {@link DadosEmpresaWrapper} contendo os dados extraídos do XML.
     * @throws JAXBException Se ocorrer um erro durante a desserialização do XML.
     * @throws IOException Se ocorrer um erro de I/O ao ler o arquivo.
     */
    DadosEmpresaWrapper parseXML(MultipartFile file) throws JAXBException, IOException;

    /**
     * Salva uma lista de entidades {@link Produto} no banco de dados.
     *
     * @param produtos A lista de produtos a ser salva.
     * @return A lista de produtos que foi salva, possivelmente com IDs atualizados.
     */
    List<Produto> saveTodosProdutos(List<Produto> produtos);

    /**
     * Salva uma lista de entidades {@link Cliente} no banco de dados.
     *
     * @param clientes A lista de clientes a ser salva.
     * @return A lista de clientes que foi salva, possivelmente com IDs atualizados.
     */
    List<Cliente> saveTodosClientes(List<Cliente> clientes);
}