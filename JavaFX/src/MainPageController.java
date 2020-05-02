/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class MainPageController implements Initializable {

    @FXML
    public Button customer;
    @FXML
    public Button professional;
    @FXML
    public Button Exit;
    
    @FXML
    public void customer_click(ActionEvent event) throws IOException
    {
        System.out.println("customer clicked");
        Stage temp = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Scene scene =new Scene(root);
        temp.setTitle("load");
        temp.setScene(scene);
        temp.show();
    }
    @FXML
    public void professional_click(ActionEvent event) throws IOException
    {
        System.out.println("professional clicked");
        Stage temp = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("PLogin.fxml"));
        Scene scene =new Scene(root);
        temp.setTitle("load");
        temp.setScene(scene);
        temp.show();
    }
    @FXML
    public void Exit(ActionEvent event)
    {
        
        System.exit(0);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
    
    
}
