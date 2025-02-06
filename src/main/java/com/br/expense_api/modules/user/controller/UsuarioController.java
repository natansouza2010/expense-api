package com.br.expense_api.modules.user.controller;

import com.br.expense_api.modules.user.model.Usuario;
import com.br.expense_api.modules.user.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.function.ServerRequest;

@RestController
@RequestMapping("/user")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/me")
    @ResponseBody
    public ResponseEntity<String> getUserInfo(@AuthenticationPrincipal OidcUser user) {
        System.out.println(user);
        return ResponseEntity.ok("Olá");
    }


    @GetMapping("/register")
    @ResponseBody
    public ResponseEntity<String> obterUsuario(@AuthenticationPrincipal OAuth2User oAuth2User) {
        if (oAuth2User == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("OIDC User is null");
        }

        Usuario usuario = usuarioService.registrarOuBuscar(oAuth2User);
        return ResponseEntity.ok("Usuario registrado ou encontrado: " + usuario.getNome());
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        return "Bem-vindo ao Dashboard!";
    }
}

