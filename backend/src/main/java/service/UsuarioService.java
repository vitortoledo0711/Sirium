package service;

import model.Usuario;
import repository.UsuarioRepository;

public class UsuarioService {

    private UsuarioRepository repository = new UsuarioRepository();

    public Usuario autenticar(String login, String senha) throws Exception {

        Usuario usuario = repository.buscarPorLogin(login);

        if(usuario == null) {
            return null;
        }

        if(!usuario.getSenha().equals(senha)) {
            return null;
        }

        return usuario;
    }

}