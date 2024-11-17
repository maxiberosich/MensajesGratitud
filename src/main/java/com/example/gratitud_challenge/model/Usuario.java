package com.example.gratitud_challenge.model;

import com.example.gratitud_challenge.dto.usuario.DatosCrearUsuario;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;

    private String nombreUsuario;
    private String password;
    private String email;

    @OneToMany
    private List<Mensaje> listaMensajes;

    public Usuario(){
        this.nombreUsuario = "Raul";
        this.listaMensajes = null;
    }

    public Usuario(DatosCrearUsuario datos) {
        this.nombreUsuario = datos.nombreUsuario();
        this.password = datos.password();
        this.email = datos.email();
    }
}
