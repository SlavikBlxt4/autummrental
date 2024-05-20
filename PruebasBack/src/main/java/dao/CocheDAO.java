package dao;

import entities.Coche;
import entities.Usuario;
import utils.MotorSQL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CocheDAO implements DAO<Coche, Integer> {


    private final String SQL_ADD
            = "INSERT INTO COCHE (MODELO, DETALLES, DESCRIPCION, PRECIO, IMAGEN) VALUES( ";

    private final String SQL_FINDALL
            = "SELECT * FROM COCHE ORDER BY ID_COCHE ASC";
    private final String SQL_DELETE = "DELETE FROM COCHE WHERE ID_COCHE=";

    private final String SQL_UPDATE = "UPDATE COCHE SET ";


    private MotorSQL motorSql;

    public CocheDAO(MotorSQL motorSql) {
        this.motorSql = motorSql;
    }





    @Override
    public int add(Coche bean) {
        this.motorSql.connect();
        String sql = "";
        sql+= SQL_ADD;
                sql+= "'" + bean.getModelo() + "'";
                sql+= ",";
                sql+= "'" + bean.getDetalles() + "'";
                sql+= ",";
                sql+= "'" + bean.getDescripcion()+ "'";
                sql+= ",";
                sql+= bean.getPrecio();
                sql+= ",";
                sql+= "'" + bean.getImagen()+ "'";
                sql+= ")";

        int filasModificadas = this.motorSql.execute(sql);
        this.motorSql.disconnect();
        
        return filasModificadas;
    }

    @Override
    public int delete(Integer e) {
        this.motorSql.connect();
        String sql = "";
        sql+= SQL_DELETE;
        sql+= e;
        int filasModificadas = this.motorSql.execute(sql);
        this.motorSql.disconnect();

        return filasModificadas;
    }

    @Override
    public int update(Coche bean) {
        this.motorSql.connect();
        String sql = "";
        sql+= SQL_UPDATE;
        sql+= "DISPONIBILIDAD='" + bean.isDisponibilidad()+ "'";
        if (!Objects.equals(bean.getModelo(), "null")) {
            sql+= ", ";
            sql+= "MODELO='" + bean.getModelo() + "'";
        }
        if (!Objects.equals(bean.getDetalles(), "null")) {
            sql+= ", ";
            sql+= "DETALLES='" + bean.getDetalles() + "'";
        }
        if (!Objects.equals(bean.getDescripcion(), "null")) {
            sql+= ", ";
            sql+= "DESCRIPCION='" + bean.getDescripcion()+ "'";
        }
        if (bean.getPrecio() != -1) {
            sql+= ", ";
            sql+= "PRECIO=" + bean.getPrecio();
        }
        if (!Objects.equals(bean.getImagen(), "null")) {
            sql+= ", ";
            sql+= "IMAGEN='" + bean.getImagen()+ "'";
        }
        sql+= " WHERE ID_COCHE=" + bean.getId_coche();
        int filasModificadas = this.motorSql.execute(sql);
        this.motorSql.disconnect();

        return filasModificadas;
    }

    @Override
    public ArrayList<Coche> findAll(Coche bean) {
        ArrayList<Coche> coches = new ArrayList<>();
        String sql = SQL_FINDALL;
        try {
            //1º)
            motorSql.connect();

            ResultSet rs = motorSql.executeQuery(sql);

            while (rs.next()) { // TRANSFOMAR LA COLECCIÓN DEBASE DE DATOS A UN ARRAYLIST
                Coche coche = new Coche();

                coche.setId_coche(rs.getInt(1));
                coche.setModelo(rs.getString(2));
                coche.setDetalles(rs.getString(3));
                coche.setDescripcion(rs.getString(4));
                coche.setPrecio(rs.getFloat(5));
                coche.setDisponibilidad(rs.getBoolean(6));
                coche.setImagen(rs.getString(7));

                coches.add(coche);
            }

        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            motorSql.disconnect();
        }
        return coches;
    }


    @Override
    public Coche findPriceById(Integer e) {
        String sql = "SELECT precio FROM COCHE WHERE id_coche=" + e;

        Coche coche = null;
        try {
            this.motorSql.connect();
            ResultSet rs = this.motorSql.executeQuery(sql);
            if (rs.next()) {
                coche = new Coche();
                coche.setPrecio(rs.getFloat("precio"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CocheDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            this.motorSql.disconnect();
        }
        return coche;
    }
}
    
  