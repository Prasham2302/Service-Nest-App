import java.io.IOException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Dbms 
{   
    public static final String DB_URL ="jdbc:mysql://localhost:3306/SERVICE_NEST";
    public static final String DB_DRV ="com.mysql.jdbc.Driver";
    public static final String DB_USER = "root";
    public static final String DB_PASSWD = "Tric@2000";
    public static Connection connection = null;
    public static Statement statement = null;
    public static ResultSet resultSet = null;
    public static ResultSet customer_logged = null;
    public static ResultSet professional_looged = null;
    public static Statement cstatement = null;
    public static Statement pstatement = null;
    
    public static void main(String[] args) throws ClassNotFoundException 
    { 
        
        try
        {
            Class.forName(DB_DRV);
            connection = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWD);
            System.out.println("1err");
            statement = connection.createStatement();
            cstatement = connection.createStatement();
            pstatement = connection.createStatement();
            //connection.setAutoCommit(false);
            
            
         
        }
        catch(SQLException ex)
        {
            System.out.println("error");
        }
        
      
        Application.launch(MainPage.class,args);
    }
    
}

