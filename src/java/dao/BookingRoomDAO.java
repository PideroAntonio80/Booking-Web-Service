
package dao;

import interfaces.IDAO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.BookingRoom;
import model.Room;
import utils.ConnectionFactory;
import utils.MotorSQL;

/**
 *
 * @author shady
 */

public class BookingRoomDAO implements IDAO<BookingRoom, Integer> {
    
    private final String SQL_FINDALL
            = "SELECT * FROM `booking_room` WHERE 1=1 ";
    private final String SQL_ADD
            = "INSERT INTO `booking_room` (`date_in`, `date_out`, `nights`, `num_person`, `prize`, `id_user`, `id_room`) VALUES ";
    private final String SQL_DELETE = "DELETE FROM `booking_room` WHERE id=";
    private final String SQL_UPDATE = "UPDATE `booking_room` SET ";
    
    private MotorSQL motorSql;

    public BookingRoomDAO() {
        motorSql = ConnectionFactory.selectDb();
    }
    
    @Override
    public ArrayList<BookingRoom> findAll(BookingRoom bean) {
         ArrayList<BookingRoom> bookingRooms = new ArrayList<>();
        String sql= SQL_FINDALL;
        try {
            //1º) 
            motorSql.connect();
            if (bean != null) {
                if (bean.getIdBookinRoom()!= 0) {
                    sql += "AND id='" + bean.getIdBookinRoom()+ "'";
                }
                if (bean.getDateIn()!= null) {
                    sql += "AND date_in='" + bean.getDateIn() + "'";
                }
                if (bean.getDateOut()!= null) {
                    sql += "AND date_out='" + bean.getDateOut() + "'";
                }
                if (bean.getNoches()!= 0) {
                    sql += "AND nights='" + bean.getNoches()+ "'";
                }
                if (bean.getNumPerson()!= 0) {
                    sql += "AND num_person='" + bean.getNumPerson() + "'";
                }
                if (bean.getPrecio()!= 0) {
                    sql += "AND prize='" + bean.getPrecio()+ "'";
                }
                if (bean.getIdUser()!= 0) {
                    sql += "AND id_user='" + bean.getIdUser()+ "'";
                }
                if (bean.getIdRoom()!= 0) {
                    sql += "AND id_room='" + bean.getIdRoom()+ "'";
                }
               
            }

            System.out.println(sql);
            ResultSet rs = motorSql.
                    executeQuery(sql);

            while (rs.next()) {
                BookingRoom bookingRoom = new BookingRoom();

                bookingRoom.setIdBookinRoom(rs.getInt(1));
                bookingRoom.setDateIn(rs.getDate(2));
                bookingRoom.setDateOut(rs.getDate(3));
                bookingRoom.setNoches(rs.getInt(4));
                bookingRoom.setNumPerson(rs.getInt(5));
                bookingRoom.setPrecio(rs.getDouble(6));
                bookingRoom.setIdUser(rs.getInt(7));
                bookingRoom.setIdRoom(rs.getInt(8));

                bookingRooms.add(bookingRoom);
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            motorSql.disconnect();
        }
        return bookingRooms;
    }

    @Override
    public int add(BookingRoom bean) {
        int resp = 0;
        try {
            motorSql.connect();

            String sql = SQL_ADD + "("
                    + bean.getDateIn()+ ", "
                    + bean.getDateOut()+ ", "
                    + bean.getNoches()+ ", "
                    + bean.getNumPerson()+ ", "
                    + bean.getPrecio()+ ", "
                    + bean.getIdUser()+ ", "
                    + bean.getIdRoom()+ ")";
            
            //desactivo la restriccion de claves foráneas para insert
            //motorSql.execute("SET FOREIGN_KEY_CHECKS=0;");
            resp = motorSql.execute(sql);
            //vuelvo a activar la restricción de claves foráneas
            //motorSql.execute("SET FOREIGN_KEY_CHECKS=1;");

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            motorSql.disconnect();

        }
        if (resp > 0) {
            System.out.println("Reserva insertada con exito.");
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
    public int update(BookingRoom bean) {
        int resp = 0;
        String sql;
        try {
            motorSql.connect();

            if (bean == null) {
                System.out.println("Introduzca datos a modificar");
            } else {

                sql = SQL_UPDATE;
                if (bean.getDateIn()!= null) {
                    sql += "date_in='" + bean.getDateIn()+ "'";
                }
                if (bean.getDateOut()!= null) {
                    sql += "date_out='" + bean.getDateOut()+ "'";
                }
                if (bean.getNoches()!= 0) {
                    sql += "nights='" + bean.getNoches()+ "'";
                }               
                if (bean.getNumPerson()!= 0) {
                    sql += "num_person='" + bean.getNumPerson()+ "'";
                }
                if (bean.getPrecio()!= 0) {
                    sql += "prize='" + bean.getPrecio()+ "'";
                }
                if (bean.getIdUser()!= 0) {
                    sql += "id_user='" + bean.getIdUser()+ "'";
                }
                if (bean.getIdRoom()!= 0) {
                    sql += "id_room='" + bean.getIdRoom()+ "'";
                }

                sql += " WHERE `id`=" + bean.getIdBookinRoom()+ ";";
                System.out.println(sql);
                resp = motorSql.execute(sql);
            }

        } catch (Exception e) {

        } finally {
            motorSql.disconnect();
        }

        if (resp > 0) {
            System.out.println("Reserva actualizada con éxito.");
        } else {
            System.out.println("No se pudo actualizar.");
        }
        return resp;
    }
    
}
