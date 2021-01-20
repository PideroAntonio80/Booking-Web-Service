
package action;

import dao.HotelDAO;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Hotel;

/**
 *
 * @author shady
 */
public class HotelAction implements interfaces.Action{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String cadDestino = "";
        String action = (String) request.getParameter("ACTION");
        String[] arrayAction = action.split("\\.");
        switch (arrayAction[1]) {
            case "FIND_ALL":
                cadDestino = findAllC(request, response);
                break;
            case "FIND_SOME":
                cadDestino = findAll(request, response);
                break;  
        }
        return cadDestino;
    }
    private String findAllC(HttpServletRequest request, HttpServletResponse response) {
        HotelDAO hotelDAO = new HotelDAO();
        ArrayList<Hotel> lstHotels = hotelDAO.findAll();
        return Hotel.toArrayJSon(lstHotels);
    }

    private String findAll(HttpServletRequest request, HttpServletResponse response) {
        String localidad = request.getParameter("LOCATION");
        Hotel hotel = new Hotel();
        hotel.setNombre_location(localidad);
        HotelDAO hotelDAO = new HotelDAO();
        ArrayList<Hotel> lstHotels = hotelDAO.findAll(hotel);
        return Hotel.toArrayJSon(lstHotels);
    }    
}
