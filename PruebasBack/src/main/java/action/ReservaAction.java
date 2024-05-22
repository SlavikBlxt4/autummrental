package action;

import dao.CocheDAO;
import dao.DAO;
import dao.ReservaDAO;
import dao.ReservaDAOInterface;
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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.time.temporal.ChronoUnit;

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
                case "UPDATE_RESERVA":
                    cadDestino = updateReserva(request, response);
                    break;
                /*case "DELETE_RESERVA":
                    cadDestino = deleteReserva(request, response);
                    break;*/
        }
        return cadDestino;
    }


    //funcion para actualizar la fecha de la reserva
    // http://localhost:8080/AutumnRental/Controller?ACTION=RESERVA.UPDATE_RESERVA&ID_RESERVA=6&FECHA_FIN=2024-04-30
    private String updateReserva(HttpServletRequest request, HttpServletResponse response) {
        MotorSQL motorSQL = FactoryMotorSQL.getInstance(FactoryMotorSQL.POSTGRES);
        ReservaDAOInterface reservaDAO = new ReservaDAO(motorSQL);
        ServiceReserva serviceReserva = new ServiceReserva(reservaDAO);
        ServiceCoche serviceCoche = new ServiceCoche(new CocheDAO(motorSQL));

        int id_reserva = Integer.parseInt(request.getParameter("ID_RESERVA"));
        Reserva reservaEncontrada = serviceReserva.encontrarFechaInicioPorId(id_reserva);

        //i want to call my function encontrarFechaInicioPorId() to get the start date, which is on servicereserva

        int id_coche = new ServiceReserva(reservaDAO).encontrarIdCochePorId(id_reserva).getId_coche();

        float coche_precio = serviceCoche.obtenerPrecioCochePorId(id_coche);
        LocalDate fecha_inicio = reservaEncontrada != null ? reservaEncontrada.getFecha_inicio() : null;
        LocalDate fecha_fin = LocalDate.parse(request.getParameter("FECHA_FIN"));

        long diffInDays = ChronoUnit.DAYS.between(fecha_inicio, fecha_fin);

        float precio = coche_precio/30*diffInDays;

        //i want to update only the end date depending of each id_reserva



        Reserva reserva = new Reserva();
        reserva.setId_reserva(id_reserva);
        reserva.setFecha_fin(fecha_fin);
        reserva.setPrecio((float) (Math.round(precio*100)/100));
        serviceReserva.updateEndDate(reserva);

        String resp = "";
        if (serviceReserva.updateEndDate(reserva) > 0) {
            resp = "OK";
        } else {
            resp = "ERROR";
        }
        return resp;
    }


    //añadir reserva
    // http://localhost:8080/AutumnRental/Controller?ACTION=RESERVA.NEW_RESERVA&ID_USUARIO=1&COCHE=1&FECHA_INICIO=2024-04-29&FECHA_FIN=2024-04-30

    private String addNewReserva(HttpServletRequest request, HttpServletResponse response) {
        MotorSQL motorSql = FactoryMotorSQL.getInstance(FactoryMotorSQL.POSTGRES);
        ReservaDAOInterface reservaDao = new ReservaDAO(motorSql);
        ServiceReserva serviceReserva = new ServiceReserva(reservaDao);
        ServiceCoche serviceCoche = new ServiceCoche(new CocheDAO(motorSql));





        int id_usuario = Integer.parseInt(request.getParameter("ID_USUARIO"));
        int id_coche = Integer.parseInt(request.getParameter("COCHE"));
        float coche_precio = serviceCoche.obtenerPrecioCochePorId(id_coche);
        LocalDate fecha_inicio = LocalDate.parse(request.getParameter("FECHA_INICIO"));
        LocalDate fecha_fin = LocalDate.parse(request.getParameter("FECHA_FIN"));
        // Calculate the number of days between fecha_inicio and fecha_fin.
        long diffInDays = ChronoUnit.DAYS.between(fecha_inicio, fecha_fin);
        float precio = coche_precio/30*diffInDays;
        








        Reserva reserva = new Reserva();
        reserva.setId_usuario(id_usuario);
        reserva.setId_coche(id_coche);
        reserva.setPrecio(precio);
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
        ReservaDAOInterface reservaDao = new ReservaDAO(motorSql);
        ServiceReserva serviceReserva = new ServiceReserva(reservaDao);
        ArrayList<Reserva> reservas = serviceReserva.leerTodasLasReservas();

        return Reserva.toArrayJSon(reservas);
    }


}
