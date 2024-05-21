package services;

import dao.DAO;
import entities.Coche;
import entities.Reserva;

import java.time.LocalDate;
import java.util.ArrayList;

public class ServiceReserva {

    private DAO<Reserva, Integer> myRepo;  // objeto del tipo interfaz de DAO (la más alta para ser genérico)

    // Constructor en el que se establece el repositorio a llamar
    public ServiceReserva(DAO<Reserva, Integer> myRepo){
        this.myRepo = myRepo;
    }

    // Función para llamar a leerTodosLosUsuarios()
    public ArrayList<Reserva> leerTodasLasReservas(){
        return myRepo.findAll(null);
    }

    public int registrarReserva(Reserva reserva) {

        return myRepo.add(reserva);
    }

    public void updateEndDate(int idReserva, LocalDate fechaFin) {

        myRepo.updateEndDate(idReserva, fechaFin);
    }

    // Función para llamar a add()
    /*
    public int añadirPelicula(Usuario usuario){

        return myRepo.add(usuario);
    }
    */
}
