package dao;

import db.Myconnection;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class UserDAO {
    public static boolean isExist(String email)throws SQLException {
        Connection connection =Myconnection.getConnection();
        PreparedStatement ps = connection.prepareStatement("select email from users");
       ResultSet rs =  ps.executeQuery();
       while (rs.next()){
           String e = rs.getString(1);
           if(e.equals(email)) {
               return true;
           }
       }
       return false;

    }
    public static int saveUser(User user) throws SQLException{
        Connection connection = Myconnection.getConnection();
        PreparedStatement ps;
        ps = connection.prepareStatement("insert into users(name,email) values( ? , ?) ");
        ps.setString(1,user.getName());
        ps.setString(2,user.getEmail());
        return ps.executeUpdate();

    }
}
