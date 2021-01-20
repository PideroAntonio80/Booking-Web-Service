
package action;

import dao.BookingRoomDAO;
import dao.RoomDAO;
import java.util.ArrayList;
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
        BookingRoom bookingRoom = new BookingRoom();
        bookingRoom.setIdUser(user);
        bookingRoom.setIdRoom(room);
        bookingRoom.setDateIn(null);
        bookingRoom.setDateOut(null);
        bookingRoom.setNoches(0);
        bookingRoom.setNumPerson(0);
        bookingRoom.setPrecio(0);
        BookingRoomDAO bookingRoomDAO = new BookingRoomDAO();
        bookingRoomDAO.add(bookingRoom);
        return BookingRoom.toObjectJson(bookingRoom);
    }
    
}
