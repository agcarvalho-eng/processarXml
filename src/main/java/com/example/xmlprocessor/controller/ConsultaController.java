package com.example.xmlprocessor.controller;

import com.example.xmlprocessor.repository.ClienteRepository;
import com.example.xmlprocessor.repository.ProdutoRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador para gerenciar a página de consulta.
 * Exibe listas de produtos e clientes e permite a exclusão de registros.
 */
@Controller
@RequestMapping("/consulta")
public class ConsultaController {

    private final ProdutoRepository produtoRepository;
    private final ClienteRepository clienteRepository;

    /**
     * Construtor para injeção de dependências dos repositórios de Produto e Cliente.
     *
     * @param produtoRepository O repositório para acesso aos dados dos produtos.
     * @param clienteRepository O repositório para acesso aos dados dos clientes.
     */
    public ConsultaController(ProdutoRepository produtoRepository,
                              ClienteRepository clienteRepository) {
        this.produtoRepository = produtoRepository;
        this.clienteRepository = clienteRepository;
    }

    /**
     * Prepara e exibe a página principal de consulta.
     * Adiciona as listas de todos os produtos e clientes ao modelo para serem exibidas na view.
     *
     * @param model O modelo para adicionar atributos que serão usados na view.
     * @return O nome da view de consulta ("consulta").
     */
    @GetMapping
    public String consultaPage(Model model) {
        model.addAttribute("produtos", produtoRepository.findAll());
        model.addAttribute("clientes", clienteRepository.findAll());
        return "consulta";
    }

    /**
     * Deleta um produto com base no seu ID.
     *
     * @param id O ID do produto a ser deletado.
     * @return Um redirecionamento de volta para a página de consulta.
     */
    @PostMapping("/delete/produto/{id}")
    public String deleteProduto(@PathVariable Long id) {
        produtoRepository.deleteById(id);
        return "redirect:/consulta";
    }

    /**
     * Deleta um cliente com base no seu ID.
     *
     * @param id O ID do cliente a ser deletado.
     * @return Um redirecionamento de volta para a página de consulta.
     */
    @PostMapping("/delete/cliente/{id}")
    public String deleteCliente(@PathVariable Long id) {
        clienteRepository.deleteById(id);
        return "redirect:/consulta";
    }
}