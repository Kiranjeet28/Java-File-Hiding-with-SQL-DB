package db;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.*;
import java.sql.Connection;
import java.sql.SQLException;
public class Myconnection {
   public static Connection connection = null;
  static  String url ="jdbc:mysql://localhost:3306/ytproject";
    static String  user = "root";
    static String password = "iamjeet28";
    public static Connection getConnection(){

   try{
       Class.forName("com.mysql.cj.jdbc.Driver");
 connection = DriverManager.getConnection(url,user,password);
   }
        catch (Exception ex ){
            System.out.println("Connection nhi hua ");

        }
        System.out.println("Connection ho gya");
        return connection;
   }

   public static void closeConntection(){
       if(connection != null){
           try{
               connection.close();
           } catch (Exception e ){
               System.out.println("galti hai gya");

           }
       }

   }
}
