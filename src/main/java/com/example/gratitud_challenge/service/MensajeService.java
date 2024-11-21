package com.example.gratitud_challenge.service;

import com.example.gratitud_challenge.dto.mensaje.DatosCrearMensaje;
import com.example.gratitud_challenge.dto.mensaje.DatosMensaje;
import com.example.gratitud_challenge.dto.usuario.DatosUsuario;
import com.example.gratitud_challenge.exception.ValidacionDeIntegridad;
import com.example.gratitud_challenge.model.Mensaje;
import com.example.gratitud_challenge.model.Usuario;
import com.example.gratitud_challenge.repository.MensajeRepository;
import com.example.gratitud_challenge.util.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
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
        DatosUsuario datosUsuario = usuarioService.buscarUsuarioPorID(idUsuario);

        Mensaje mensaje = new Mensaje(modelMapper.convertirAUsuario(datosUsuario), datos.contenido());
        mensajeRepository.save(mensaje);

        return modelMapper.convertirADatosMensaje(mensaje);
    }

    public void eliminarMensaje(Long idMensaje, String nombreUsuario) {
        Mensaje mensaje = mensajeRepository.findById(idMensaje)
                .orElseThrow(() -> new RuntimeException(("Id de mensaje no encontrado")));

        com.example.gratitud_challenge.dto.usuario.DatosUsuario datosUsuario = usuarioService.buscarUsuarioPorNombre(nombreUsuario);
        if (datosUsuario == null || !mensaje.getUsuario().getIdUsuario().equals(datosUsuario.idUsuario())) {
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
        DatosUsuario usuario = usuarioService.buscarUsuarioPorID(idUsuario);
        List<Mensaje> mensajes = mensajeRepository.findByUsuario(modelMapper.convertirAUsuario(usuario));
        return mensajes.stream()
                .map(modelMapper::convertirADatosMensaje)
                .collect(Collectors.toList());
    }

    public List<DatosMensaje> obtenerMensajesPorNombreUsuario(String nombreUsuario) {
        DatosUsuario usuario = usuarioService.buscarUsuarioPorNombre(nombreUsuario);
        if (usuario == null) {
            throw new RuntimeException("Usuario no encontrado");
        }
        List<Mensaje> mensajes = mensajeRepository.findByUsuario(modelMapper.convertirAUsuario(usuario));
        return mensajes.stream()
                .map(modelMapper::convertirADatosMensaje)
                .collect(Collectors.toList());
    }

    public DatosMensaje puntuarMensaje(Long id, Double puntuacion) {
        Mensaje mensaje = mensajeRepository.findById(id)
                .orElseThrow(() -> new ValidacionDeIntegridad("Mensaje no encontrado"));

        mensaje.setNumeroPuntuacion(mensaje.getNumeroPuntuacion() + 1);
        mensaje.setTotalPuntuacion(mensaje.getTotalPuntuacion() + puntuacion);

        Double promedio = mensaje.getTotalPuntuacion() / mensaje.getNumeroPuntuacion();
        mensaje.setPuntuacion(promedio);

        mensajeRepository.save(mensaje);
        return new DatosMensaje(mensaje.getIdMensaje(), mensaje.getContenido(), mensaje.getPuntuacion(), mensaje.getFechaCreacion(), mensaje.getUsuario().getNombreUsuario());
    }

    public List<DatosMensaje> obtenerMensajesPorFecha() {
        return mensajeRepository.findAllByOrderByFechaCreacionDesc().stream()
                .map(mensaje -> new DatosMensaje(mensaje.getIdMensaje(), mensaje.getContenido(), mensaje.getPuntuacion(), mensaje.getFechaCreacion(), mensaje.getUsuario().getNombreUsuario()))
                .collect(Collectors.toList());
    }

    public List<DatosMensaje> obtenerMensajesPorPuntuacion() {
        return mensajeRepository.findAllByOrderByPuntuacionDesc().stream()
                .map(mensaje -> new DatosMensaje(mensaje.getIdMensaje(), mensaje.getContenido(), mensaje.getPuntuacion(), mensaje.getFechaCreacion(), mensaje.getUsuario().getNombreUsuario()))
                .collect(Collectors.toList());
    }

}
