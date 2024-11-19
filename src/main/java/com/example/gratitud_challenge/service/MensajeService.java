package com.example.gratitud_challenge.service;

import com.example.gratitud_challenge.dto.mensaje.DatosCrearMensaje;
import com.example.gratitud_challenge.dto.mensaje.DatosMensaje;
import com.example.gratitud_challenge.model.Mensaje;
import com.example.gratitud_challenge.model.Usuario;
import com.example.gratitud_challenge.repository.MensajeRepository;
import com.example.gratitud_challenge.util.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class MensajeService {

    @Autowired
    private MensajeRepository mensajeRepository;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private ModelMapper modelMapper;

    public DatosMensaje crearMensaje(Long idUsuario, DatosCrearMensaje datos) {
        Usuario usuario = usuarioService.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException(("Usuario no encontrado")));

        Mensaje mensaje = new Mensaje(usuario, datos.contenido());
        mensajeRepository.save(mensaje);

        return modelMapper.convertirADatosMensaje(mensaje);
    }

    public void eliminarMensaje(Long idMensaje, String nombreUsuario) {
        Mensaje mensaje = mensajeRepository.findById(idMensaje)
                .orElseThrow(() -> new RuntimeException(("Id de mensaje no encontrado")));

        Usuario usuario = usuarioService.buscarUsuarioPorNombre(nombreUsuario);
        if (usuario == null || !mensaje.getUsuario().getIdUsuario().equals(usuario.getIdUsuario())) {
            throw new RuntimeException("No tienes permiso para eliminar este mensaje");
        }
        mensajeRepository.delete(mensaje);

    }

    public List<DatosMensaje> todosLosMensajes() {
        List<Mensaje> mensajes = mensajeRepository.findAll();
        return mensajes.stream()
                .map(modelMapper::convertirADatosMensaje)
                .collect(Collectors.toList());
    }

    public List<DatosMensaje> obtenerMensajePorIdUsuario(Long idUsuario){
        Usuario usuario = usuarioService.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        List<Mensaje> mensajes = mensajeRepository.findByUsuario(usuario);
        return mensajes.stream()
                .map(modelMapper::convertirADatosMensaje)
                .collect(Collectors.toList());
    }

    public List<DatosMensaje> obtenerMensajesPorNombreUsuario(String nombreUsuario) {
        Usuario usuario = usuarioService.buscarUsuarioPorNombre(nombreUsuario);
        if (usuario == null) {
            throw new RuntimeException("Usuario no encontrado");
        }
        List<Mensaje> mensajes = mensajeRepository.findByUsuario(usuario);
        return mensajes.stream()
                .map(modelMapper::convertirADatosMensaje)
                .collect(Collectors.toList());
    }


}
