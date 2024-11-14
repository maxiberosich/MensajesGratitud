package com.example.gratitud_challenge.model;

import com.example.gratitud_challenge.dto.DatosCrearUsuario;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;

    private String nombreUsuario;
    private String password;
    private String email;

    public Usuario(DatosCrearUsuario datos) {
        this.nombreUsuario = datos.nombreUsuario();
        this.password = datos.password();
        this.email = datos.email();
    }
}
