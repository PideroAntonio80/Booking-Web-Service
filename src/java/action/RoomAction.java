
package action;

import dao.RoomDAO;
import dao.UserDAO;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Hotel;
import model.Room;
import model.User;

/**
 *
 * @author shady
 */
public class RoomAction implements interfaces.Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String cadDestino = "";
        String action = (String) request.getParameter("ACTION");
        String[] arrayAction = action.split("\\.");
        switch (arrayAction[1]) {
            case "FIND_ALL":
                cadDestino = findAll(request, response);
                break;
            case "FIND_SOME": 
                cadDestino = findSome(request, response);
                break;
        }
        return cadDestino;
    }

    private String findAll(HttpServletRequest request, HttpServletResponse response) {
        RoomDAO roomDAO = new RoomDAO();
        ArrayList<Room> lstRooms = roomDAO.findAll(null);
        return Room.toArrayJSon(lstRooms);
    }

    private String findSome(HttpServletRequest request, HttpServletResponse response) {
        String hotel = request.getParameter("HOTEL");
        Room room = new Room();
        room.setNombreHotel(hotel);
        RoomDAO roomDAO = new RoomDAO();
        ArrayList<Room> roomList = roomDAO.findSome(room);
        return Room.toArrayJSon(roomList);
    }
    
}
