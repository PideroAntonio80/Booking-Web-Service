
package action;

import dao.BookingRoomDAO;
import dao.RoomDAO;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.BookingRoom;
import model.Room;

/**
 *
 * @author shady
 */

public class BookingRoomAction implements interfaces.Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String cadDestino = "";
        String action = (String) request.getParameter("ACTION");
        String[] arrayAction = action.split("\\.");
        switch (arrayAction[1]) {
            case "FIND_ALL":
                cadDestino = findAll(request, response);
                break;
            case "INSERT":
                cadDestino = insert(request, response);
                break;    
        }
        return cadDestino;
    }

    private String findAll(HttpServletRequest request, HttpServletResponse response) {
        BookingRoomDAO bookingRoomDAO = new BookingRoomDAO();
        ArrayList<BookingRoom> lstBookingRooms = bookingRoomDAO.findAll(null);
        return BookingRoom.toArrayJSon(lstBookingRooms);
    }

    private String insert(HttpServletRequest request, HttpServletResponse response) {
        Integer user = Integer.parseInt(request.getParameter("USER"));
        Integer room = Integer.parseInt(request.getParameter("ROOM"));
        Integer nights = Integer.parseInt(request.getParameter("NIGHTS"));
        Double precioTotal = Double.parseDouble(request.getParameter("PRIZE"));
        Integer persons = Integer.parseInt(request.getParameter("PERSONS"));
        String dateIn = request.getParameter("DATEIN");
        String dateOut = request.getParameter("DATEOUT");
           
        BookingRoom bookingRoom = new BookingRoom();
        bookingRoom.setIdUser(user);
        bookingRoom.setIdRoom(room);
        bookingRoom.setDateIn(dateIn);
        bookingRoom.setDateOut(dateOut);
        bookingRoom.setNoches(nights);
        bookingRoom.setNumPerson(persons);
        bookingRoom.setPrecio(precioTotal);
        BookingRoomDAO bookingRoomDAO = new BookingRoomDAO();
        bookingRoomDAO.add(bookingRoom);
        
        return BookingRoom.toObjectJson(bookingRoom);
            
    }
    
}
