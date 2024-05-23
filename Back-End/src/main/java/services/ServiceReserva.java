package services;

import dao.ReservaDAOInterface;
import entities.Reserva;

import java.util.ArrayList;

public class ServiceReserva {

    private ReservaDAOInterface myRepo;  // objeto del tipo interfaz de DAO (la más alta para ser genérico)

    // Constructor en el que se establece el repositorio a llamar
    public ServiceReserva(ReservaDAOInterface myRepo){
        this.myRepo = myRepo;
    }

    // Función para llamar a leerTodosLosUsuarios()
    public ArrayList<Reserva> leerTodasLasReservas(Reserva reserva){
        return myRepo.findAll(reserva);
    }

    public int registrarReserva(Reserva reserva) {

        return myRepo.add(reserva);
    }

    public int updateEndDate(Reserva reserva) {

        myRepo.update(reserva);
        return 1;
    }

    public Reserva encontrarFechaInicioPorId(int id_reserva){
        return myRepo.findStartDateById(id_reserva);
    }
    public Reserva encontrarIdCochePorId(int id_reserva){
        return myRepo.findIdCoche(id_reserva);
    }
        public int eliminarReserva(int id_reserva){
        return myRepo.delete(id_reserva);
    }

    // Función para llamar a add()
    /*
    public int añadirPelicula(Usuario usuario){

        return myRepo.add(usuario);
    }
    */
}
