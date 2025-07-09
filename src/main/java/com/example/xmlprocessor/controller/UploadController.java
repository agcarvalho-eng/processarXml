package com.example.xmlprocessor.controller;

import com.example.xmlprocessor.service.XMLProcessorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.xml.bind.JAXBException;
import java.io.IOException;

@Controller
public class UploadController {

    private final XMLProcessorService xmlProcessorService;

    public UploadController(XMLProcessorService xmlProcessorService) {
        this.xmlProcessorService = xmlProcessorService;
    }

    @GetMapping("/upload")
    public String showUploadPage(Model model) {
        model.addAttribute("title", "Upload de Arquivo XML");
        return "upload";
    }


    @PostMapping("/upload")
    public String handleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) {
        try {
            validateFile(file);
            xmlProcessorService.processXMLFile(file);

            redirectAttributes.addFlashAttribute("message",
                    "Arquivo processado e dados salvos com sucesso!");
            redirectAttributes.addFlashAttribute("alertType", "success");

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "Erro ao processar o arquivo: " + e.getMessage());
            redirectAttributes.addFlashAttribute("alertType", "danger");
        }

        // MUDANÇA: Redireciona para a página inicial para exibir o status.
        return "redirect:/";
    }

    private void validateFile(MultipartFile file) {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("Por favor, selecione um arquivo para upload.");
        }

        if (!isValidXmlFile(file)) {
            throw new IllegalArgumentException("Por favor, envie apenas arquivos XML (.xml).");
        }
    }

    private boolean isValidXmlFile(MultipartFile file) {
        String contentType = file.getContentType();
        String fileName = file.getOriginalFilename();

        return (contentType != null && (contentType.equals("application/xml") ||
                contentType.equals("text/xml"))) ||
                (fileName != null && fileName.toLowerCase().endsWith(".xml"));
    }
}