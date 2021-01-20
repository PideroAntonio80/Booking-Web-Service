
package model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;

/**
 *
 * @author shady
 */
public class Location {
    
    private String nombre;
    private int idLocation;

    public Location() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId() {
        return idLocation;
    }

    public void setId(int id) {
        this.idLocation = id;
    }
    
    public static String toArrayJSon(ArrayList<Location> locations) {
        GsonBuilder builder = new GsonBuilder(); 
        builder.setPrettyPrinting();

        Gson gson = builder.create();
        String resp = gson.toJson(locations);
            
        return resp;
    }
        
    public static String toObjectJson(Location location){
        GsonBuilder builder = new GsonBuilder(); 
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        String resp = gson.toJson(location);
        return resp;
    }
    
}
