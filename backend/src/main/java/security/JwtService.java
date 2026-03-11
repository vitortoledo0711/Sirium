package security;

import java.util.Base64;
import model.Usuario;

public class JwtService {

    public static String gerarToken(Usuario usuario) {

        String token = usuario.getId() + ":" + System.currentTimeMillis();

        return Base64.getEncoder().encodeToString(token.getBytes());
    }

}