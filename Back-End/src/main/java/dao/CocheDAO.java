package dao;

import entities.Coche;
import entities.Reserva;
import entities.Usuario;
import utils.MotorSQL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CocheDAO implements CocheDAOInterface {


    private final String SQL_ADD
            = "INSERT INTO COCHE (MODELO, DETALLES, DESCRIPCION, PRECIO, IMAGEN) VALUES( ";

    private final String SQL_FINDALL
            = "SELECT * FROM COCHE ORDER BY ID_COCHE ASC";
    private final String SQL_DELETE = "DELETE FROM COCHE WHERE ID_COCHE=";

    private final String SQL_UPDATE = "UPDATE COCHE SET ";
    private final String SQL_FINDPRICE = "SELECT PRECIO FROM COCHE WHERE ID_COCHE=";
    private final String SQL_FINDBETWEENDATES = "SELECT * FROM COCHE\n" +
            "WHERE ID_COCHE NOT IN (SELECT ID_COCHE\n" +
            "\tFROM RESERVA\n ";
            /*"\tWHERE (FECHA_INICIO BETWEEN '2024-05-20' AND '2024-05-22') OR\n" +
            "\t\t\t(FECHA_FINAL BETWEEN '2024-05-20' AND '2024-05-22'));";*/


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
                coche.setImagen(rs.getString(6));

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
    public Coche findPriceById(int e) {
        String sql = SQL_FINDPRICE + e;

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

    @Override
    public ArrayList<Coche> findBetweenDates(Reserva bean) {

        /*"\tWHERE (FECHA_INICIO BETWEEN '2024-05-20' AND '2024-05-22') OR\n" +
            "\t\t\t(FECHA_FINAL BETWEEN '2024-05-20' AND '2024-05-22'));";*/
        this.motorSql.connect();
        String sql = SQL_FINDBETWEENDATES;
        sql+= "WHERE (FECHA_INICIO BETWEEN '"+bean.getFecha_inicio()+"' AND '"+bean.getFecha_fin()+"') OR\n" +
            "\t\t\t(FECHA_FINAL BETWEEN '"+bean.getFecha_inicio()+"' AND '"+bean.getFecha_fin()+"'));";
        ResultSet rs = this.motorSql.executeQuery(sql);

        ArrayList<Coche> coches = new ArrayList<>();
        try {
            while (rs.next()) {
                Coche coche = new Coche();
                coche.setId_coche(rs.getInt(1));
                coche.setModelo(rs.getString(2));
                coche.setDetalles(rs.getString(3));
                coche.setDescripcion(rs.getString(4));
                coche.setPrecio(rs.getFloat(5));
                coche.setImagen(rs.getString(6));
                coches.add(coche);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CocheDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            this.motorSql.disconnect();
        }

        return coches;
    }


}
    
  