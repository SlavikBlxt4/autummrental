package utils;

import java.sql.*;

// Clase ABSTRACT que sirve de molde para todos los motores.
public abstract class MotorSQL {
    protected Connection conn;
    protected Statement st;
    // protected PrepareStatement preSt;
    protected ResultSet rs;
    public abstract void connect();
    public abstract void disconnect();
    public abstract ResultSet executeQuery(String SQL);
    public abstract int execute(String SQL);
}
