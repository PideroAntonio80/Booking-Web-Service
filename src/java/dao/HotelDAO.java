
package dao;

import interfaces.IDAO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.BookingRoom;
import model.Hotel;
import model.Location;
import utils.ConnectionFactory;
import utils.MotorSQL;

/**
 *
 * @author shady
 */
public class HotelDAO implements IDAO<Hotel, Integer> {
    
    /*private final String SQL_FINDALL = "SELECT * FROM `hotel` WHERE 1=1 ";  <-- Para que me devuelva todos hoteles */
    /*private final String SQL_FINDALL = "SELECT hotel.*, location.nombre FROM hotel INNER JOIN location ON hotel.id_location = location.id WHERE 1=1";   <-- Para que me devuelva todos hoteles más nombre de localidad */
    private final String SQL_FINDSOME ="SELECT hotel.*, location.nombre, COUNT(booking_room.id) FROM booking_room INNER JOIN room ON room.id = booking_room.id_room RIGHT JOIN hotel ON room.id_hotel = hotel.id INNER JOIN location ON hotel.id_location = location.id WHERE 1=1";
    private final String SQL_FINDALL ="SELECT hotel.*, location.nombre, COUNT(booking_room.id) FROM booking_room INNER JOIN room ON room.id = booking_room.id_room RIGHT JOIN hotel ON room.id_hotel = hotel.id INNER JOIN location ON hotel.id_location = location.id GROUP BY hotel.id";
    private final String SQL_ADD
            = "INSERT INTO `hotel` (`nombre`, `descripcion`,`url_foto`, `id_location`) VALUES ";
    private final String SQL_DELETE = "DELETE FROM `hotel` WHERE id=";
    private final String SQL_UPDATE = "UPDATE `hotel` SET ";
    private final String SQL_GET_RESERVAS 
            = "SELECT hotel.*, location.nombre, COUNT(booking_room.id) FROM booking_room INNER JOIN room ON room.id = booking_room.id_room INNER JOIN hotel ON room.id_hotel = hotel.id GROUP BY hotel.nombre";
    
    private MotorSQL motorSql;

    public HotelDAO() {
        motorSql = ConnectionFactory.selectDb();
    }
    
    /* Creo este método findAll sin parámetros que me devuelve todos los datos 
    (Hoteles con todos sus datos, nombre localidad y número de reservas) */
    public ArrayList<Hotel> findAll() {
        ArrayList<Hotel> hoteles = new ArrayList<>();
       
        String sql= SQL_FINDALL;
        try {
             
            motorSql.connect();

            System.out.println(sql);
            ResultSet rs = motorSql.executeQuery(sql);

            while (rs.next()) {
                Hotel hotel = new Hotel();

                hotel.setIdHotel(rs.getInt(1));
                hotel.setNombre(rs.getString(2));
                hotel.setDescripcion(rs.getString(3));
                hotel.setUrl_foto(rs.getString(4));
                hotel.setId_location(rs.getInt(5));
                hotel.setPuntuacion(rs.getDouble(6));
                hotel.setPrecio_medio(rs.getDouble(7));
                hotel.setEstrellas(rs.getInt(8));
                hotel.setNombre_location(rs.getString(9));
                hotel.setNumReservas(rs.getInt(10));
                
                hoteles.add(hotel);

            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            motorSql.disconnect();
        }
        return hoteles;
    }
    
    @Override
    public ArrayList<Hotel> findAll(Hotel bean) {
        ArrayList<Hotel> hoteles = new ArrayList<>();
       
        String sql= SQL_FINDSOME;
        try {
            //1º) 
            motorSql.connect();
            if (bean != null) {
                if (bean.getIdHotel() != 0) {
                    sql += " AND id='" + bean.getIdHotel() + "'";
                }
                if (bean.getNombre() != null) {
                    sql += " AND nombre='" + bean.getNombre() + "'";
                }
                if (bean.getDescripcion() != null) {
                    sql += " AND descripcion='" + bean.getDescripcion() + "'";
                }
                if (bean.getUrl_foto() != null) {
                    sql += " AND url_foto='" + bean.getUrl_foto() + "'";
                }
                if (bean.getId_location() != 0) {
                    sql += " AND id_location='" + bean.getId_location() + "'";
                }
                if (bean.getPuntuacion()!= 0) {
                    sql += " AND puntuacion='" + bean.getPuntuacion() + "'";
                }
                if (bean.getPrecio_medio()!= 0) {
                    sql += " AND precio_medio='" + bean.getPrecio_medio() + "'";
                }
                if (bean.getEstrellas()!= 0) {
                    sql += " AND estrellas='" + bean.getEstrellas() + "'";
                }
                if (bean.getNombre_location()!= null) {
                    sql += " AND location.nombre='" + bean.getNombre_location() + "'";
                }
                if (bean.getNumReservas()!= 0) {
                    sql += " AND COUNT(booking_room.id)='" + bean.getNumReservas() + "'";
                }
                
            }
            
            sql += " GROUP BY hotel.id";

            System.out.println(sql);
            ResultSet rs = motorSql.executeQuery(sql);

            while (rs.next()) {
                Hotel hotel = new Hotel();

                hotel.setIdHotel(rs.getInt(1));
                hotel.setNombre(rs.getString(2));
                hotel.setDescripcion(rs.getString(3));
                hotel.setUrl_foto(rs.getString(4));
                hotel.setId_location(rs.getInt(5));
                hotel.setPuntuacion(rs.getDouble(6));
                hotel.setPrecio_medio(rs.getDouble(7));
                hotel.setEstrellas(rs.getInt(8));
                hotel.setNombre_location(rs.getString(9));
                hotel.setNumReservas(rs.getInt(10));
                
                hoteles.add(hotel);

            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            motorSql.disconnect();
        }
        return hoteles;
    }

    @Override
    public int add(Hotel bean) {
        int resp = 0;
        try {
            motorSql.connect();

            String sql = SQL_ADD + "('"
                    + bean.getNombre() + "', '"
                    + bean.getDescripcion() + "', '"
                    + bean.getUrl_foto()+ "', '"
                    + bean.getId_location()+ "');";

            resp = motorSql.execute(sql);
            
            /*FIN*/
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            motorSql.disconnect();

        }
        if (resp > 0) {
            System.out.println("Hotel insertado con exito.");
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
    public int update(Hotel bean) {
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

                if (bean.getDescripcion()!= null) {
                    sql += "descripcion='" + bean.getDescripcion()+ "'";
                }
                
                if (bean.getUrl_foto()!= null) {
                    sql += "url_foto='" + bean.getUrl_foto()+ "'";
                }

                if (bean.getId_location()> 0) {
                    sql += "id_location='" + bean.getId_location()+ "'";
                }

                sql += " WHERE `id`=" + bean.getIdHotel() + ";";
                System.out.println(sql);
                resp = motorSql.execute(sql);
            }

        } catch (Exception e) {

        } finally {
            motorSql.disconnect();
        }

        if (resp > 0) {
            System.out.println("Hotel actualizado con éxito.");
        } else {
            System.out.println("No se pudo actualizar.");
        }
        return resp;
    }
    
    public ArrayList<Hotel> getReservas() {
        ArrayList<Hotel> hoteles = new ArrayList<>();
        Hotel hotel = new Hotel();
        String sql= SQL_GET_RESERVAS;
        try {
            
            motorSql.connect();

            System.out.println(sql);
            ResultSet rs = motorSql.executeQuery(sql);

            while (rs.next()) {
                hotel.setNombre(rs.getString(1));
                hotel.setNumReservas(rs.getInt(2));
                
                hoteles.add(hotel);

            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            motorSql.disconnect();
        }
        return hoteles;
    }
    
}
