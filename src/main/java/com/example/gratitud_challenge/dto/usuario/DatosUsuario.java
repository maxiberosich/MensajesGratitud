package com.example.gratitud_challenge.dto.usuario;

import com.example.gratitud_challenge.dto.mensaje.DatosMensaje;
import com.example.gratitud_challenge.model.Mensaje;
import jakarta.persistence.OneToMany;

import java.util.List;

public record DatosUsuario(
        Long idUsuario,
        String nombreUsuario,
        String email,
        List<DatosMensaje>listaMensajes
) {
}
