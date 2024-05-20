package action;

import dao.CocheDAO;
import dao.DAO;
import dao.ReservaDAO;
import entities.Coche;
import entities.Reserva;
import services.ServiceCoche;
import services.ServiceReserva;
import utils.FactoryMotorSQL;
import utils.MotorSQL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

public class ReservaAction implements IAction{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String cadDestino = "";
        String action = (String) request.getParameter("ACTION");  // ACTION=RESERVA.FINDALL
        String[] arrayAction = action.split("\\.");           // Aqui se divide el string cuando hay un punto
        switch (arrayAction[1]) {
            case "FINDALL":
                cadDestino = findAll(request, response);
                break;
        }
        return cadDestino;
    }


    private String findAll(HttpServletRequest request, HttpServletResponse response) {
        MotorSQL motorSql = FactoryMotorSQL.getInstance(FactoryMotorSQL.POSTGRES);
        DAO reservaDao = new ReservaDAO(motorSql);
        ServiceReserva serviceReserva = new ServiceReserva(reservaDao);
        ArrayList<Reserva> reservas = serviceReserva.leerTodasLasReservas();

        return Reserva.toArrayJSon(reservas);
    }


}
