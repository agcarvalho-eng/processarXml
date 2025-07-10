package com.example.xmlprocessor.controller;

import com.example.xmlprocessor.model.entity.Produto;
import com.example.xmlprocessor.repository.ProdutoRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador para as operações CRUD (Criar, Ler, Atualizar, Deletar) de Produtos.
 * Gerencia as requisições web para adicionar, editar, salvar e remover produtos.
 */
@Controller
@RequestMapping("/produto")
public class ProdutoController {

    private final ProdutoRepository produtoRepository;

    /**
     * Construtor do controlador de produtos com injeção de dependência do repositório.
     *
     * @param produtoRepository O repositório para acesso aos dados dos produtos.
     */
    public ProdutoController(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    /**
     * Exibe o formulário para adicionar um novo produto.
     *
     * @param model O modelo para adicionar o novo objeto Produto, que será preenchido pelo formulário.
     * @return O nome da view do formulário de produto ("produto-form").
     */
    @GetMapping("/novo")
    public String addForm(Model model) {
        model.addAttribute("produto", new Produto());
        return "produto-form";
    }

    /**
     * Exibe o formulário para editar um produto existente.
     *
     * @param id O ID do produto a ser editado.
     * @param model O modelo para adicionar o produto encontrado, que será usado para preencher o formulário.
     * @return O nome da view do formulário de produto ("produto-form").
     * @throws IllegalArgumentException se o ID do produto não for encontrado.
     */
    @GetMapping("/editar/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID de Produto inválido: " + id));
        model.addAttribute("produto", produto);
        return "produto-form";
    }

    /**
     * Salva um novo produto ou atualiza um existente.
     * Recebe os dados do produto do formulário através do @ModelAttribute.
     *
     * @param produto O objeto Produto a ser salvo no banco de dados.
     * @return Um redirecionamento para a página de consulta.
     */
    @PostMapping("/salvar")
    public String saveProduto(@ModelAttribute Produto produto) {
        produtoRepository.save(produto);
        return "redirect:/consulta";
    }


    /**
     * Deleta um produto com base no seu ID.
     *
     * @param id O ID do produto a ser deletado.
     * @return Um redirecionamento para a página de consulta.
     */
    @PostMapping("/deletar/{id}")
    public String deleteProduto(@PathVariable Long id) {
        produtoRepository.deleteById(id);
        return "redirect:/consulta";
    }
}