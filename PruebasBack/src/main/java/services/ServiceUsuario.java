package services;

import dao.DAO;
import entities.Usuario;

import java.util.ArrayList;

public class ServiceUsuario {

    private DAO<Usuario, Integer> myRepo;  // objeto del tipo interfaz de DAO (la más alta para ser genérico)

    // Constructor en el que se establece el repositorio a llamar
    public ServiceUsuario(DAO<Usuario, Integer> myRepo){
        this.myRepo = myRepo;
    }

    // Función para llamar a leerTodosLosUsuarios()
    public ArrayList<Usuario> leerTodosLosUsuarios(){
        return myRepo.findAll(null);
    }

    // Función para llamar al registro de usuarios
    public int registrarUsuario(Usuario usuario){
        return myRepo.add(usuario);
    }


}
