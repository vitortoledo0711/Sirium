package service;

import model.Usuario;
import org.springframework.stereotype.Service;
import repository.UsuarioRepository;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public Usuario autenticar(String login, String senha) {
        Usuario usuario = repository.buscarPorLogin(login);

        if (usuario == null) {
            return null;
        }

        String storedPassword = usuario.getSenha();
        if (storedPassword != null) {
            storedPassword = storedPassword.trim();
        }

        if (senha != null) {
            senha = senha.trim();
        }

        if (!senha.equals(storedPassword)) {
            return null;
        }

        return usuario;
    }

}