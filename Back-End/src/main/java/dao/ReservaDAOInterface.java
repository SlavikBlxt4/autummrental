package dao;

import entities.Coche;
import entities.Reserva;

import java.util.ArrayList;

public interface ReservaDAOInterface extends DAO<Reserva, Integer> {
    public Reserva findStartDateById(Integer e);
    public Reserva findIdCoche(int e);

}
