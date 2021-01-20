
package model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;

/**
 *
 * @author shady
 */

public class User {
    
    private String nombre, apellidos, email, password;
    private int idUser;

    public User() {
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getApellidos() {
        return apellidos;
    }
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public int getIdUser() {
        return idUser;
    }
    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
    
    public static String toArrayJSon(ArrayList<User> users) {
        GsonBuilder builder = new GsonBuilder(); 
        builder.setPrettyPrinting();

        Gson gson = builder.create();
        String resp = gson.toJson(users);
            
        return resp;
    }
        
    public static String toObjectJson(User user){
        GsonBuilder builder = new GsonBuilder(); 
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        String resp = gson.toJson(user);
        return resp;
    }
    
}
