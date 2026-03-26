package controller;

import model.LoginRequest;
import model.TokenResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import service.UsuarioService;
import model.Usuario;
import security.JwtService;

@RestController
@RequestMapping("/login")
@CrossOrigin(origins = "*") // allow frontend requests from any origin during development
public class AuthController {

    private final UsuarioService service;

    public AuthController(UsuarioService service) {
        this.service = service;
    }

    @PostMapping
    public TokenResponse login(@RequestBody LoginRequest request) {
        Usuario usuario = service.autenticar(
                request.getUsuario(),
                request.getSenha()
        );

        if (usuario == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Login inválido");
        }

        String token = JwtService.gerarToken(usuario);

        return new TokenResponse(token);
    }

}