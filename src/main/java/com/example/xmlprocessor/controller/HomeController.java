package com.example.xmlprocessor.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controlador responsável pela página inicial da aplicação.
 * Mapeia a rota raiz ("/") para a view principal.
 */
@Controller
public class HomeController {

    /**
     * Mapeia a requisição GET para a raiz do site ("/") e retorna a página inicial.
     *
     * @return O nome da view a ser renderizada, neste caso, "index".
     */
    @GetMapping("/")
    public String home() {
        return "index";
    }
}