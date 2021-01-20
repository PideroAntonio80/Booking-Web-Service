
package dao;

import interfaces.IDAO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Location;
import model.User;
import utils.ConnectionFactory;
import utils.MotorSQL;

/**
 *
 * @author shady
 */
public class UserDAO implements IDAO<User, Integer>{
    
    private final String SQL_FINDALL
            = "SELECT * FROM `user` WHERE 1=1 ";
    private final String SQL_ADD
            = "INSERT INTO `user` (`name`, `surename`, `email`, `password`) VALUES ";
    private final String SQL_DELETE = "DELETE FROM `user` WHERE id=";
    private final String SQL_UPDATE = "UPDATE `user` SET ";
    
    private MotorSQL motorSql;

    public UserDAO() {
        motorSql = ConnectionFactory.selectDb();
    }
    
    @Override
    public ArrayList<User> findAll(User bean) {
        ArrayList<User> users = new ArrayList<>();
        String sql= SQL_FINDALL;
        try {
            //1º) 
            motorSql.connect();
            if (bean != null) {
                if (bean.getIdUser() != 0) {
                    sql += " AND id='" + bean.getIdUser()+ "'";
                }
                if (bean.getNombre() != null) {
                    sql += " AND name='" + bean.getNombre() + "'";
                }
                if (bean.getApellidos() != null) {
                    sql += " AND surename='" + bean.getApellidos() + "'";
                }
                if (bean.getEmail() != null) {
                    sql += " AND email='" + bean.getEmail() + "'";
                }
                if (bean.getPassword() != null) {
                    sql += " AND password='" + bean.getPassword() + "'";
                }
               
            }

            System.out.println(sql);
            ResultSet rs = motorSql.
                    executeQuery(sql);

            while (rs.next()) {
                User user = new User();

                user.setIdUser(rs.getInt(1));
                user.setNombre(rs.getString(2));
                user.setApellidos(rs.getString(3));
                user.setEmail(rs.getString(4));
                user.setPassword(rs.getString(5));

                users.add(user);
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            motorSql.disconnect();
        }
        return users;
    }

    @Override
    public int add(User bean) {
        int resp = 0;
        try {
            motorSql.connect();

            String sql = SQL_ADD + "('"
                    + bean.getNombre() + "', '"
                    + bean.getApellidos()+ "', '"
                    + bean.getEmail()+ "', '"
                    + bean.getPassword()+ "');";

            resp = motorSql.execute(sql);

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            motorSql.disconnect();

        }
        if (resp > 0) {
            System.out.println("Usuario insertado con exito.");
        }
        return resp;
    }
    
    public ArrayList<User> addOne(User bean) {
        UserDAO userDAO = null;
        ArrayList<User> users;
        User user = null;
        int resp = 0;
        try {
            motorSql.connect();

            String sql = SQL_ADD + "('"
                    + bean.getNombre() + "', '"
                    + bean.getApellidos()+ "', '"
                    + bean.getEmail()+ "', '"
                    + bean.getPassword()+ "');";

            resp = motorSql.execute(sql);
            users = new ArrayList<User>();
            user = new User();
            user.setNombre(bean.getNombre());
            user.setApellidos(bean.getApellidos());
            user.setEmail(bean.getEmail());
            user.setPassword(bean.getPassword());
            
            users.add(user);
            
            userDAO = new UserDAO();
            userDAO.findAll(user);

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            motorSql.disconnect();

        }
        if (resp > 0) {
            System.out.println("Usuario insertado con exito.");
        }
        return userDAO.findAll(user);
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
    public int update(User bean) {
        int resp = 0;
        String sql;
        try {
            motorSql.connect();

            if (bean == null) {
                System.out.println("Introduzca datos a modificar");
            } else {

                sql = SQL_UPDATE;
                if (bean.getNombre()!= null) {
                    sql += "name='" + bean.getNombre()+ "'";
                }

                if (bean.getApellidos()!= null) {
                    sql += "surename='" + bean.getApellidos()+ "'";
                }

                if (bean.getEmail()!= null) {
                    sql += "email='" + bean.getEmail()+ "'";
                }
                
                if (bean.getPassword()!= null) {
                    sql += "password='" + bean.getEmail()+ "'";
                }

                sql += " WHERE `id`=" + bean.getIdUser()+ ";";
                System.out.println(sql);
                resp = motorSql.execute(sql);
            }

        } catch (Exception e) {

        } finally {
            motorSql.disconnect();
        }

        if (resp > 0) {
            System.out.println("Usuario actualizado con éxito.");
        } else {
            System.out.println("No se pudo actualizar.");
        }
        return resp;
    }
    
}
