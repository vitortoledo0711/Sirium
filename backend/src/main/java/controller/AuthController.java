package controller;

import model.LoginRequest;
import model.TokenResponse;
import org.springframework.web.bind.annotation.*;

import service.UsuarioService;
import model.Usuario;
import security.JwtService;

@RestController
@RequestMapping("/login")
public class AuthController {

    private UsuarioService service = new UsuarioService();

    @PostMapping
    public TokenResponse login(@RequestBody LoginRequest request) throws Exception {

        Usuario usuario = service.autenticar(
                request.getUsuario(),
                request.getSenha()
        );

        if(usuario == null) {
            throw new RuntimeException("Login inválido");
        }

        String token = JwtService.gerarToken(usuario);

        return new TokenResponse(token);
    }

}