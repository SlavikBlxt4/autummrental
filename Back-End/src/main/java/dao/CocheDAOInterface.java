package dao;

import entities.Coche;
import entities.Reserva;

import java.util.ArrayList;

public interface CocheDAOInterface extends DAO<Coche, Integer> {
    public Coche findPriceById(int e);
    public ArrayList<Coche> findBetweenDates(Reserva bean);


}
