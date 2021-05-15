
package model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author shady
 */
public class BookingRoom {
    
    private int idBookinRoom, numPerson, idRoom, idUser, noches;
    private String dateIn, dateOut;
    private double precio;

    public BookingRoom() {
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdBookinRoom() {
        return idBookinRoom;
    }

    public void setIdBookinRoom(int idBookinRoom) {
        this.idBookinRoom = idBookinRoom;
    }

    public int getNumPerson() {
        return numPerson;
    }

    public void setNumPerson(int numPerson) {
        this.numPerson = numPerson;
    }

    public int getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(int idRoom) {
        this.idRoom = idRoom;
    }

    public String getDateIn() {
        return dateIn;
    }

    public void setDateIn(String dateIn) {
        this.dateIn = dateIn;
    }

    public String getDateOut() {
        return dateOut;
    }

    public void setDateOut(String dateOut) {
        this.dateOut = dateOut;
    }
    
    public int getNoches() {
        return noches;
    }

    public void setNoches(int noches) {
        this.noches = noches;
        
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
    
    public static String toArrayJSon(ArrayList<BookingRoom> bookingrooms) {
        GsonBuilder builder = new GsonBuilder(); 
        builder.setPrettyPrinting();

        Gson gson = builder.create();
        String resp = gson.toJson(bookingrooms);
            
        return resp;
    }
        
    public static String toObjectJson(BookingRoom bookingroom){
        GsonBuilder builder = new GsonBuilder(); 
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        String resp = gson.toJson(bookingroom);
        return resp;
    }
 
}
