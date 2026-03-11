package controller;

import model.Usuario;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @GetMapping("/{id}")
    public Usuario buscar(@PathVariable Long id) {

        Usuario u = new Usuario();
        u.setId(id);
        u.setNome("João");

        return u;
    }

}