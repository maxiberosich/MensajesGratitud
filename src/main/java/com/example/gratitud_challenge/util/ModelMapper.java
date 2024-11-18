package com.example.gratitud_challenge.util;

import com.example.gratitud_challenge.dto.mensaje.DatosMensaje;
import com.example.gratitud_challenge.dto.usuario.DatosPublicoUsuario;
import com.example.gratitud_challenge.model.Mensaje;
import com.example.gratitud_challenge.model.Usuario;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ModelMapper {

    public DatosPublicoUsuario convertirADatosUsuario(Usuario usuario) {
        DatosPublicoUsuario usuarioDTO = new DatosPublicoUsuario(usuario.getNombreUsuario());
        return usuarioDTO;
    }

    public Usuario convertirAUsuario(DatosPublicoUsuario usuarioDTO) {
        Usuario usuario = new Usuario();
        usuario.setNombreUsuario(usuarioDTO.nombreUsuario());
        return usuario;
    }

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
