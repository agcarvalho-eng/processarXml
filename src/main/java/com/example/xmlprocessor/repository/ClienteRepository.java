package com.example.xmlprocessor.repository;

import com.example.xmlprocessor.model.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional; // Importe o Optional

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    // Método para buscar um cliente pelo e-mail.
    Optional<Cliente> findByEmail(String email);
}