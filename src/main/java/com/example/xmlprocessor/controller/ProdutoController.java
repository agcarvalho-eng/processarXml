package com.example.xmlprocessor.controller;

import com.example.xmlprocessor.model.entity.Produto;
import com.example.xmlprocessor.repository.ProdutoRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/produto")
public class ProdutoController {

    private final ProdutoRepository produtoRepository;

    public ProdutoController(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @GetMapping("/novo")
    public String showAddForm(Model model) {
        model.addAttribute("produto", new Produto());
        return "produto-form";
    }

    @GetMapping("/editar/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID de Produto inválido: " + id));
        model.addAttribute("produto", produto);
        return "produto-form";
    }

    @PostMapping("/salvar")
    public String saveProduto(@ModelAttribute Produto produto) {
        produtoRepository.save(produto);
        return "redirect:/consulta";
    }

    @PostMapping("/deletar/{id}")
    public String deleteProduto(@PathVariable Long id) {
        produtoRepository.deleteById(id);
        return "redirect:/consulta";
    }
}