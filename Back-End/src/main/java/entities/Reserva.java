
package entities;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import utils.LocalDateTypeAdapter;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;


public class Reserva {
    private int id_reserva;
    private int id_usuario;
    private int id_coche;
    private String modelo_coche;
    private String imagen_coche;

    public String getModelo_coche() {
        return modelo_coche;
    }

    public void setModelo_coche(String modelo_coche) {
        this.modelo_coche = modelo_coche;
    }

    public String getImagen_coche() {
        return imagen_coche;
    }

    public void setImagen_coche(String imagen_coche) {
        this.imagen_coche = imagen_coche;
    }

    private LocalDate fecha_inicio;
    private LocalDate fecha_fin;
    private float precio;

    public Reserva() {
    }

    public int getId_reserva() {
        return id_reserva;
    }

    public void setId_reserva(int id_reserva) {
        this.id_reserva = id_reserva;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public int getId_coche() {
        return id_coche;
    }

    public void setId_coche(int id_coche) {
        this.id_coche = id_coche;
    }

    public LocalDate getFecha_inicio() {
        return fecha_inicio;
    }


    public void setFecha_inicio(LocalDate fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }


    public LocalDate getFecha_fin() {
        return fecha_fin;
    }


    public void setFecha_fin(LocalDate fecha_fin) {
        this.fecha_fin = fecha_fin;
    }



    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public static String fromObjectToJSON(Reserva coche) {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();

        Gson gson = builder.create();
        String resp = gson.toJson(coche);

        return resp;
    }

    public static String toArrayJSon(ArrayList<Reserva> coches) {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();


        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter())
                .create();

        String jsonString = gson.toJson(coches);
        return jsonString;
    }
}
