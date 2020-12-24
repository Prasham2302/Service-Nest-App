/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class ServiceProviderController implements Initializable {

    
     public int rand = 0;
    
    public void send_mail(InternetAddress[] recipients)
    {
        String from = "servicenesthelpline@gmail.com";
        final String username = "servicenesthelpline";
        final String password = "Service@2000";
        String host = "smtp.gmail.com";
        Properties pro = new Properties();
        pro.put("mail.smtp.host",host);
        pro.put("mail.smtp.socketFactory.port","465");
         pro.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        pro.put("mail.smtp.auth","true");
         pro.put("mail.smtp.port","465");
        Session session = Session.getInstance(pro,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication()
                    {
                        return new PasswordAuthentication(username,password);
                    }
                }

                );
        try 
        {
            Random r = new Random();
            int low = 100000;
            int high = 999999;
            this.rand = r.nextInt(high-low) + low;
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipients(Message.RecipientType.TO,recipients);
            message.setSubject("URGENT OTP verifiction");
            message.setText("your OTP for verification of your account at SERVICE NEST :"+rand +"\nPlease reply back by sending 1 if you got it\n if it doesn't belong to you kindly ignore");
            Transport.send(message);


        } 
        catch (Exception e) {

            System.out.println(e.toString());
        }
    }
    
    
    
    
    
    ObservableList<String> loc = FXCollections.observableArrayList("East","South","West","North","Central");
    ObservableList<String> gen = FXCollections.observableArrayList("M","F","O");
    @FXML
    public Button new_otpg;
    @FXML
    public Button prof_sumbit;
    @FXML
    public TableView prof_loc;
    @FXML
    public TextField prof_add;
    @FXML
    public TextField prof_mob;
    @FXML
    public TextField prof_age;
    @FXML
    public TextField prof_otp;
    @FXML
    public ChoiceBox prof_gender;
    @FXML
    public TextField prof_email;
    @FXML
    public TextField prof_name;
    @FXML
    public TextField prof_action;
    
    @FXML
    public CheckBox north;
    @FXML
    public CheckBox west;
    @FXML
    public CheckBox south;
    @FXML
    public CheckBox central;
    @FXML
    public CheckBox east;
    
    
    
    @FXML
    public void validate_otp(ActionEvent event) throws AddressException, SQLException
    {
        InternetAddress[] validation = new InternetAddress[1];
        
            String Email = prof_email.getText();
            
            validation[0] = new InternetAddress(Email);
            send_mail(validation);
        
        
        
    }
    @FXML
    public void sign_up(ActionEvent event) throws SQLException, Exception 
    {
        String next_id;
        Stage a = (Stage)((Button)event.getSource()).getScene().getWindow();
        String temp[] = a.getTitle().split(" ");
        next_id = temp[0];
        String category = temp[1];
        System.out.println("Signup");
        try {
            String Name = prof_name.getText();
            String Email = prof_email.getText();
            String age = prof_age.getText();
            String gender = gen.get(prof_gender.getSelectionModel().getSelectedIndex());
            String mob = prof_mob.getText();
            String add = prof_add.getText();
            ArrayList<String> locations = new ArrayList<String>();
            int count = 0;
            if(east.isSelected())
            {
                locations.add("East");
                count++;
            }
            if(west.isSelected())
            {
                locations.add("West");
                count++;
            }
            if(north.isSelected())
            {
                locations.add("North");
                count++;
            }
            if(south.isSelected())
            {
                locations.add("South");
                count++;
            }
            if(central.isSelected())
            {
                locations.add("Central");
                count++;
            }
            
            if(count==0)
            {
                throw new Exception();
            }
            String get_otp = prof_otp.getText();
            System.out.println(rand+" "+get_otp);
            if(Integer.valueOf(get_otp) == rand && rand !=0)
            {
                System.out.println("welcome");
                
                System.err.println(next_id);
                String query = "INSERT INTO Service_Provider " +"VALUES('"+next_id+"' ,'"+Name+"','"+age+"','"+gender+"','"+add+"','"+mob+"','0','1','1','"+Email+"','1')";
                System.out.println(query);
                Dbms.statement.executeUpdate(query);
                prof_action.setText("You are now registered. Please login to continue");
                rand = 0;
                for(int i=0;i<locations.size();i++)
                {
                    String loca = locations.get(i);
                    String quera = "INSERT INTO Works_In VALUES('"+next_id+"','"+loca+"')";
                    System.out.println(quera);
                    Dbms.statement.executeUpdate(quera);
                }
                
            }
            else{
                throw new Exception();
            }
           String q = "ALTER TABLE Teaches ADD CONSTRAINT FK_ID FOREIGN KEY (ID) REFERENCES Service_Provider(ID)";
           Dbms.statement.executeUpdate(q);
        
        } 
        catch (Exception e) {
            prof_action.setText("Please provide correct details in required format");
            System.out.println("DELETE FROM Tutors WHERE ID = '"+next_id+"'");
            Dbms.statement.executeUpdate("DELETE FROM Tutors WHERE ID = '"+next_id+"'");
            Dbms.statement.executeUpdate("DELETE FROM Doctors WHERE ID = '"+next_id+"'");
            Dbms.statement.executeUpdate("DELETE FROM Nurses WHERE ID = '"+next_id+"'");
            Dbms.statement.executeUpdate("DELETE FROM Electrician WHERE ID = '"+next_id+"'");
            Dbms.statement.executeUpdate("DELETE FROM Plumbers WHERE ID ='"+next_id+"'");
            Dbms.statement.executeUpdate("DELETE FROM Housekeeping WHERE ID ='"+next_id+"'");
            Dbms.statement.executeUpdate("DELETE FROM BookStores WHERE ID ='"+next_id+"'");
            Dbms.statement.executeUpdate("DELETE FROM Works_In WHERE ID ='"+next_id+"'");
            Dbms.statement.executeUpdate("DELETE FROM Teaches WHERE ID ='"+next_id+"'");
            Dbms.statement.executeUpdate("DELETE FROM Laboratories WHERE ID ='"+next_id+"'");
            
        }
        
        
    }
    
    
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        prof_gender.setItems(gen);
        //prof_gender.getSelectionModel().se
        //prof_loc.setItems(loc);
      // prof_loc.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        
        
    }    
    
}
