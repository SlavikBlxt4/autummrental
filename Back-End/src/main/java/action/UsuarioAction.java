package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DAO;
import dao.UsuarioDAOInterface;
import org.mindrot.jbcrypt.BCrypt;
import services.ServiceUsuario;
import utils.FactoryMotorSQL;
import entities.Usuario;
import dao.UsuarioDAO;
import utils.MotorSQL;
import utils.PasswordUtils;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.ArrayList;
import java.util.Date;

public class UsuarioAction implements IAction {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String cadDestino = "";
        String action = (String) request.getParameter("ACTION");  // ACTION=USUARIO.LOGIN
        String[] arrayAction = action.split("\\.");           // Aqui se divide el string cuando hay un punto
        switch (arrayAction[1]) {
            case "LOGIN":
                    cadDestino = login(request, response);
                break;
            case "REGISTER":
                cadDestino = register(request, response);
                break;
            case "FINDALL":
                cadDestino = findAll(request, response);
                break;

        }
        return cadDestino;
    }

    private String createToken(Usuario usuario) {
        Algorithm algorithm = Algorithm.HMAC256("secret"); // Usa una clave secreta más fuerte en producción
        String token = JWT.create()
                .withSubject(String.valueOf(usuario.getId_usuario())) // Usa un identificador único del usuario
                .withExpiresAt(new Date(System.currentTimeMillis() + 3600 * 1000)) // Tiempo de expiración
                .withIssuer("untitled")
                .sign(algorithm);
        return token;
    }

    private String register(HttpServletRequest request, HttpServletResponse response) {
        MotorSQL motorSql = FactoryMotorSQL.getInstance(FactoryMotorSQL.POSTGRES);
        UsuarioDAOInterface usuarioDao = new UsuarioDAO(motorSql);
        ServiceUsuario serviceUsuario = new ServiceUsuario(usuarioDao);


        // Método para generar un hash de una contraseña


        String email = request.getParameter("EMAIL");
        String password = request.getParameter("PASSWORD");
        String hashedPassword = PasswordUtils.hashPassword(password);

        Usuario usuario = new Usuario();
        usuario.setEmail(email);
        usuario.setPassword(hashedPassword);

        String resp = "";
        if (serviceUsuario.registrarUsuario(usuario) > 0) {
            resp = "OK";
        } else {
            resp = "ERROR";
        }

        return resp;
    }

    private String findAll(HttpServletRequest request, HttpServletResponse response) {
        MotorSQL motorSql = FactoryMotorSQL.getInstance(FactoryMotorSQL.POSTGRES);
        UsuarioDAOInterface usuarioDao = new UsuarioDAO(motorSql);
        ServiceUsuario serviceUsuario = new ServiceUsuario(usuarioDao);
        ArrayList<Usuario> usuarios = serviceUsuario.leerTodosLosUsuarios();
        return Usuario.toArrayJSon(usuarios);
    }

    private String login(HttpServletRequest request, HttpServletResponse response) {
        MotorSQL motorSql = FactoryMotorSQL.getInstance(FactoryMotorSQL.POSTGRES);
        UsuarioDAOInterface usuarioDao = new UsuarioDAO(motorSql);
        ServiceUsuario serviceUsuario = new ServiceUsuario(usuarioDao);

        String email = request.getParameter("EMAIL");
        String password = request.getParameter("PASSWORD");
        int id_rol = serviceUsuario.encontrarIdRol(email);

        // Obtener el usuario por email, asumiendo que existe una función para ello
        Usuario usuario = usuarioDao.findByEmail(email);

        if (usuario != null && PasswordUtils.checkPassword(password, usuario.getPassword())) {
            return String.valueOf(id_rol);
        } else {
            // Login fallido
            return "ERROR";
        }
    }
}
