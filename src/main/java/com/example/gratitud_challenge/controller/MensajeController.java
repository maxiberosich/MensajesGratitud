package com.example.gratitud_challenge.controller;

import com.example.gratitud_challenge.dto.mensaje.DatosCrearMensaje;
import com.example.gratitud_challenge.dto.mensaje.DatosMensaje;
import com.example.gratitud_challenge.service.MensajeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mensaje")
public class MensajeController {

    @Autowired
    private MensajeService mensajeService;

    @PostMapping("/{idUsuario}/crear-mensaje")
    public DatosCrearMensaje crearMensaje(@PathVariable Long idUsuario, @RequestBody @Valid DatosCrearMensaje mensaje){
        return mensajeService.crearMensaje(idUsuario, mensaje);
    }

    @DeleteMapping("/{id}")
    public void borrarMensaje(@PathVariable Long id){
        mensajeService.eliminarMensaje(id);
        System.out.println("Eliminado MENSAJE correctamente");
    }

    @GetMapping
    public List<DatosMensaje> todosLosMensajes(){
        return mensajeService.todosLosMensajes();
    }

}
