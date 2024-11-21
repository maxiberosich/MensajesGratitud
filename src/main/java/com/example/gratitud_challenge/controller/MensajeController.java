package com.example.gratitud_challenge.controller;

import com.example.gratitud_challenge.dto.mensaje.DatosCrearMensaje;
import com.example.gratitud_challenge.dto.mensaje.DatosMensaje;
import com.example.gratitud_challenge.model.Mensaje;
import com.example.gratitud_challenge.service.MensajeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mensaje")
public class MensajeController {

    @Autowired
    private MensajeService mensajeService;

    @PostMapping("/{idUsuario}/crear-mensaje")
    public DatosMensaje crearMensaje(@PathVariable Long idUsuario, @RequestBody @Valid DatosCrearMensaje mensaje){
        return mensajeService.crearMensaje(idUsuario, mensaje);
    }

    @DeleteMapping("/{idMensaje}")
    public void borrarMensaje(@PathVariable Long idMensaje){
        String nombreUsuario = SecurityContextHolder.getContext().getAuthentication().getName();
        mensajeService.eliminarMensaje(idMensaje, nombreUsuario);
        System.out.println("Eliminado MENSAJE correctamente");
    }

    @GetMapping
    public List<DatosMensaje> todosLosMensajes(){
        return mensajeService.todosLosMensajes();
    }

    @GetMapping("/usuario/{idUsuario}")
    public List<DatosMensaje> obtenerMensajesPorIdUsuario(@PathVariable Long idUsuario) {
        return mensajeService.obtenerMensajePorIdUsuario(idUsuario);
    }

    @GetMapping("/usuario")
    public List<DatosMensaje> obtenerMensajesPorNombreUsuario(@RequestParam String nombreUsuario) {
        return mensajeService.obtenerMensajesPorNombreUsuario(nombreUsuario);
    }

    @PostMapping("/{id}/puntuar")
    public ResponseEntity<DatosMensaje> puntuarMensaje(@PathVariable Long id, @RequestParam Double puntuacion) {
        DatosMensaje mensajePuntuado = mensajeService.puntuarMensaje(id, puntuacion);
        return ResponseEntity.ok(mensajePuntuado);
    }

    @GetMapping("/orden/fecha")
    public List<DatosMensaje> obtenerMensajesPorFecha() {
        return mensajeService.obtenerMensajesPorFecha();
    }

    @GetMapping("/orden/puntuacion")
    public List<DatosMensaje> obtenerMensajesPorPuntuacion() {
        return mensajeService.obtenerMensajesPorPuntuacion();
    }


}
