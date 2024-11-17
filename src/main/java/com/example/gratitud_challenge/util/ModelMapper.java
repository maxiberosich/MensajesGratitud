package com.example.gratitud_challenge.util;

import com.example.gratitud_challenge.dto.mensaje.DatosMensaje;
import com.example.gratitud_challenge.model.Mensaje;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ModelMapper {

    public DatosMensaje convertirADatosMensaje(Mensaje mensaje){
        return new DatosMensaje(mensaje.getIdMensaje(), mensaje.getContenido(), mensaje.getPuntuacion(), LocalDateTime.now(), mensaje.getUsuario().getNombreUsuario());
    }

    public Mensaje convertirAMensaje(DatosMensaje datosMensaje){
        Mensaje mensaje = new Mensaje();
        mensaje.setIdMensaje(datosMensaje.idMensaje());
        mensaje.setContenido(datosMensaje.contenido());
        mensaje.setPuntuacion(datosMensaje.puntuacion());
        return mensaje;
    }

}
