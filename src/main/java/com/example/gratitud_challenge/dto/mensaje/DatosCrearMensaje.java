package com.example.gratitud_challenge.dto.mensaje;

import jakarta.validation.constraints.NotBlank;

public record DatosCrearMensaje(
        @NotBlank(message = "El mensaje no puede estar en blanco")
        String contenido
) {
}
