package com.example.gratitud_challenge.service;

import com.example.gratitud_challenge.dto.usuario.DatosCrearUsuario;
import com.example.gratitud_challenge.dto.usuario.DatosPublicoUsuario;
import com.example.gratitud_challenge.dto.usuario.DatosUsuario;
import com.example.gratitud_challenge.exception.ValidacionDeIntegridad;
import com.example.gratitud_challenge.model.Usuario;
import com.example.gratitud_challenge.repository.UsuarioRepository;
import com.example.gratitud_challenge.util.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<DatosPublicoUsuario> obtenerTodosLosUsuarios(){
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios.stream()
                .map(modelMapper::convertirADatosPublicoUsuario)
                .collect(Collectors.toList());
    }

    public DatosUsuario buscarUsuarioPorNombre(String nombre){
        Usuario usuario =  usuarioRepository.findByNombreUsuario(nombre)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        return modelMapper.convertirADatosUsuario(usuario);
    }

    public DatosUsuario buscarUsuarioPorID(Long idUsuario) {
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        return modelMapper.convertirADatosUsuario(usuario);
    }

    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public DatosUsuario actualizarUsuario(Long idUsuario, DatosCrearUsuario datosUsuario) {
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        usuario.setNombreUsuario(datosUsuario.nombreUsuario());
        usuario.setEmail(datosUsuario.email());
        usuario.setPassword(datosUsuario.password());

        usuario = usuarioRepository.save(usuario);
        return modelMapper.convertirADatosUsuario(usuario);
    }

    public void eliminarUsuario(Long idUsuario) {
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException(("Usuario no encontrado")));
        usuarioRepository.delete(usuario);
    }
}
