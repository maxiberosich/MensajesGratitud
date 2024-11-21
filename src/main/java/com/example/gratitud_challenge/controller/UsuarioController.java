package com.example.gratitud_challenge.controller;

import com.example.gratitud_challenge.dto.usuario.DatosCrearUsuario;
import com.example.gratitud_challenge.dto.usuario.DatosPublicoUsuario;
import com.example.gratitud_challenge.dto.usuario.DatosUsuario;
import com.example.gratitud_challenge.service.UsuarioService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/registrar")
    public DatosCrearUsuario registrarUsuario(@RequestBody @Valid DatosCrearUsuario datos){
        return usuarioService.crearUsuario(datos);
    }

    @GetMapping("/{idUsuario}")
    public DatosUsuario buscarUsuarioPorId(@PathVariable Long idUsuario){
        return usuarioService.buscarUsuarioPorID(idUsuario);
    }

    @GetMapping
    public List<DatosPublicoUsuario> mostrarTodosLosUsuarios(){
        return usuarioService.obtenerTodosLosUsuarios();
    }

    @PutMapping("/{idUsuario}")
    public DatosUsuario actualizarUsuario(@PathVariable Long idUsuario, @RequestBody DatosCrearUsuario datosUsuario){
        return usuarioService.actualizarUsuario(idUsuario, datosUsuario);
    }

    @DeleteMapping("/{idUsuario}")
    public void eliminarUsuario(@PathVariable Long idUsuario){
        usuarioService.eliminarUsuario(idUsuario);
    }

}
