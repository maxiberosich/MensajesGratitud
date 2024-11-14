package com.example.gratitud_challenge.service;

import com.example.gratitud_challenge.dto.DatosCrearUsuario;
import com.example.gratitud_challenge.model.Usuario;
import com.example.gratitud_challenge.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public DatosCrearUsuario crearUsuario(DatosCrearUsuario datosCrearUsuario){
        Usuario usuario = new Usuario(datosCrearUsuario);
        usuarioRepository.save(usuario);
        return datosCrearUsuario;
    }

}
