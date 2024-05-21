package dao;

import entities.Coche;

public interface CocheDAOInterface extends DAO<Coche, Integer> {
    public Coche findPriceById(int e);
}
