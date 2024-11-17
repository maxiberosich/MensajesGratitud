package com.example.gratitud_challenge.repository;

import com.example.gratitud_challenge.model.Mensaje;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MensajeRepository extends JpaRepository<Mensaje, Long> {
}
