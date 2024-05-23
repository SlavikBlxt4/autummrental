package services;

import dao.DAO;
import dao.UsuarioDAOInterface;
import entities.Usuario;

import java.util.ArrayList;

public class ServiceUsuario {

    private UsuarioDAOInterface myRepo;  // objeto del tipo interfaz de DAO (la más alta para ser genérico)

    // Constructor en el que se establece el repositorio a llamar
    public ServiceUsuario(UsuarioDAOInterface myRepo){
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

    public Usuario logearUsuario(Usuario usuario){
        return myRepo.login(usuario);
    }

    public Usuario findByEmail(String email){
        return myRepo.findByEmail(email);
    }
    public int encontrarIdRol(String email){
        return myRepo.findIdRol(email);
    }
    public int encontrarIdUsuario(String email){
        return myRepo.findIdUsuario(email);
    }

}
