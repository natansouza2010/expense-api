package com.br.expense_api.modules.user.service;


import com.br.expense_api.config.exception.UsuarioNotFoundException;
import com.br.expense_api.modules.despesas.model.Despesa;
import com.br.expense_api.modules.despesas.repository.DespesaRepository;
import com.br.expense_api.modules.despesas.service.DespesaService;
import com.br.expense_api.modules.user.model.Usuario;
import com.br.expense_api.modules.user.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UsuarioService {


    private final UsuarioRepository usuarioRepository;
    private final DespesaService despesaService;

    public UsuarioService(UsuarioRepository usuarioRepository, DespesaRepository despesaRepository, DespesaService despesaService) {
        this.usuarioRepository = usuarioRepository;
        this.despesaService = despesaService;
    }

    public Usuario registrarOuBuscar(OAuth2User oAuth2User) {
        String email = oAuth2User.getAttribute("email");
        Optional<Usuario> usuarioExistente = usuarioRepository.findByEmail(email);

        return usuarioExistente.orElseGet(() -> {
            Usuario newUser = new Usuario();
            newUser.setEmail(email);
            newUser.setNome(oAuth2User.getName());
            return usuarioRepository.save(newUser);
        });
    }

    @Transactional
    public Despesa adicionarDespesa(UUID usuarioId, Despesa despesa) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new UsuarioNotFoundException("Usuário não encontrado"));

        despesa.setUsuario(usuario);
        return despesaService.salvarDespesa(despesa);
    }
}
