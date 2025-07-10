package com.example.xmlprocessor.controller;

import com.example.xmlprocessor.service.XMLProcessorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Controlador que gerencia o upload e processamento de arquivos XML.
 * Fornece endpoints para exibir a página de upload e para lidar com o arquivo enviado.
 */
@Controller
public class UploadController {

    private final XMLProcessorService xmlProcessorService;

    /**
     * Construtor para injeção de dependência do serviço de processamento XML.
     *
     * @param xmlProcessorService O serviço que encapsula a lógica de processamento do arquivo XML.
     */
    public UploadController(XMLProcessorService xmlProcessorService) {
        this.xmlProcessorService = xmlProcessorService;
    }

    /**
     * Exibe a página de upload de arquivos.
     *
     * @param model O modelo para adicionar atributos que serão usados na view.
     * @return O nome da view de upload ("upload").
     */
    @GetMapping("/upload")
    public String uploadPage(Model model) {
        model.addAttribute("title", "Upload de Arquivo XML");
        return "upload";
    }


    /**
     * Lida com o upload do arquivo XML. Valida o arquivo, o processa usando o
     * XMLProcessorService e fornece feedback ao usuário através de RedirectAttributes.
     *
     * @param file O arquivo MultipartFile enviado pelo formulário.
     * @param redirectAttributes Atributos para adicionar mensagens de feedback
     * após o redirecionamento.
     * @return Uma string de redirecionamento para a página inicial, onde a mensagem de status será exibida.
     */
    @PostMapping("/upload")
    public String handleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) {
        try {
            validarFile(file);
            xmlProcessorService.processXMLFile(file);

            redirectAttributes.addFlashAttribute("message",
                    "Arquivo processado e dados salvos com sucesso!");
            redirectAttributes.addFlashAttribute("alertType", "success");

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "Erro ao processar o arquivo: " + e.getMessage());
            redirectAttributes.addFlashAttribute("alertType", "danger");
        }

        return "redirect:/";
    }

    /**
     * Valida o arquivo recebido para garantir que não está vazio e que é um arquivo XML.
     *
     * @param file O arquivo MultipartFile a ser validado.
     * @throws IllegalArgumentException se o arquivo estiver vazio ou não for um arquivo XML válido.
     */
    private void validarFile(MultipartFile file) {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("Por favor, selecione um arquivo para upload.");
        }

        if (!isValidoXmlFile(file)) {
            throw new IllegalArgumentException("Por favor, envie apenas arquivos XML (.xml).");
        }
    }

    /**
     * Verifica se um MultipartFile é um arquivo XML válido, analisando seu
     * tipo de conteúdo (Content-Type) ou a extensão do nome do arquivo.
     *
     * @param file O arquivo a ser verificado.
     * @return {@code true} se for um arquivo XML, {@code false} caso contrário.
     */
    private boolean isValidoXmlFile(MultipartFile file) {
        String contentType = file.getContentType();
        String fileName = file.getOriginalFilename();

        return (contentType != null && (contentType.equals("application/xml") ||
                contentType.equals("text/xml"))) ||
                (fileName != null && fileName.toLowerCase().endsWith(".xml"));
    }
}