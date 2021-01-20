package action;

import dao.LocationDAO;
import dao.UserDAO;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Location;
import model.User;

/**
 *
 * @author shady
 */
public class UserAction implements interfaces.Action{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String cadDestino = "";
        String action = (String) request.getParameter("ACTION");
        String[] arrayAction = action.split("\\.");
        switch (arrayAction[1]) {
            case "FIND_ALL":
                cadDestino = findAll(request, response);
                break;
            case "LOGIN":
                cadDestino = login(request, response);
                break; 
            case "ADD": 
                cadDestino = register(request, response);
                break;
        }
        return cadDestino;
    }

    private String findAll(HttpServletRequest request, HttpServletResponse response) {
        UserDAO userDAO = new UserDAO();
        ArrayList<User> lstUsers = userDAO.findAll(null);
        return User.toArrayJSon(lstUsers);
    }

    private String login(HttpServletRequest request, HttpServletResponse response) {    
        String email = request.getParameter("EMAIL");
        String password = request.getParameter("PASSWORD");
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        UserDAO userDAO = new UserDAO();
        ArrayList<User> users = userDAO.findAll(user);
        return User.toArrayJSon(users);
    }

    private String register(HttpServletRequest request, HttpServletResponse response) {
        String nombre = request.getParameter("NOMBRE");
        String apellidos = request.getParameter("APELLIDOS");
        String email = request.getParameter("EMAIL");
        String password = request.getParameter("PASSWORD");
        User user = new User();
        user.setNombre(nombre);
        user.setApellidos(apellidos);
        user.setEmail(email);
        user.setPassword(password);
        UserDAO userDAO = new UserDAO();
        userDAO.add(user);
        ArrayList<User> users = userDAO.findAll(user);
        return User.toArrayJSon(users);
    }
    
}
