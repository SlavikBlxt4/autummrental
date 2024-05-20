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
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

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

                case "NEW_RESERVA":
                    cadDestino = addNewReserva(request, response);
                    break;
               /* case "UPDATE_RESERVA":
                    cadDestino = updateReserva(request, response);
                    break;
                case "DELETE_RESERVA":
                    cadDestino = deleteReserva(request, response);
                    break;*/
        }
        return cadDestino;
    }

    private String addNewReserva(HttpServletRequest request, HttpServletResponse response) {
        MotorSQL motorSql = FactoryMotorSQL.getInstance(FactoryMotorSQL.POSTGRES);
        DAO reservaDao = new ReservaDAO(motorSql);
        ServiceReserva serviceReserva = new ServiceReserva(reservaDao);




        int id_usuario = Integer.parseInt(request.getParameter("ID_USUARIO"));
        int id_coche = Integer.parseInt(request.getParameter("COCHE"));
        Timestamp fecha_inicio = Timestamp.valueOf(request.getParameter("FECHA_INICIO"));
        Timestamp fecha_fin = Timestamp.valueOf(request.getParameter("FECHA_FIN"));
        Coche coche = getCocheById(id_coche); // This method is hypothetical as it's not in the provided snippets.
        float cochePrecio = coche.getPrecio();

        // Calculate the number of days between fecha_inicio and fecha_fin.
        long diffInMillies = Math.abs(fecha_fin.getTime() - fecha_inicio.getTime());
        long diffInDays = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);

        // Calculate the precio for the Reserva.
        BigDecimal precio = BigDecimal.valueOf(cochePrecio / 30 * diffInDays);




        Reserva reserva = new Reserva();
        reserva.setId_usuario(id_usuario);
        reserva.setId_coche(id_coche);
        reserva.setPrecio(precio.floatValue());
        reserva.setFecha_inicio(fecha_inicio);
        reserva.setFecha_fin(fecha_fin);


        String resp = "";
        if (serviceReserva.registrarReserva(reserva) > 0) {
            resp = "OK";
        } else {
            resp = "ERROR";
        }

        return resp;
    }


    private String findAll(HttpServletRequest request, HttpServletResponse response) {
        MotorSQL motorSql = FactoryMotorSQL.getInstance(FactoryMotorSQL.POSTGRES);
        DAO reservaDao = new ReservaDAO(motorSql);
        ServiceReserva serviceReserva = new ServiceReserva(reservaDao);
        ArrayList<Reserva> reservas = serviceReserva.leerTodasLasReservas();

        return Reserva.toArrayJSon(reservas);
    }


}
