package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DAO;
import services.ServiceUsuario;
import utils.FactoryMotorSQL;
import entities.Usuario;
import dao.UsuarioDAO;
import utils.MotorSQL;

import java.util.ArrayList;

public class UsuarioAction implements IAction{

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
        DAO usuarioDao = new UsuarioDAO(motorSql);
        ServiceUsuario serviceUsuario = new ServiceUsuario(usuarioDao);

        String email = request.getParameter("EMAIL");
        String password = request.getParameter("PASSWORD");

        Usuario usuario = new Usuario();
        usuario.setEmail(email);
        usuario.setPassword(password);

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
        DAO usuarioDao = new UsuarioDAO(motorSql);
        ServiceUsuario serviceUsuario = new ServiceUsuario(usuarioDao);
        ArrayList<Usuario> usuarios = serviceUsuario.leerTodosLosUsuarios();
        return Usuario.toArrayJSon(usuarios);
    }

     private String login(HttpServletRequest request, HttpServletResponse response) {
        // SELECT * FROM USUARIO WHERE EMAIL = 'a@svalero.com'  AND PASSWORD='1234'
        String email = request.getParameter("EMAIL");
        String password = request.getParameter("PASSWORD");
        //String nombre = request.getParameter("NOMBRE");
        
       /* Usuario usuario =new Usuario();
            usuario.setEmail(email);
            usuario.setPassword(password);*/
            //usuario.setNombre(nombre);

         MotorSQL motorSql = FactoryMotorSQL.getInstance(FactoryMotorSQL.POSTGRES);
         DAO usuarioDAO = new UsuarioDAO(motorSql);
         
            //int numUsuarios = usuarioDAO.add(usuario);
         
        Usuario usuario2 = ((UsuarioDAO) usuarioDAO).login(email, password);
        
        // String resp = "['message:'good', 'usuario':{email:'"+email+"', password:'1234'}]";
        
        return Usuario.fromObjectToJSON(usuario2) ; 
    }
}
