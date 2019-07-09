package app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
 
import app.DbContract;

/**  
 * @author Espinoza Guillermo
 */
public class TestConnection {
        
    public static void main(String[] args) {
        try {
            Class.forName("org.postgresql.Driver");
            Connection c = DriverManager.getConnection(
                    DbContract.HOST+DbContract.DB_NAME,
                    DbContract.USERNAME,
                    DbContract.PASSWORD);
            
            System.out.println("DB connected");
             
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
 
    }
 
}