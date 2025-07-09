package com.example.xmlprocessor.controller;

import com.example.xmlprocessor.model.entity.Cliente;
import com.example.xmlprocessor.model.entity.Produto;
import com.example.xmlprocessor.repository.ClienteRepository;
import com.example.xmlprocessor.repository.ProdutoRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/consulta")
public class ConsultaController {

    private final ProdutoRepository produtoRepository;
    private final ClienteRepository clienteRepository;

    public ConsultaController(ProdutoRepository produtoRepository,
                              ClienteRepository clienteRepository) {
        this.produtoRepository = produtoRepository;
        this.clienteRepository = clienteRepository;
    }

    @GetMapping
    public String showConsultaPage(Model model) {
        model.addAttribute("produtos", produtoRepository.findAll());
        model.addAttribute("clientes", clienteRepository.findAll());
        return "consulta";
    }

    @PostMapping("/delete/produto/{id}")
    public String deleteProduto(@PathVariable Long id) {
        produtoRepository.deleteById(id);
        return "redirect:/consulta";
    }

    @PostMapping("/delete/cliente/{id}")
    public String deleteCliente(@PathVariable Long id) {
        clienteRepository.deleteById(id);
        return "redirect:/consulta";
    }
}