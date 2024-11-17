package com.example.gratitud_challenge.repository;

import com.example.gratitud_challenge.dto.usuario.DatosPublicoUsuario;
import com.example.gratitud_challenge.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    DatosPublicoUsuario findByNombreUsuario(String nombre);
}
