package services;

import dao.DAO;
import entities.Coche;

import java.util.ArrayList;

public class ServiceCoche {

    private DAO<Coche, Integer> myRepo;  // objeto del tipo interfaz de DAO (la más alta para ser genérico)

    // Constructor en el que se establece el repositorio a llamar
    public ServiceCoche(DAO<Coche, Integer> myRepo){
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
}
