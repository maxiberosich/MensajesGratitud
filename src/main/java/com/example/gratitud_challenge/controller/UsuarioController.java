package com.example.gratitud_challenge.controller;

import com.example.gratitud_challenge.dto.DatosCrearUsuario;
import com.example.gratitud_challenge.service.UsuarioService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/registrar")
    public DatosCrearUsuario registrarUsuario(@RequestBody @Valid DatosCrearUsuario datos){
        return usuarioService.crearUsuario(datos);
    }

}
