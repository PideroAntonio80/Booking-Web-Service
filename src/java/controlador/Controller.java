/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import action.BookingRoomAction;
import action.HotelAction;
import action.LocationAction;
import action.RoomAction;
import action.UserAction;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author shady
 */
@WebServlet(name = "Controller", urlPatterns = {"/Controller"})
public class Controller extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/plain;charset=UTF-8");
            PrintWriter out = response.getWriter();
            String action = request.getParameter("ACTION");
            //?ACTION=HOTEL.FIND_ALL
            //?ACTION=LOCATION.FIND_ALL
            //?ACTION=USER.FIND_ALL
            //?ACTION=ROOM.FIND_ALL
            //?ACTION=BOOKINGROOM.FIND_ALL
            String arrayAction[] = action.split("\\.");
            switch (arrayAction[0]) {
                case "HOTEL":
                    out.print(new HotelAction().execute(request, response));
                    break;
                case "LOCATION":
                    out.print(new LocationAction().execute(request, response));
                    break;
                case "USER":
                    out.print(new UserAction().execute(request, response));
                    break;
                case "ROOM":
                    out.print(new RoomAction().execute(request, response));
                    break;  
                case "BOOKINGROOM":
                    out.print(new BookingRoomAction().execute(request, response));
                    break;   
                default:
                    break;
            }
            
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
