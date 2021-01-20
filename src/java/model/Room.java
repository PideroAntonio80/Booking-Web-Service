
package model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;

/**
 *
 * @author shady
 */
public class Room {
    
    private String disponible, nombreHotel, localidad;
    private int idRoom, capacidad, hotel;
    private double precio;

    public Room() {
    }

    public String getDisponible() {
        return disponible;
    }

    public void setDisponible(String disponible) {
        this.disponible = disponible;
    }

    public int getHotel() {
        return hotel;
    }

    public void setHotel(int hotel) {
        this.hotel = hotel;
    }

    public int getId() {
        return idRoom;
    }

    public void setId(int id) {
        this.idRoom = id;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getNombreHotel() {
        return nombreHotel;
    }

    public void setNombreHotel(String nombreHotel) {
        this.nombreHotel = nombreHotel;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }
    
    
    
    
    
    public static String toArrayJSon(ArrayList<Room> rooms) {
        GsonBuilder builder = new GsonBuilder(); 
        builder.setPrettyPrinting();

        Gson gson = builder.create();
        String resp = gson.toJson(rooms);
            
        return resp;
    }
        
    public static String toObjectJson(Room room){
        GsonBuilder builder = new GsonBuilder(); 
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        String resp = gson.toJson(room);
        return resp;
    }
    
}
