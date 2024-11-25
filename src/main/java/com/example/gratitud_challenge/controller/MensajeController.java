package com.example.gratitud_challenge.controller;

import com.example.gratitud_challenge.dto.mensaje.DatosCrearMensaje;
import com.example.gratitud_challenge.dto.mensaje.DatosMensaje;
import com.example.gratitud_challenge.exception.ValidacionDeIntegridad;
import com.example.gratitud_challenge.model.Mensaje;
import com.example.gratitud_challenge.service.MensajeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
    @Operation(summary = "Crear un nuevo mensaje", responses = {
            @ApiResponse(responseCode = "201", description = "Mensaje creado correctamente"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos"),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    public DatosMensaje crearMensaje(
            @Parameter(description = "ID del usuario que va a crear el mensaje", required = true)
            @PathVariable Long idUsuario,
            @RequestBody @Valid DatosCrearMensaje mensaje){
        return mensajeService.crearMensaje(idUsuario, mensaje);
    }

    @DeleteMapping("/{idMensaje}")
    @Operation(summary = "Eliminar un mensaje por ID", responses = {
            @ApiResponse(responseCode = "200", description = "Mensaje eliminado correctamente"),
            @ApiResponse(responseCode = "404", description = "Mensaje no encontrado")
    })
    public void borrarMensaje(
            @Parameter(description = "ID del mensaje a eliminar", required = true)
            @PathVariable Long idMensaje){
        String nombreUsuario = SecurityContextHolder.getContext().getAuthentication().getName();
        mensajeService.eliminarMensaje(idMensaje, nombreUsuario);
        System.out.println("Eliminado MENSAJE correctamente");
    }

    @GetMapping
    @Operation(summary = "Obtener todos los mensajes de gratitud", responses = {
            @ApiResponse(responseCode = "200", description = "Mensajes obtenidos correctamente")
    })
    public List<DatosMensaje> todosLosMensajes(){
        return mensajeService.todosLosMensajes();
    }

    @GetMapping("/usuario/{idUsuario}")
    @Operation(summary = "Obtener mensaje por ID", responses = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa"),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    public List<DatosMensaje> obtenerMensajesPorIdUsuario(
            @Parameter(description = "ID del usuario que se quieren obtener los mensajes",required = true)
            @PathVariable Long idUsuario) {
        return mensajeService.obtenerMensajePorIdUsuario(idUsuario);
    }

    @GetMapping("/usuario")
    @Operation(summary = "Obtener mensajes por nombre de usuario", responses = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa"),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    public List<DatosMensaje> obtenerMensajesPorNombreUsuario(
            @Parameter(description = "Nombre del usuario para buscar los mensajes", required = true)
            @RequestParam String nombreUsuario) {
        List<DatosMensaje> mensajes = mensajeService.obtenerMensajesPorNombreUsuario(nombreUsuario);
        if (mensajes.isEmpty()) {
            throw new ValidacionDeIntegridad("Usuario no encontrado");
        }
        return mensajes;
    }

    @PostMapping("/{id}/puntuar")
    @Operation(summary = "Puntuar un mensaje especifico, de acuerdo al ID", responses = {
            @ApiResponse(responseCode = "200", description = "Operación exitos"),
            @ApiResponse(responseCode = "404", description = "Mensaje no encontrado")
    })
    public ResponseEntity<DatosMensaje> puntuarMensaje(
            @Parameter(description = "ID del mensaje que se quiere calificar", required = true)
            @PathVariable Long id,
            @RequestParam Double puntuacion) {
        DatosMensaje mensajePuntuado = mensajeService.puntuarMensaje(id, puntuacion);
        return ResponseEntity.ok(mensajePuntuado);
    }

    @GetMapping("/orden/fecha")
    @Operation(summary = "Ordenar los mensajes por fecha en orden descendente", responses = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa")
    })
    public List<DatosMensaje> obtenerMensajesPorFecha() {
        return mensajeService.obtenerMensajesPorFecha();
    }

    @GetMapping("/orden/puntuacion")
    @Operation(summary = "Ordenar los mensajes por puntuación en orden descendente", responses = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa")
    })
    public List<DatosMensaje> obtenerMensajesPorPuntuacion() {
        return mensajeService.obtenerMensajesPorPuntuacion();
    }


}
