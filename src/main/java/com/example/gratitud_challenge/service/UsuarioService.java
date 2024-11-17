package com.example.gratitud_challenge.service;

import com.example.gratitud_challenge.dto.usuario.DatosCrearUsuario;
import com.example.gratitud_challenge.dto.usuario.DatosPublicoUsuario;
import com.example.gratitud_challenge.model.Usuario;
import com.example.gratitud_challenge.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public DatosCrearUsuario crearUsuario(DatosCrearUsuario datosCrearUsuario){
        Usuario usuario = new Usuario(datosCrearUsuario);
        usuarioRepository.save(usuario);
        return datosCrearUsuario;
    }

    public DatosPublicoUsuario buscarUsuarioPorNombre(String nombre){
        return usuarioRepository.findByNombreUsuario(nombre);
    }

    public Optional<Usuario> findById(Long idUsuario) {
        return usuarioRepository.findById(idUsuario);
    }

}
