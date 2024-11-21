package com.example.gratitud_challenge.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Mensaje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMensaje;

    private String contenido;
    private Double puntuacion;
    private LocalDateTime fechaCreacion;

    private Integer numeroPuntuacion;
    private Double totalPuntuacion;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    public Mensaje(){
        this.puntuacion = 0.0;
        this.fechaCreacion = LocalDateTime.now();
    }

    public Mensaje(Usuario usuario, String contenidoMensaje) {
        this.contenido = contenidoMensaje;
        this.puntuacion = 0.0;
        this.fechaCreacion = LocalDateTime.now();
        this.usuario = usuario;
    }
}
