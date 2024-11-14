package com.example.gratitud_challenge.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DatosCrearUsuario (
        @NotBlank(message = "El nombre de usuario no puede estar en blanco")
        String nombreUsuario,
        @NotBlank(message = "La contraseña no puede estar en blanco")
        String password,
        @NotBlank(message = "El email no puede estar en blanco")
        @Email(message = "El email debe tener un formato válido")
        String email
){
}
