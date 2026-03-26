package repository;

import model.Usuario;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UsuarioRepository {

    private final JdbcTemplate jdbcTemplate;

    public UsuarioRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Usuario buscarPorLogin(String login) {
        // A tabela foi criada como T_USER (maiúsculo). No Postgres, nomes criados com aspas ficam case-sensitive.
        String sql = "select cpf, pass from \"T_USER\" where cpf = ?";

        List<Usuario> usuarios = jdbcTemplate.query(sql, (rs, rowNum) -> {
            Usuario u = new Usuario();
            u.setLogin(rs.getString("cpf"));
            u.setSenha(rs.getString("pass"));
            return u;
        }, login);

        return usuarios.isEmpty() ? null : usuarios.get(0);
    }

}
