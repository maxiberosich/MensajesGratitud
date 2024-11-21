package com.example.gratitud_challenge.util;

import com.example.gratitud_challenge.dto.mensaje.DatosMensaje;
import com.example.gratitud_challenge.dto.usuario.DatosPublicoUsuario;
import com.example.gratitud_challenge.dto.usuario.DatosUsuario;
import com.example.gratitud_challenge.model.Mensaje;
import com.example.gratitud_challenge.model.Usuario;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Component
public class ModelMapper {

    public DatosUsuario convertirADatosUsuario(Usuario usuario) {
        DatosUsuario datosUsuarioDTO = new DatosUsuario(usuario.getIdUsuario(), usuario.getNombreUsuario(),
                usuario.getEmail(), usuario.getListaMensajes().stream()
                .map(this::convertirADatosMensaje).collect(Collectors.toList()));
        return datosUsuarioDTO;
    }

    public DatosPublicoUsuario convertirADatosPublicoUsuario(Usuario usuario) {
        DatosPublicoUsuario datosUsuarioDTO = new DatosPublicoUsuario(usuario.getIdUsuario(),
                usuario.getNombreUsuario());
        return datosUsuarioDTO;
    }

    public Usuario convertirAUsuario(DatosUsuario datosUsuarioDTO) {
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(datosUsuarioDTO.idUsuario());
        usuario.setNombreUsuario(datosUsuarioDTO.nombreUsuario());
        usuario.setEmail(datosUsuarioDTO.email());
        usuario.setListaMensajes(datosUsuarioDTO.listaMensajes().stream()
                .map(this::convertirAMensaje)
                .collect(Collectors.toList()));
        return usuario;
    }

    public DatosMensaje convertirADatosMensaje(Mensaje mensaje){
        return new DatosMensaje(
                mensaje.getIdMensaje(),
                mensaje.getContenido(),
                mensaje.getPuntuacion(),
                LocalDateTime.now(),
                mensaje.getUsuario().getNombreUsuario());
    }

    public Mensaje convertirAMensaje(DatosMensaje datosMensaje){
        Mensaje mensaje = new Mensaje();
        mensaje.setIdMensaje(datosMensaje.idMensaje());
        mensaje.setContenido(datosMensaje.contenido());
        mensaje.setPuntuacion(datosMensaje.puntuacion());
        return mensaje;
    }

}
