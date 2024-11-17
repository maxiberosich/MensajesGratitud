package com.example.gratitud_challenge.dto.mensaje;

import java.time.LocalDateTime;

public record DatosMensaje(
        Long idMensaje,
        String contenido,
        Double puntuacion,
        LocalDateTime fechaCreacion,
        String nombreUsuario
) {
}
