package com.example.gratitud_challenge.controller;

import com.example.gratitud_challenge.dto.usuario.DatosCrearUsuario;
import com.example.gratitud_challenge.dto.usuario.DatosPublicoUsuario;
import com.example.gratitud_challenge.dto.usuario.DatosUsuario;
import com.example.gratitud_challenge.service.UsuarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
    @Operation(summary = "Registrar usuario", responses = {
            @ApiResponse(responseCode = "200", description = "Usuario registrado correctamente"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
    })
    public DatosCrearUsuario registrarUsuario(@RequestBody @Valid DatosCrearUsuario datos){
        return usuarioService.crearUsuario(datos);
    }

    @GetMapping("/{idUsuario}")
    @Operation(summary = "Buscar usuario por ID", responses = {
            @ApiResponse(responseCode = "200", description = "Usuario encontrado"),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    public DatosUsuario buscarUsuarioPorId(
            @Parameter(description = "ID del usuario a buscar", required = true)
            @PathVariable Long idUsuario){
        return usuarioService.buscarUsuarioPorID(idUsuario);
    }

    @GetMapping
    @Operation(summary = "Mostrar todos los usuarios", responses = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa")
    })
    public List<DatosPublicoUsuario> mostrarTodosLosUsuarios(){
        return usuarioService.obtenerTodosLosUsuarios();
    }

    @PutMapping("/{idUsuario}")
    @Operation(summary = "Actualizar usuario", responses = {
            @ApiResponse(responseCode = "200", description = "Usuario actualizado correctamente"),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
    })
    public DatosUsuario actualizarUsuario(
            @Parameter(description = "ID del usuario que se quiere actualizar", required = true)
            @PathVariable Long idUsuario,
            @RequestBody DatosCrearUsuario datosUsuario){
        return usuarioService.actualizarUsuario(idUsuario, datosUsuario);
    }

    @DeleteMapping("/{idUsuario}")
    @Operation(summary = "Eliminar usuario", responses = {
            @ApiResponse(responseCode = "200", description = "Usuario eliminado correctamente"),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    public void eliminarUsuario(
            @Parameter(description = "ID del usuario a eliminar", required = true)
            @PathVariable Long idUsuario){
        usuarioService.eliminarUsuario(idUsuario);
    }

}
