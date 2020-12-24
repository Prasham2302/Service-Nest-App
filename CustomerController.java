/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Observable;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class CustomerController implements Initializable {

    
    
    ObservableList<String> exp = FXCollections.observableArrayList("0","1","5","10");
    ObservableList<String> loc = FXCollections.observableArrayList("East","South","West","North","Central");
    ObservableList<String> rat = FXCollections.observableArrayList("1","2","3","4","5");
    ObservableList<String> prof = FXCollections.observableArrayList("DOCTORS","NURSES");
    ObservableList<String> prof1 = FXCollections.observableArrayList("TUTORS");
    ObservableList<String> prof2 = FXCollections.observableArrayList("HOUSEKEEPING","ELECTRICIAN","PLUMBERS");
    ObservableList<String> gen = FXCollections.observableArrayList("M","F","O");
    @FXML
    public ChoiceBox exp_pick;
    @FXML
    public ChoiceBox ratings_pick;
    @FXML
    public ChoiceBox location_pick;
    @FXML
    public Slider price_slider;
    @FXML 
    public ChoiceBox Profession_pick;
    @FXML 
    public Button Search_M;
    @FXML
    public TableView medical_table;
    
    @FXML
    public ChoiceBox exp_pick1;
    @FXML
    public ChoiceBox ratings_pick1;
    @FXML
    public ChoiceBox location_pick1;
    @FXML
    public Slider price_slider1;
    @FXML 
    public ChoiceBox Profession_pick1;
    @FXML 
    public Button Search_M1;
    @FXML
    public TableView edu_table;
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
    public ChoiceBox exp_pick2;
    @FXML
    public ChoiceBox ratings_pick2;
    @FXML
    public ChoiceBox location_pick2;
    @FXML
    public Slider price_slider2;
    @FXML 
    public ChoiceBox Profession_pick2;
    @FXML 
    public Button Search_M2;
    @FXML
    public TableView dtd_table;
    @FXML
    public Button book;
    @FXML
    public Button book1;
    @FXML
    public Button book2;
    
    @FXML
    public TableView YourOrders;
    
    
    
    @FXML
    public TableView Books;
    @FXML
    public Button book_open;
    @FXML
    public Button Search_M3;
    @FXML
    public TextField book_name;
    @FXML
    public ChoiceBox location_pick3;
    
    @FXML
    public void book_now(ActionEvent event) throws SQLException 
    {
        if(event.getSource()==book || event.getSource()==book1 || event.getSource()==book2)
        {
            TableView temp = null;
            String pro = "";
            if(event.getSource()==book)
            {
                temp = medical_table;
                pro = prof.get(Profession_pick.getSelectionModel().getSelectedIndex());
            }
            else if(event.getSource()==book1)
            {
                temp = edu_table;
                pro = prof1.get(Profession_pick1.getSelectionModel().getSelectedIndex());
            }
            else if(event.getSource()==book2)
            {
                temp = dtd_table;
                pro = prof2.get(Profession_pick2.getSelectionModel().getSelectedIndex());
            }
            
            
            ObservableList<String> pk = (ObservableList<String>)temp.getSelectionModel().getSelectedItem();
            String pk1 = pk.get(0);
            System.out.println(pk1);
           // `Services_ID` int PRIMARY KEY,
            
            Dbms.professional_looged = Dbms.pstatement.executeQuery("Select * from Works_In W, Service_Provider S,"+pro+" P where S.ID = P.ID AND P.ID = W.ID AND S.ID = "+pk1);
            Dbms.professional_looged.next();
            String Customer_Id = Dbms.customer_logged.getString("ID");
            String Customer_Phone =  Dbms.customer_logged.getString("Phone_Number");
            String Service_Provider_Phone = Dbms.professional_looged.getString("Phone_Number");
            String Service_Provider_Id = Dbms.professional_looged.getString("ID");
            String Amount_Paid = Dbms.professional_looged.getString("Fees_Per_Visit");
            
            
            String Review_By_Customer = "0";
            String Review_By_ServiceProvider = "0";
            String Progress = "Pending";
            String Service_Category = Dbms.professional_looged.getString("Service_Category");
            String Location = Dbms.customer_logged.getString("Location");
            String Home_Address = Dbms.customer_logged.getString("Home_Address");
            Timestamp Date_Time_Of_Service = new Timestamp(System.currentTimeMillis());
            
            System.out.println(Date_Time_Of_Service);
            String cols = "(Customer_Id,Customer_Phone,Service_Provider_Phone,Service_Provider_Id,Amount_Paid,Review_By_Customer,Progress,Service_Category,Location,Home_Address,Date_Time_Of_Service)";
            String query = "insert into Services values(?,?,?,?,?,?,?,?,?,?,?,?)";
            
            Dbms.resultSet = Dbms.statement.executeQuery("Select max(Services_ID) from Services ");
            int total_services = 1;
            if(Dbms.resultSet.next())
            {   
                total_services = Dbms.resultSet.getInt(1)+1;
            }
            
            PreparedStatement p = Dbms.connection.prepareStatement(query);
            p.setInt(1, total_services);
            p.setString(2, Customer_Id);
            p.setString(3, Customer_Phone);
            p.setString(4, Service_Provider_Phone);
            p.setString(5, Service_Provider_Id);
            p.setString(6, Amount_Paid);
            p.setString(7, Review_By_Customer);
            //p.setString(8, Review_By_ServiceProvider);
            p.setString(8, Progress);
            p.setString(9, Service_Category);
            p.setString(10, Location);
            p.setString(11, Home_Address);
            p.setTimestamp(12, Date_Time_Of_Service);
            System.out.println(p.executeUpdate());
              
        }
        
        else if(event.getSource()==book_open)
        {
            
        }
        
        
    }
    
    
    @FXML
    public void show_table(TableView x) throws SQLException
    {
        for(int i=0 ; i<Dbms.resultSet.getMetaData().getColumnCount(); i++){
                //We are using non property style for making dynamic table
                final int j = i;                
                TableColumn col = new TableColumn(Dbms.resultSet.getMetaData().getColumnName(i+1));
                col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList,String>,ObservableValue<String>>(){                    
                    public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {                                                                                              
                        return new SimpleStringProperty(param.getValue().get(j).toString());                        
                    }                    
                });

                x.getColumns().addAll(col);
        }
        ObservableList<ObservableList> data = FXCollections.observableArrayList();
        
        while (Dbms.resultSet.next()) {
	// retrieve and print the values for the current row
	
        
        ObservableList<String> row = FXCollections.observableArrayList();
        
        for(int i=0;i<Dbms.resultSet.getMetaData().getColumnCount();i++)
        {
            String t = Dbms.resultSet.getString(Dbms.resultSet.getMetaData().getColumnName(i+1));
            row.add(t);
        }
        System.out.println("Row [1] added "+row );
        data.add(row);
        
 }
      
        x.setItems(data); 
    }
    
    
    
    
    @FXML 
    public void save_details(ActionEvent event) throws SQLException
    {
        String Name = cust_name.getText();
        String Email = cust_email.getText();
        String age = cust_age.getText();
        
        String gender = gen.get(cust_gender.getSelectionModel().getSelectedIndex());
        
        String mob = cust_mob.getText();
        String add = cust_add.getText();
        String location = loc.get(cust_loc.getSelectionModel().getSelectedIndex());
        
        String ID = Dbms.customer_logged.getString("ID");
        
        String q = "UPDATE Customers SET Full_Name = '"+Name+"', Email = '"+Email+"' ,Age = '"+age+"', Gender ='"+gender+"', Phone_Number = '"+mob+"',Home_Address ='"+add+"',Location = '"+location+"' where ID = '"+ID+"'";
        System.out.println(q);
        
        Dbms.statement.executeUpdate(q);
        Dbms.customer_logged = Dbms.statement.executeQuery("select * from Customers where ID="+ID);
        Dbms.customer_logged.next();
    }
    
    
    
    
    @FXML
    public void filter(ActionEvent event) throws SQLException
    {
        System.out.println("filter");
        String Profession="";
        String Location = "";
        String Price = "0";
        String Experience = "0";
        String Ratings = "0";
        TableView x=null;
        
        if(event.getSource()==Search_M1)
        {
          Profession = prof1.get(Profession_pick1.getSelectionModel().getSelectedIndex());
          if(location_pick1.getSelectionModel().getSelectedIndex() >= 0)
          {
              Location = loc.get(location_pick1.getSelectionModel().getSelectedIndex());
          }
          Price = String.valueOf(price_slider1.getValue());
          if(exp_pick1.getSelectionModel().getSelectedIndex() >= 0)
          {
            Experience = exp.get(exp_pick1.getSelectionModel().getSelectedIndex());
          }
          if(ratings_pick1.getSelectionModel().getSelectedIndex()>=0)
          {
            Ratings = rat.get(ratings_pick1.getSelectionModel().getSelectedIndex());
          }
            x = edu_table;
            String query = "SELECT * FROM "+Profession+" P,Service_Provider S,Works_In W WHERE P.ID = S.ID AND W.ID =S.ID AND S.Availability=1 AND W.Location like '%"+Location+"%' AND P.Fees_Per_Visit>'"+Price+"' AND P.Experience >'"+Experience+"' AND S.Ratings> '"+Ratings+"'";
        System.out.println(query);
        Dbms.resultSet = Dbms.statement.executeQuery(query);
        
        show_table(x);
        }
        else if(event.getSource()==Search_M)
        {
            Profession = prof.get(Profession_pick.getSelectionModel().getSelectedIndex());
          if(location_pick.getSelectionModel().getSelectedIndex() >= 0)
          {
              Location = loc.get(location_pick.getSelectionModel().getSelectedIndex());
          }
          Price = String.valueOf(price_slider.getValue());
          if(exp_pick.getSelectionModel().getSelectedIndex() >= 0)
          {
            Experience = exp.get(exp_pick.getSelectionModel().getSelectedIndex());
          }
          if(ratings_pick.getSelectionModel().getSelectedIndex()>=0)
          {
            Ratings = rat.get(ratings_pick.getSelectionModel().getSelectedIndex());
          }
            x = medical_table;
            String query = "SELECT * FROM "+Profession+" P,Service_Provider S,Works_In W WHERE P.ID = S.ID AND W.ID =S.ID AND S.Availability=1 AND W.Location like '%"+Location+"%' AND P.Fees_Per_Visit>'"+Price+"' AND P.Experience >'"+Experience+"' AND S.Ratings> '"+Ratings+"'";
        System.out.println(query);
        Dbms.resultSet = Dbms.statement.executeQuery(query);
        
        show_table(x);
        }
        else if(event.getSource()==Search_M2)
        {
            Profession = prof2.get(Profession_pick2.getSelectionModel().getSelectedIndex());
            
          if(location_pick2.getSelectionModel().getSelectedIndex() >= 0)
          {
              Location = loc.get(location_pick2.getSelectionModel().getSelectedIndex());
          }
          Price = String.valueOf(price_slider2.getValue());
          if(exp_pick2.getSelectionModel().getSelectedIndex() >= 0)
          {
            Experience = exp.get(exp_pick2.getSelectionModel().getSelectedIndex());
          }
          if(ratings_pick2.getSelectionModel().getSelectedIndex()>=0)
          {
            Ratings = rat.get(ratings_pick2.getSelectionModel().getSelectedIndex());
          }
            x = dtd_table;
            String query = "SELECT * FROM "+Profession+" P,Service_Provider S,Works_In W WHERE P.ID = S.ID AND W.ID =S.ID AND S.Availability=1 AND W.Location like '%"+Location+"%' AND P.Fees_Per_Visit>'"+Price+"' AND P.Experience >'"+Experience+"' AND S.Ratings> '"+Ratings+"'";
        System.out.println(query);
        Dbms.resultSet = Dbms.statement.executeQuery(query);
        
        show_table(x);
        }
        
        else if(event.getSource() == Search_M3)
        {
            String loca = "";
            if(location_pick3.getSelectionModel().getSelectedIndex()>=0)
            {
                loca = loc.get(location_pick3.getSelectionModel().getSelectedIndex());
            }
            String name = "";
            name = book_name.getText();
            //System.out.println(name +" "+lo);
            
            String query = "select * from BookStores B,Books C,Keeps K where B.ID = K.store_ID AND C.Book_Name like '%"+name +"%' AND B.Location like '%"+loca+"%'";
            System.out.println(query);
            Dbms.resultSet = Dbms.statement.executeQuery(query);
            x = Books;
            show_table(x);
        }
        


        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       exp_pick.setItems(exp);
       location_pick.setItems(loc);
       ratings_pick.setItems(rat);
       price_slider.setMin(0);
       price_slider.setMax(1000);
       price_slider.adjustValue(500);
       Profession_pick.setItems(prof);
       
       
       exp_pick1.setItems(exp);
       location_pick1.setItems(loc);
       ratings_pick1.setItems(rat);
       price_slider1.setMin(0);
       price_slider1.setMax(1000);
       price_slider1.adjustValue(500);
       Profession_pick1.setItems(prof1);
       
       exp_pick2.setItems(exp);
       location_pick2.setItems(loc);
       ratings_pick2.setItems(rat);
       price_slider2.setMin(0);
       price_slider2.setMax(1000);
       price_slider2.adjustValue(500);
       Profession_pick2.setItems(prof2);
       
       location_pick3.setItems(loc);
       
       
        try {
            cust_name.setText(Dbms.customer_logged.getString("Full_Name"));
            cust_age.setText(Dbms.customer_logged.getString("Age"));
            cust_email.setText(Dbms.customer_logged.getString("email"));
            cust_mob.setText(Dbms.customer_logged.getString("Phone_Number"));
            cust_add.setText(Dbms.customer_logged.getString("Home_Address"));
            cust_gender.setItems(gen);
            cust_gender.getSelectionModel().select(gen.indexOf(Dbms.customer_logged.getString("Gender")));
            cust_loc.setItems(loc);
            cust_loc.getSelectionModel().select(gen.indexOf(Dbms.customer_logged.getString("Location")));
            
            
        } catch (SQLException ex) {
            Logger.getLogger(CustomerController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        try {
            Dbms.resultSet = Dbms.statement.executeQuery("Select * from Services where Customer_Id ="+Dbms.customer_logged.getString("ID"));
            show_table(YourOrders);
        } catch (SQLException ex) {
            Logger.getLogger(CustomerController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }
}