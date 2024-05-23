package dao;

import entities.Usuario;

public interface UsuarioDAOInterface extends DAO<Usuario, Integer> {

    public Usuario login(Usuario usuario);

    Usuario findByEmail(String email);
    public int findIdRol(String email);
}
