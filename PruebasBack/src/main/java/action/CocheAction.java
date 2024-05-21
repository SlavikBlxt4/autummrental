package action;

import dao.CocheDAO;
import dao.CocheDAOInterface;
import dao.DAO;
import dao.UsuarioDAO;
import entities.Coche;
import entities.Usuario;
import services.ServiceCoche;
import services.ServiceUsuario;
import utils.FactoryMotorSQL;
import utils.MotorSQL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

public class CocheAction implements IAction{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String cadDestino = "";
        String action = (String) request.getParameter("ACTION");  // ACTION=COCHE.FINDALL
        String[] arrayAction = action.split("\\.");           // Aqui se divide el string cuando hay un punto
        switch (arrayAction[1]) {
            case "FINDALL":
                cadDestino = findAll(request, response);
                break;
            case "NEW_CAR":
                cadDestino = addNewCar(request, response);
                break;
            case "UPDATE_CAR":
                cadDestino = updateCar(request, response);
                break;
            case "DELETE_CAR":
                cadDestino = deleteCar(request, response);
                break;
        }
        return cadDestino;
    }


    private String addNewCar(HttpServletRequest request, HttpServletResponse response) {
        MotorSQL motorSql = FactoryMotorSQL.getInstance(FactoryMotorSQL.POSTGRES);
        CocheDAOInterface cocheDao = new CocheDAO(motorSql);
        ServiceCoche serviceCoche = new ServiceCoche(cocheDao);

        String modelo = request.getParameter("MODELO");
        String detalles = request.getParameter("DETALLES");
        String descripcion = request.getParameter("DESCRIPCION");
        String precio = request.getParameter("PRECIO");
        String imagen = request.getParameter("IMAGEN");

        Coche coche = new Coche();
        coche.setModelo(modelo);
        coche.setDetalles(detalles);
        coche.setDescripcion(descripcion);
        coche.setPrecio((float) Double.parseDouble(precio));
        coche.setImagen(imagen);

        String resp = "";
        if (serviceCoche.registrarCoche(coche) > 0) {
            resp = "OK";
        } else {
            resp = "ERROR";
        }

        return resp;
    }

    private String updateCar(HttpServletRequest request, HttpServletResponse response) {
        MotorSQL motorSql = FactoryMotorSQL.getInstance(FactoryMotorSQL.POSTGRES);
        CocheDAOInterface cocheDao = new CocheDAO(motorSql);
        ServiceCoche serviceCoche = new ServiceCoche(cocheDao);

        String id_coche = request.getParameter("ID_COCHE");
        String modelo = request.getParameter("MODELO");
        String detalles = request.getParameter("DETALLES");
        String descripcion = request.getParameter("DESCRIPCION");
        String precio = request.getParameter("PRECIO");
        String disponibilidad = request.getParameter("DISPONIBILIDAD");
        String imagen = request.getParameter("IMAGEN");

        Coche coche = new Coche();
        coche.setId_coche(Integer.parseInt(id_coche));
        coche.setModelo(modelo);
        coche.setDetalles(detalles);
        coche.setDescripcion(descripcion);
        if (precio.equals("null")) {
            coche.setPrecio(-1);
        }else {
            coche.setPrecio((float) Double.parseDouble(precio));
        }
        if (disponibilidad.equals("true")) {
            coche.setDisponibilidad(true);
        }else {
            coche.setDisponibilidad(false);
        }
        coche.setImagen(imagen);

        String resp = "";
        if (serviceCoche.actualizarCoche(coche) > 0) {
            resp = "OK";
        } else {
            resp = "ERROR";
        }
        return resp;
    }

    private String deleteCar(HttpServletRequest request, HttpServletResponse response) {
        MotorSQL motorSql = FactoryMotorSQL.getInstance(FactoryMotorSQL.POSTGRES);
        CocheDAOInterface cocheDao = new CocheDAO(motorSql);
        ServiceCoche serviceCoche = new ServiceCoche(cocheDao);

        String id_coche = request.getParameter("ID_COCHE");

        String resp = "";
        if (serviceCoche.eliminarCoche(Integer.parseInt(id_coche)) > 0) {
            resp = "OK";
        } else {
            resp = "ERROR";
        }
        return resp;
    }


    private String findAll(HttpServletRequest request, HttpServletResponse response) {
        MotorSQL motorSql = FactoryMotorSQL.getInstance(FactoryMotorSQL.POSTGRES);
        CocheDAOInterface cocheDao = new CocheDAO(motorSql);
        ServiceCoche serviceCoche = new ServiceCoche(cocheDao);
        ArrayList<Coche> coches = serviceCoche.leerTodosLosCoches();

        return Coche.toArrayJSon(coches);
    }


}
