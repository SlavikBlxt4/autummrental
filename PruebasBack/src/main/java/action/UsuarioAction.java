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

import java.util.ArrayList;

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

        // Obtener el usuario por email, asumiendo que existe una función para ello
        Usuario usuario = usuarioDao.findByEmail(email);

        if (usuario != null && PasswordUtils.checkPassword(password, usuario.getPassword())) {
            // Login exitoso
            return Usuario.fromObjectToJSON(usuario);
        } else {
            // Login fallido
            return "ERROR";
        }
    }
}
