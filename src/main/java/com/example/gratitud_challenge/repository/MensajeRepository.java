package com.example.gratitud_challenge.repository;

import com.example.gratitud_challenge.model.Mensaje;
import com.example.gratitud_challenge.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MensajeRepository extends JpaRepository<Mensaje, Long> {
    List<Mensaje> findByUsuario(Usuario usuario);
    List<Mensaje> findAllByOrderByFechaCreacionDesc();
    List<Mensaje> findAllByOrderByPuntuacionDesc();
}
