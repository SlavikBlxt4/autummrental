package utils;


//commit de prueba

import java.sql.*;
import java.util.Properties;


public class MotorPostgres extends MotorSQL {
    private Properties properties;

    private static final String URL = "jdbc:postgresql://autumnrentaldatabase.ckao0shs26e2.us-east-1.rds.amazonaws.com/postgres";
    private static final String CONTROLADOR = "org.postgresql.Driver";
    private static final String USER = "postgres";
    private static final String PASS = "xavineta753";
    private static final String SSL = "false";

    // Constructo   r private
    private MotorPostgres(){
        this.properties = new Properties();
            this.properties.setProperty("user", USER);
            this.properties.setProperty("password", PASS);
            this.properties.setProperty("ssl", SSL);
    }

    // Patrón Singleton
    private static MotorPostgres instance = null;
    public static MotorPostgres getInstance() {
        if (instance == null) {
            instance = new MotorPostgres();
        }
        return instance;  // La única instancia de MotorPostgres
    }


    @Override
    public void connect() {
        try {
            Class.forName(CONTROLADOR);
            conn = DriverManager.getConnection(URL, properties);
            st = conn.createStatement();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void disconnect() {
        try {
            if (rs != null) {
                rs.close();

            }
            if (st != null) {
                st.close();

            }
            if (conn != null) {
                conn.close();

            }
        } catch (SQLException ex) {

        }
    }
    @Override
    public ResultSet executeQuery(String SQL) {
        try {
            super.rs = this.st.executeQuery(SQL);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return super.rs;
    }
    @Override
    public int execute(String SQL) {
        int filasModificadas = 0;
        try {
            filasModificadas = this.st.executeUpdate(SQL);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return filasModificadas;
    }



}
