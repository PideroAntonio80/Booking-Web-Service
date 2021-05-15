
package dao;

import interfaces.IDAO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Hotel;
import model.Room;
import model.User;
import utils.ConnectionFactory;
import utils.MotorSQL;

/**
 *
 * @author shady
 */
public class RoomDAO implements IDAO<Room, Integer>{
    
    private final String SQL_FINDSOME = "SELECT room.*, hotel.nombre, location.nombre FROM room INNER JOIN hotel ON hotel.id = room.id_hotel INNER JOIN location ON location.id = hotel.id_location WHERE 1=1 ";
    private final String SQL_FINDALL
            = "SELECT * FROM `room` WHERE 1=1 ";
    private final String SQL_ADD
            = "INSERT INTO `room` (`capacity`, `prize`, `available`, `id_hotel`) VALUES ";
    private final String SQL_DELETE = "DELETE FROM `room` WHERE id=";
    private final String SQL_UPDATE = "UPDATE `room` SET ";
    
    private MotorSQL motorSql;

    public RoomDAO() {
        motorSql = ConnectionFactory.selectDb();
    }
    
    public ArrayList<Room> findSome(Room bean) {
        ArrayList<Room> rooms = new ArrayList<>();
        String sql= SQL_FINDSOME;
        try {
            //1º) 
            motorSql.connect();
            if (bean != null) {
                if (bean.getNombreHotel()!= null) {
                    sql += "AND hotel.nombre='" + bean.getNombreHotel()+ "'";
                }
               
            }
            sql += " AND available='si'";

            System.out.println(sql);
            ResultSet rs = motorSql.
                    executeQuery(sql);

            while (rs.next()) {
                Room room = new Room();

                room.setId(rs.getInt(1));
                room.setCapacidad(rs.getInt(2));
                room.setPrecio(rs.getDouble(3));
                room.setDisponible(rs.getString(4));
                room.setHotel(rs.getInt(5));
                room.setNombreHotel(rs.getString(6));
                room.setLocalidad(rs.getString(7));

                rooms.add(room);
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            motorSql.disconnect();
        }
        return rooms;
    }
    
    @Override
    public ArrayList<Room> findAll(Room bean) {
        ArrayList<Room> rooms = new ArrayList<>();
        String sql= SQL_FINDALL;
        try {
            //1º) 
            motorSql.connect();
            if (bean != null) {
                if (bean.getId()!= 0) {
                    sql += "AND id='" + bean.getCapacidad()+ "'";
                }
                if (bean.getCapacidad()!= 0) {
                    sql += "AND capacity='" + bean.getCapacidad() + "'";
                }
                if (bean.getPrecio()!= 0) {
                    sql += "AND prize='" + bean.getPrecio()+ "'";
                }
                if (bean.getDisponible()!= null) {
                    sql += "AND available='" + bean.getDisponible() + "'";
                }
                if (bean.getHotel()!= 0) {
                    sql += "AND id_hotel='" + bean.getHotel()+ "'";
                }
               
            }

            System.out.println(sql);
            ResultSet rs = motorSql.
                    executeQuery(sql);

            while (rs.next()) {
                Room room = new Room();

                room.setId(rs.getInt(1));
                room.setCapacidad(rs.getInt(2));
                room.setPrecio(rs.getDouble(3));
                room.setDisponible(rs.getString(4));
                room.setHotel(rs.getInt(5));

                rooms.add(room);
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            motorSql.disconnect();
        }
        return rooms;
    }

    @Override
    public int add(Room bean) {
        int resp = 0;
        try {
            motorSql.connect();

            String sql = SQL_ADD + "('"
                    + bean.getCapacidad()+ "', '"
                    + bean.getPrecio()+ "', '"
                    + bean.getDisponible()+ "', '"
                    + bean.getHotel()+ "');";

            resp = motorSql.execute(sql);

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            motorSql.disconnect();

        }
        if (resp > 0) {
            System.out.println("Habitacion insertada con exito.");
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
    public int update(Room bean) {
        int resp = 0;
        String sql;
        try {
            motorSql.connect();

            if (bean == null) {
                System.out.println("Introduzca datos a modificar");
            } else {

                sql = SQL_UPDATE;
                if (bean.getCapacidad()!= 0) {
                    sql += "capacity='" + bean.getCapacidad()+ "'";
                }

                if (bean.getPrecio()!= 0) {
                    sql += "prize='" + bean.getPrecio()+ "'";
                }

                if (bean.getDisponible()!= null) {
                    sql += "available='" + bean.getDisponible()+ "'";
                }
                
                if (bean.getHotel()!= 0) {
                    sql += "id_hotel='" + bean.getHotel()+ "'";
                }

                sql += " WHERE `id`=" + bean.getId()+ ";";
                System.out.println(sql);
                resp = motorSql.execute(sql);
            }

        } catch (Exception e) {

        } finally {
            motorSql.disconnect();
        }

        if (resp > 0) {
            System.out.println("Habitacion actualizada con éxito.");
        } else {
            System.out.println("No se pudo actualizar.");
        }
        return resp;
    }
    
}
