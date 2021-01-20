
package dao;

import interfaces.IDAO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Location;
import utils.ConnectionFactory;
import utils.MotorSQL;

/**
 *
 * @author shady
 */
public class LocationDAO implements IDAO<Location, Integer>{
    private final String SQL_FINDALL
            = "SELECT * FROM `location` WHERE 1=1 ";
    private final String SQL_GETNAME
            = "SELECT nombre FROM location WHERE id =";
    private final String SQL_ADD
            = "INSERT INTO `location` (`nombre`) VALUES ";
    private final String SQL_DELETE = "DELETE FROM `location` WHERE id=";
    private final String SQL_UPDATE = "UPDATE `location` SET ";
    
    private MotorSQL motorSql;

    public LocationDAO() {
        motorSql = ConnectionFactory.selectDb();
    }

    @Override
    public ArrayList<Location> findAll(Location bean) {
        ArrayList<Location> locations = new ArrayList<>();
        String sql= SQL_FINDALL;
        try {
            //1º) 
            motorSql.connect();
            if (bean != null) {
                if (bean.getId() != 0) {
                    sql += "AND id='" + bean.getId()+ "'";
                }
                if (bean.getNombre() != null) {
                    sql += "AND nombre='" + bean.getNombre() + "'";
                }
               
            }

            System.out.println(sql);
            ResultSet rs = motorSql.
                    executeQuery(sql);

            while (rs.next()) {
                Location location = new Location();

                location.setId(rs.getInt(1));
                location.setNombre(rs.getString(2));

                locations.add(location);

            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            motorSql.disconnect();
        }
        return locations;
    }

    @Override
    public int add(Location bean) {
        int resp = 0;
        try {
            motorSql.connect();

            String sql = SQL_ADD + "('"
                    + bean.getNombre()+ "');";

            resp = motorSql.execute(sql);

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            motorSql.disconnect();

        }
        if (resp > 0) {
            System.out.println("Localidad insertada con exito.");
        }
        return resp;
    }
    
    @Override
    public int delete(Integer id) {
        int resp = 0;
        motorSql.connect();
        try {
            String sql = SQL_DELETE + id;
            //desactivo la restriccion de claves foráneas para borrado
            motorSql.execute("SET FOREIGN_KEY_CHECKS=0;");
            resp = motorSql.execute(sql);
            //vuelvo a activar la restricción de claves foráneas
            motorSql.execute("SET FOREIGN_KEY_CHECKS=1;");

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            motorSql.disconnect();

        }
        if (resp > 0) {
            System.out.println("Borrado con exito.");
        } else {
            System.out.println("No se pudo borrar.");
        }
        return resp;
    }

    @Override
    public int update(Location bean) {
        int resp = 0;
        String sql;
        try {
            motorSql.connect();

            if (bean == null) {
                System.out.println("Introduzca datos a modificar");
            } else {

                sql = SQL_UPDATE;
                if (bean.getNombre()!= null) {
                    sql += "nombre='" + bean.getNombre()+ "'";
                }

                sql += " WHERE `id`=" + bean.getId() + ";";
                System.out.println(sql);
                resp = motorSql.execute(sql);
            }

        } catch (Exception e) {

        } finally {
            motorSql.disconnect();
        }

        if (resp > 0) {
            System.out.println("Localidad actualizada con éxito.");
        } else {
            System.out.println("No se pudo actualizar.");
        }
        return resp;
    }
    
    public String getNombreLocation(int id) {
        String location = "";
        String sql = SQL_GETNAME;
        sql += id;
        
        ResultSet rs = motorSql.executeQuery(sql);
        try {
            if(rs.next()) {
                location = rs.getString("nombre");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(LocationDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            motorSql.disconnect();
        }
        return location;   
    }
    
           
    
}
