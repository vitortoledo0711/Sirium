package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.sql.DataSource;

import model.Usuario;

public class UsuarioRepository {

    public Usuario buscarPorLogin(String login) throws Exception {

        String sql = "select id, nome, login, senha from usuario where login = ?";

        try (Connection conn = null; PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, login);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                Usuario u = new Usuario();

                u.setId(rs.getLong("id"));
                u.setNome(rs.getString("nome"));
                u.setLogin(rs.getString("login"));
                u.setSenha(rs.getString("senha"));

                return u;
            }

        }

        return null;
    }

}
