package com.example.xmlprocessor.controller;

import com.example.xmlprocessor.model.entity.Cliente;
import com.example.xmlprocessor.repository.ClienteRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador responsável pelas operações relacionadas a Clientes.
 * Gerencia as requisições web para criar, editar, salvar e deletar clientes.
 */
@Controller
@RequestMapping("/cliente")
public class ClienteController {

    private final ClienteRepository clienteRepository;

    /**
     * Construtor para injeção de dependência do ClienteRepository.
     *
     * @param clienteRepository O repositório para acesso aos dados dos clientes.
     */
    public ClienteController(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    /**
     * Exibe o formulário para a criação de um novo cliente.
     *
     * @param model O modelo para adicionar atributos que serão usados na view.
     * @return O nome da view do formulário de cliente ("cliente-form").
     */
    @GetMapping("/novo")
    public String addForm(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "cliente-form";
    }

    /**
     * Exibe o formulário para editar um cliente existente.
     *
     * @param id O ID do cliente a ser editado.
     * @param model O modelo para adicionar o cliente encontrado que será usado na view.
     * @return O nome da view do formulário de cliente ("cliente-form").
     * @throws IllegalArgumentException se o ID do cliente for inválido.
     */
    @GetMapping("/editar/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID de Cliente inválido: " + id));
        model.addAttribute("cliente", cliente);
        return "cliente-form";
    }

    /**
     * Salva um cliente novo ou atualiza um existente.
     *
     * @param cliente O objeto cliente preenchido com os dados do formulário.
     * @return Um redirecionamento para a página de consulta de clientes.
     */
    @PostMapping("/salvar")
    public String saveCliente(@ModelAttribute Cliente cliente) {
        clienteRepository.save(cliente);
        return "redirect:/consulta";
    }

    /**
     * Deleta um cliente com base no seu ID.
     *
     * @param id O ID do cliente a ser deletado.
     * @return Um redirecionamento para a página de consulta de clientes.
     */
    @PostMapping("/deletar/{id}")
    public String deleteCliente(@PathVariable Long id) {
        clienteRepository.deleteById(id);
        return "redirect:/consulta";
    }
}
