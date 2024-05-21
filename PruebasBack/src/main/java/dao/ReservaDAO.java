package dao;

import entities.Coche;
import entities.Reserva;
import utils.MotorSQL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ReservaDAO implements DAO<Reserva, Integer> {


    private final String SQL_ADD
            = "INSERT INTO RESERVA (MODELO, DETALLES, DESCRIPCION, PRECIO, DISPONIBILIDAD, IMAGEN) VALUES( ";

    private final String SQL_FINDALL
            = "SELECT * FROM RESERVA";
    /*
    private final String SQL_FIND_BY_FILTER =
            "SELECT p.titulo, p.descripcion, p.ano, c.nombre " +
                    "FROM peliculas p " +
                    "INNER JOIN peliculas_categorias pc ON p.id = pc.id_pelicula " +
                    "INNER JOIN categorias c ON pc.id_categoria = c.id ";

    private final String SQL_SEARCH_Start
            = "SELECT * FROM pelicula WHERE ID_Pelicula IN ( SELECT ID_Pelicula FROM clasificar WHERE ID_Clasificacion IN ( SELECT ID_Clasificacion FROM clasificar WHERE ID_Genero IN (SELECT ID_Genero FROM genero WHERE UPPER(Genero) LIKE '%";
    private final String SQL_SEARCH_Final
            ="%')))";
    private final String SQL_DELETE = "DELETE FROM `pelicula` WHERE ID_Pelicula=";
    private final String SQL_UPDATE = "UPDATE `pelicula` SET ";
    */

    private MotorSQL motorSql;

    public ReservaDAO(MotorSQL motorSql) {
        this.motorSql = motorSql;
    }


    @Override
    public int add(Reserva bean) {
            this.motorSql.connect();
        String sql = "";
        sql+= "INSERT INTO RESERVA (ID_USUARIO, ID_COCHE, FECHA_INICIO, FECHA_FINAL, PRECIO) VALUES( ";
        sql+= bean.getId_usuario();
        sql+= ", ";
        sql+= bean.getId_coche();
        sql+= ", '";
        sql+= bean.getFecha_inicio();
        sql+= "', '";
        sql+= bean.getFecha_fin();
        sql+= "', ";
        sql+= bean.getPrecio();
        sql+= ");";

        //int lastId = "SELECT MAX(ID) from COCHE";

        // bean.setId(lastId); //999

        int filasModificadas = this.motorSql.execute(sql);
        this.motorSql.disconnect();

        return filasModificadas;
    }

    @Override
    public int delete(Integer e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int update(Reserva bean) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Reserva> findAll(Reserva bean) {
        ArrayList<Reserva> reservas = new ArrayList<>();
        String sql = SQL_FINDALL;
        try {
            //1º)
            motorSql.connect();

            ResultSet rs = motorSql.executeQuery(sql);

            while (rs.next()) { // TRANSFOMAR LA COLECCIÓN DEBASE DE DATOS A UN ARRAYLIST
                Reserva reserva = new Reserva();

                reserva.setId_reserva(rs.getInt("ID_RESERVA"));
                reserva.setId_usuario(rs.getInt("ID_USUARIO"));
                reserva.setId_coche(rs.getInt("ID_COCHE"));
                reserva.setFecha_inicio(rs.getDate("FECHA_INICIO").toLocalDate());
                reserva.setFecha_fin(rs.getDate("FECHA_FINAL").toLocalDate());
                reserva.setPrecio(rs.getFloat("PRECIO"));

                reservas.add(reserva);
            }

        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            motorSql.disconnect();
        }
        return reservas;
    }

}
    
  