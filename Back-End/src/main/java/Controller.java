import action.CocheAction;
import action.PeliculaAction;
import action.ReservaAction;
import action.UsuarioAction;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(name = "Controller", urlPatterns = {"/Controller"})
public class Controller extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        /*********************************************************/
        // HECHOS LOS MÉTODOS FINDALL DE USUARIO Y COCHE
        // http://localhost:8080/AutumnRental/Controller?ACTION=USUARIO.FINDALL
        // http://localhost:8080/AutumnRental/Controller?ACTION=COCHE.FINDALL

        // HECHO EL MÉTODO DE REGISTRO DE USUARIO
        // http://localhost:8080/AutumnRental/Controller?ACTION=USUARIO.REGISTER&EMAIL=john123.doe@example.com&PASSWORD=1234

        // HECHO MÉTODO AÑADIR, ACTUALIZAR Y ELIMINAR COCHE
        // http://localhost:8080/AutumnRental/Controller?ACTION=COCHE.NEW_CAR&MODELO=CRESPAN&DETALLES=KIA&DESCRIPCION=KIA&PRECIO=2000&DISPONIBILIDAD=true&IMAGEN=HTTP://www.kia.com/JEGCYUEW

        // Ejemplo de url con varios parámetros, usar & para separarlos
        // http://localhost:8080/AutumnRental/Controller?ACTION=USUARIO.CREATE&NAME=John&EMAIL=john.doe@example.com
        /*********************************************************/

        response.setContentType("text/plain;charset=UTF-8");
        response.setHeader("Access-Control-Allow-Origin", "*"); // Permitir acceso desde cualquier origen
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS"); // Permitir los métodos HTTP
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization"); // Permitir ciertos encabezados
        response.setHeader("Access-Control-Max-Age", "3600"); // Cache de opciones preflight durante 1 hora
        response.setContentType("text/plain;charset=UTF-8"); // Configurar el tipo de contenido de la respuesta

        PrintWriter out = response.getWriter();
        String action = request.getParameter("ACTION");
        String[] arrayAction = action.split("\\.");

        switch (arrayAction[0]) {
            case "USUARIO":
                String resp = new UsuarioAction().execute(request, response);
                System.out.println(resp);
                out.print(resp);
                break;
            case "COCHE":
                String resp2 = new CocheAction().execute(request, response);
                System.out.println(resp2);
                out.print(resp2);
                break;
            case  "RESERVA":
                String resp3 = new ReservaAction().execute(request, response);
                System.out.println(resp3);
                out.print(resp3);
                break;
        }
    }
}
