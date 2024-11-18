package com.example.gratitud_challenge.service;

import com.example.gratitud_challenge.dto.usuario.DatosCrearUsuario;
import com.example.gratitud_challenge.dto.usuario.DatosPublicoUsuario;
import com.example.gratitud_challenge.exception.ValidacionDeIntegridad;
import com.example.gratitud_challenge.model.Usuario;
import com.example.gratitud_challenge.repository.UsuarioRepository;
import com.example.gratitud_challenge.util.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ModelMapper modelMapper;

    public DatosCrearUsuario crearUsuario(DatosCrearUsuario datosCrearUsuario){
        if (usuarioRepository.findByNombreUsuario(datosCrearUsuario.nombreUsuario()).equals(datosCrearUsuario.nombreUsuario())) {
            throw new ValidacionDeIntegridad("El nombre ya existe, ingrese otro nombre");
        }

        String contrasenaCodificada = passwordEncoder().encode(datosCrearUsuario.password());
        Usuario usuario = new Usuario(datosCrearUsuario);
        usuario.setPassword(contrasenaCodificada);
        usuarioRepository.save(usuario);
        return datosCrearUsuario;
    }

    public DatosPublicoUsuario buscarUsuarioPorNombre(String nombre){
        Usuario usuario = usuarioRepository.findByNombreUsuario(nombre).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        return modelMapper.convertirADatosUsuario(usuario);
    }

    public Optional<Usuario> findById(Long idUsuario) {
        return usuarioRepository.findById(idUsuario);
    }

    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
