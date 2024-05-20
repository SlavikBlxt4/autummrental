package services;

import dao.DAO;
import entities.Coche;
import entities.Reserva;

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

    // Función para llamar a add()
    /*
    public int añadirPelicula(Usuario usuario){

        return myRepo.add(usuario);
    }
    */
}
