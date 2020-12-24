/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.Random;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Properties;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.activation.*;
import javax.mail.*;
import javax.mail.Session;
import  javax.mail.Transport;
import javax.mail.internet.*;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class LoginController implements Initializable {

    /**
     * Initializes the controller class.
     * @param event
     */
    
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
    public Button cust_sumbit;
    @FXML
    public ChoiceBox cust_loc;
    @FXML
    public TextField cust_add;
    @FXML
    public TextField cust_mob;
    @FXML
    public TextField cust_age;
    @FXML
    public ChoiceBox cust_gender;
    @FXML
    public TextField cust_email;
    @FXML
    public TextField cust_name;
    @FXML
    public TextField cust_action;
    @FXML 
    public TextField old_otp;
    @FXML
    public Button old_otpg;
    @FXML 
    public TextField new_otp;
    @FXML
    public Button new_otpg;
    @FXML 
    public Label login_msg;
    @FXML
    public Button login_button;
    @FXML
    public TextField login_id;
   
    
    @FXML
    public void validate_otp(ActionEvent event) throws AddressException, SQLException
    {
        InternetAddress[] validation = new InternetAddress[1];
        if(event.getSource()== new_otpg)
        {
            String Email = cust_email.getText();
            
            validation[0] = new InternetAddress(Email);
            send_mail(validation);
        }
        else if(event.getSource() == old_otpg)
            {
                String query = "select * from Customers where ID="+login_id.getText();
                try{
                    Dbms.resultSet = Dbms.statement.executeQuery(query);
                    Dbms.resultSet.next();
                    validation[0] = new InternetAddress(Dbms.resultSet.getString("Email"));
                    send_mail(validation);
                }
                catch(Exception e)
                {
                    login_msg.setText("Wrong Credentials");
                }
            }
        
    }
    @FXML
    public void sign_up(ActionEvent event) 
    {
        System.out.println("Signup");
        try {
            String Name = cust_name.getText();
            String Email = cust_email.getText();
            String age = cust_age.getText();
            String gender = gen.get(cust_gender.getSelectionModel().getSelectedIndex());
            String mob = cust_mob.getText();
            String add = cust_add.getText();
            String location = loc.get(cust_loc.getSelectionModel().getSelectedIndex());
            String get_otp = new_otp.getText();
            System.out.println(rand+" "+get_otp);
            if(Integer.valueOf(get_otp) == rand && rand !=0)
            {
                Dbms.resultSet = Dbms.statement.executeQuery("select max(ID) from Customers");
                int next_id=1;
                if(Dbms.resultSet.next())
                {
                    next_id = Dbms.resultSet.getInt(1);
                    next_id+=1;
                }
                
                String query = "INSERT INTO Customers " +"VALUES('"+next_id+"' ,'"+Name+"','"+age+"','"+gender+"','"+mob+"','"+add+"','"+location+ "','"+ Email+"')";
                System.out.println(query);
                Dbms.statement.executeUpdate(query);
                cust_action.setText("Your Registered login id: "+next_id+" Please login to continue");
                rand = 0;
            }
            else{
                throw new Exception();
            }
           
        
        } 
        catch (Exception e) {
            cust_action.setText("Please provide correct details in required format");
        }
        
        
    }
    
     @FXML
    public void login(ActionEvent event) throws AddressException, IOException, SQLException
    {
         
         
    
        
        String get_otp = old_otp.getText();
        
        try
        {    
        
            if(Integer.valueOf(get_otp) == rand && rand !=0)
            {
                rand = 0;
                Dbms.customer_logged = Dbms.statement.executeQuery("select * from Customers where ID="+login_id.getText());
                Dbms.customer_logged.next();
            }
            else{
                throw new Exception();
            }
            
            String query = "Select * from Customers where ID = '" + login_id.getText()+"'";
        System.out.println(query);
        Dbms.customer_logged = Dbms.cstatement.executeQuery(query);
        Dbms.customer_logged.next();
        System.out.println(Dbms.customer_logged.getString("ID"));
            
        Parent root = FXMLLoader.load(getClass().getResource("Customer.fxml"));
        Scene scene =new Scene(root);
        Stage Customer_stage = new Stage();
        Customer_stage.setTitle("load");
        Customer_stage.setScene(scene);
        Customer_stage.show();
        }
        catch(Exception e)
        {
            System.out.println("Please Login again");
        }
    }
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cust_loc.setItems(loc);
        cust_gender.setItems(gen);
    }    
    
}
