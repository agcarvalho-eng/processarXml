package com.example.xmlprocessor.controller;

import com.example.xmlprocessor.model.entity.Cliente;
import com.example.xmlprocessor.repository.ClienteRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cliente")
public class ClienteController {

    private final ClienteRepository clienteRepository;

    public ClienteController(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @GetMapping("/novo")
    public String showAddForm(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "cliente-form";
    }

    @GetMapping("/editar/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID de Cliente inválido: " + id));
        model.addAttribute("cliente", cliente);
        return "cliente-form";
    }

    @PostMapping("/salvar")
    public String saveCliente(@ModelAttribute Cliente cliente) {
        clienteRepository.save(cliente);
        return "redirect:/consulta";
    }

    @PostMapping("/deletar/{id}")
    public String deleteCliente(@PathVariable Long id) {
        clienteRepository.deleteById(id);
        return "redirect:/consulta";
    }
}
