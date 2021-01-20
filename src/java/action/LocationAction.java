
package action;

import dao.HotelDAO;
import dao.LocationDAO;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Hotel;
import model.Location;

/**
 *
 * @author shady
 */
public class LocationAction implements interfaces.Action{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String cadDestino = "";
        String action = (String) request.getParameter("ACTION");
        String[] arrayAction = action.split("\\.");
        switch (arrayAction[1]) {
            case "FIND_ALL":
                cadDestino = findAll(request, response);
                break;
        }
        return cadDestino;
    }

    private String findAll(HttpServletRequest request, HttpServletResponse response) {
        LocationDAO locationDAO = new LocationDAO();
        ArrayList<Location> lstLocations = locationDAO.findAll(null);
        return Location.toArrayJSon(lstLocations);
    }
    
}
