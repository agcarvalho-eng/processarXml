package com.example.xmlprocessor.service;

import com.example.xmlprocessor.model.entity.Cliente;
import com.example.xmlprocessor.model.entity.Produto;
import com.example.xmlprocessor.model.xml.DadosEmpresaWrapper;
import org.springframework.web.multipart.MultipartFile;

import jakarta.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;

public interface XMLProcessorService {
    void processXMLFile(MultipartFile file) throws JAXBException, IOException;
    DadosEmpresaWrapper parseXML(MultipartFile file) throws JAXBException, IOException;
    List<Produto> saveAllProdutos(List<Produto> produtos);
    List<Cliente> saveAllClientes(List<Cliente> clientes);
}