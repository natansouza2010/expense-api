package com.br.expense_api.modules.user.service;


import com.br.expense_api.modules.user.model.Usuario;
import com.br.expense_api.modules.user.repository.UsuarioRepository;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {


    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario registrarOuBuscar(OidcUser oidcUser) {
        String email = oidcUser.getEmail();
        Optional<Usuario> usuarioExistente = usuarioRepository.findByEmail(email);

        return usuarioExistente.orElseGet(() -> {
            Usuario newUser = new Usuario();
            newUser.setEmail(email);
            newUser.setNome(oidcUser.getName());
            return usuarioRepository.save(newUser);
        });
    }
}
