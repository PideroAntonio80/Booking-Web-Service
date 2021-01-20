
package model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;

/**
 *
 * @author shady
 */
public class Hotel {
    
    private String nombre, descripcion, url_foto, nombre_location;
    private int idHotel, id_location, estrellas, numReservas;
    private double puntuacion, precio_medio;

    public Hotel(String nombre, String descripcion, int idHotel, int id_location, String url_foto, double puntuacion, double precio_medio, int estrellas, String nombre_location, int numReservas) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.idHotel = idHotel;
        this.id_location = id_location;
        this.url_foto = url_foto;
        this.puntuacion = puntuacion;
        this.precio_medio = precio_medio;
        this.estrellas = estrellas;
        this.nombre_location = nombre_location;
        this.numReservas = numReservas;
    }
    

    public Hotel() {
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public String getUrl_foto() {
        return url_foto;
    }
    public void setUrl_foto(String url_foto) {
        this.url_foto = url_foto;
    }
    public int getIdHotel() {
        return idHotel;
    }
    public void setIdHotel(int idHotel) {
        this.idHotel = idHotel;
    }
    public int getId_location() {
        return id_location;
    }
    public void setId_location(int id_location) {
        this.id_location = id_location;
    }
    public double getPuntuacion() {
        return puntuacion;
    }
    public void setPuntuacion(double puntuacion) {
        this.puntuacion = puntuacion;
    }
    public double getPrecio_medio() {
        return precio_medio;
    }
    public void setPrecio_medio(double precio_medio) {
        this.precio_medio = precio_medio;
    }
    public int getEstrellas() {
        return estrellas;
    }
    public void setEstrellas(int estrellas) {
        this.estrellas = estrellas;
    }
    public String getNombre_location() {
        return nombre_location;
    }
    public void setNombre_location(String nombre_location) {
        this.nombre_location = nombre_location;
    }
    public int getNumReservas() {
        return numReservas;
    }
    public void setNumReservas(int numReservas) {
        this.numReservas = numReservas;
    }
    
    public static String toArrayJSon(ArrayList<Hotel> hotels) {
        GsonBuilder builder = new GsonBuilder(); 
        builder.setPrettyPrinting();

        Gson gson = builder.create();
        String resp = gson.toJson(hotels);
            
        return resp;
    }
        
    public static String toObjectJson(Hotel hotel){
        GsonBuilder builder = new GsonBuilder(); 
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        String resp = gson.toJson(hotel);
        return resp;
    }
  
}
