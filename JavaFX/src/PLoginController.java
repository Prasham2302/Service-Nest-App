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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Spinner;
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
public class PLoginController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    public static  int rand=0;
    public String category = "0";
    
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
            rand = r.nextInt(high-low) + low;
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
    ObservableList<String> prof2 = FXCollections.observableArrayList("Housekeeping","Electrician","Plumbers");
    ObservableList<String> prof1 = FXCollections.observableArrayList("Doctors","Nurses");
    ObservableList<String> subjects = FXCollections.observableArrayList("Maths","Science","English","Social Science","Hindi");
    ObservableList<String> bool = FXCollections.observableArrayList("YES","NO");

    @FXML
    public void validate_otp(ActionEvent event) throws AddressException
    {
         InternetAddress[] validation = new InternetAddress[1];
        
        
            
                String query = "select * from Service_Provider where ID="+login_id.getText();
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
    
    @FXML
    public TextField login_msg;
    @FXML
    public ChoiceBox choose_work;
    @FXML
    public TextField work_fees;
    @FXML
    public TextField work_exp;
    @FXML
    public Button work_next;
    
    @FXML
    public ChoiceBox choose_med;
    @FXML
    public TextField med_add;
    @FXML
    public TextField med_exp;
    @FXML
    public TextField med_fees;
    @FXML
    public TextField med_spec;
    @FXML
    public Button med_next;
            
    
//    @FXML
//    public ChoiceBox choose_tut;
    @FXML
    public TextField tut_fees;
    @FXML
    public TextField tut_exp;
    @FXML
    public TextField tut_qual;
    @FXML
    public Button tut_next;
    
    @FXML
    public ChoiceBox lab_loc;
    @FXML
    public TextField lab_name;
    @FXML
    public TextField morning_time;
    @FXML
    public TextField evening_time;
    @FXML
    public Button lab_next;
    
    
    @FXML
    public ChoiceBox store_stat;
    @FXML
    public ChoiceBox store_don;
    @FXML
    public ChoiceBox store_sec;
    @FXML
    public ChoiceBox store_loc;
    @FXML
    public TextField store_time;
    @FXML
    public Button store_next;
    
    
    @FXML
    public CheckBox maths; 
    @FXML
    public CheckBox science; 
    @FXML
    public CheckBox hindi; 
    @FXML
    public CheckBox english; 
    @FXML
    public CheckBox sst; 
    @FXML
    public CheckBox cs; 
    @FXML
    public CheckBox economics; 
    
    
    @FXML
    public void psign_up(ActionEvent event) throws IOException, SQLException, Exception 
    {
        System.out.println("Signup");
        Dbms.resultSet = Dbms.statement.executeQuery("select max(ID) from Service_Provider");
        int id = 1;
        if(Dbms.resultSet.next())
        {
            id = Dbms.resultSet.getInt(1);
            id+=1;
        }
        String query = "";
       //try {
            if(event.getSource()==work_next)
            {
                
                String fees = work_fees.getText();
                String exp = work_exp.getText();
                String prof = prof2.get(choose_work.getSelectionModel().getSelectedIndex());
                if(prof.equals("Electrician")){
                    category = "4";
                }
                else if(prof.equals("Plumbers")){
                    category = "3";
                }
                else{
                    category = "5";
                }
                query = "INSERT INTO " + prof + " VALUES('"+ id + "','" + fees +"','0','"+ exp + "') ";
                Dbms.statement.executeUpdate(query);
                System.out.println(query);
            }
            
            else if(event.getSource()==med_next)
            {
                String prof = prof1.get(choose_med.getSelectionModel().getSelectedIndex());
                if(prof.equals("Doctors")){
                    category = "1";
                }
                else{
                    category = "2";
                }
                String fees = med_fees.getText();
                String add = med_add.getText();
                String spec = med_spec.getText();
                String exp = med_exp.getText();
                query = "INSERT INTO " + prof + " VALUES('"+ id + "','" + add +"','"+exp +"','"+ fees + "','"+spec+"') ";
                Dbms.statement.executeUpdate(query);
                System.out.println(query);
                
            }
            
            else if(event.getSource() == tut_next)
            {   category = "6";
                //Checkbox a = new CheckBox
                ArrayList<String> sub = new ArrayList<String>();
                if(maths.isSelected())
                {
                    sub.add("Maths");
                }
                if(science.isSelected())
                {
                    sub.add("Science");
                }
                if(hindi.isSelected())
                {
                    sub.add("Hindi");
                }
                if(english.isSelected())
                {
                    sub.add("English");
                }
                if(sst.isSelected())
                {
                    sub.add("Social Science");
                }
                if(economics.isSelected())
                {
                    sub.add("Economics");
                }
                if(cs.isSelected())
                {
                    sub.add("Computer Science");
                }
                if(sub.size()==0)
                {
                    throw new Exception();
                }
                String fees = tut_fees.getText();
                int f = Integer.valueOf(fees)/4;
                String qual = tut_qual.getText();
                String exp = tut_exp.getText();
                query = "INSERT INTO Tutors"   + " VALUES('"+ id + "','" + fees +"','"+f +"','"+ exp + "','"+qual+"') ";
                Dbms.statement.executeUpdate(query);
                System.out.println(query);
                String q = "ALTER TABLE Teaches DROP FOREIGN KEY FK_ID;";
                Dbms.statement.executeUpdate(q);
                for(int i=0;i<sub.size();i++)
                {
                    String subo = sub.get(i);
                    String quera = "INSERT INTO Teaches VALUES('"+id+"','"+subo+"')";
                    System.out.println(quera);
                    Dbms.statement.executeUpdate(quera);
                }
                
                
                
            }
            
            else if (event.getSource() == lab_next)
            {   category = "8";
                String labname = lab_name.getText();
                String m_time = morning_time.getText();
                String e_time = evening_time.getText();
                query = "INSERT INTO Laboratories"   + " VALUES('"+ id + "','" + labname +"','"+m_time +"','"+ e_time +"') ";
                Dbms.statement.executeUpdate(query);
                System.out.println(query);
                
            }
            
            else if(event.getSource() == store_next)
            {   
                category = "7";
                String time = store_time.getText();
                String sec = bool.get(store_sec.getSelectionModel().getSelectedIndex());
                String don = bool.get(store_don.getSelectionModel().getSelectedIndex());
                String stat = bool.get(store_stat.getSelectionModel().getSelectedIndex());
                String loca = loc.get(store_loc.getSelectionModel().getSelectedIndex());
                query = "INSERT INTO BookStores"   + " VALUES('"+ id + "','" + loca +"','"+sec +"','"+ stat +"','"+don+"','"+time+"')";
                Dbms.statement.executeUpdate(query);
                System.out.println(query);
                category = "8";
                
            }
            
            
            
            Stage Sp = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("ServiceProvider.fxml"));
            Scene scene =new Scene(root);
            Sp.setTitle(String.valueOf(id)+" "+category);
            
            Sp.setScene(scene);
            Sp.show();
        
      // }  
        //catch (Exception e) {
            //cust_action.setText("Please provide correct details in required format");
          //  System.out.println("P Signup");
        //}
        
        
    }
    
    
    @FXML
    public Button login_button;
    @FXML
    public TextField old_otp;
    @FXML
    public Button old_otpg;
    @FXML
    public TextField login_id;
   
    
    
    @FXML
    public void login(ActionEvent event) throws AddressException, IOException
    {
         
        String get_otp = old_otp.getText();
        
        try
        {    
        
            if(Integer.valueOf(get_otp) == rand && rand !=0)
            {
                rand = 0;
                Dbms.professional_looged = Dbms.pstatement.executeQuery("select * from Service_Provider where ID="+login_id.getText());
                Dbms.professional_looged.next();
            }
            else{
                throw new Exception();
            }
            
//            String query = "Select * from Service_Provider where ID = '" + login_id.getText()+"'";
//        System.out.println(query);
//        Dbms.customer_logged = Dbms.cstatement.executeQuery(query);
//        Dbms.customer_logged.next();
//        System.out.println(Dbms.customer_logged.getString("ID"));
            
        Parent root = FXMLLoader.load(getClass().getResource("Professional.fxml"));
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
        choose_work.setItems(prof2);
        choose_med.setItems(prof1);
        
        lab_loc.setItems(loc);
        store_don.setItems(bool);
        store_sec.setItems(bool);
        store_stat.setItems(bool);
        store_loc.setItems(loc);
        
        
    }    
    
}
