package services;

import dao.CocheDAOInterface;
import dao.DAO;
import entities.Coche;
import entities.Reserva;

import java.util.ArrayList;

public class ServiceCoche {

    private CocheDAOInterface myRepo;  // objeto del tipo interfaz de DAO (la más alta para ser genérico)

    // Constructor en el que se establece el repositorio a llamar
    public ServiceCoche(CocheDAOInterface myRepo){
        this.myRepo = myRepo;
    }

    // Función para llamar a leerTodosLosCoche()
    public ArrayList<Coche> leerTodosLosCoches(){
        return myRepo.findAll(null);
    }

    // Función para añadir un nuevo coche
    public int registrarCoche(Coche coche){
        return myRepo.add(coche);
    }

    // Función para actualizar un coche
    public int actualizarCoche(Coche coche){
        return myRepo.update(coche);
    }

    public int eliminarCoche(int id_coche) {
        return myRepo.delete(id_coche);
    }

    public float obtenerPrecioCochePorId(int id_coche){
        return myRepo.findPriceById(id_coche).getPrecio();
    }

        public ArrayList<Coche> obtenerCochesPorFecha(Reserva bean){
        return myRepo.findBetweenDates(bean);
    }

}
