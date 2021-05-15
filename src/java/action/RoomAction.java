
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
            case "UPDATE": 
                cadDestino = update(request, response);
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

    private String update(HttpServletRequest request, HttpServletResponse response) {
        int id_room = Integer.parseInt(request.getParameter("ROOM"));
        Room room = new Room();
        room.setId(id_room);
        room.setDisponible("no");
        RoomDAO roomDAO = new RoomDAO();
        roomDAO.update(room);
        return Room.toObjectJson(room);
    }
}
