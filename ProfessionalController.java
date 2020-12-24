/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.net.URL;
import java.sql.SQLException;
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
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author hp
 */



public class ProfessionalController implements Initializable {

    /**
     * Initializes the controller class.
     */
    public void show_table()
    {
        try{
        Dbms.resultSet = Dbms.statement.executeQuery("Select * from Services where Service_Provider_Id ="+Dbms.professional_looged.getString("ID"));
        for(int i=0 ; i<Dbms.resultSet.getMetaData().getColumnCount(); i++){
                //We are using non property style for making dynamic table
                final int j = i;                
                TableColumn col = new TableColumn(Dbms.resultSet.getMetaData().getColumnName(i+1));
                col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList,String>,ObservableValue<String>>(){                    
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {                                                                                              
                        return new SimpleStringProperty(param.getValue().get(j).toString());                        
                    }                    
                });

               Tasks.getColumns().addAll(col);
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
        Tasks.setItems(data);
    }
        catch(Exception e)
                {
                System.out.println("Your orders not printed sorry");
                }
    }
    
    
    @FXML
    public TableView Tasks;
    @FXML 
    public Button mark_done;
    
    ObservableList<String> gen = FXCollections.observableArrayList("M","F","O");
    ObservableList<String> loc = FXCollections.observableArrayList("East","South","West","North","Central");
    String[] profession = new String[] {"Doctors","Doctors","Nurses","Plumber","Electrician","Housekeeping","Tutors","Bookstores","Laboratories"};
    
    public Button pro_submit;
    @FXML
    public ChoiceBox pro_loc;
    @FXML
    public TextField pro_add;
    @FXML
    public TextField pro_mob;
    @FXML
    public TextField pro_fees;
    @FXML
    public TextField pro_age;
    @FXML
    public ChoiceBox pro_gender;
    @FXML
    public TextField pro_email;
    @FXML
    public TextField pro_name;
    @FXML
    public TextField pro_action;
    
    
    
    @FXML 
    public void marking_done(ActionEvent event)
    {
        try {
            ObservableList<String> pk = (ObservableList<String>)Tasks.getSelectionModel().getSelectedItem();
            String pk1 = pk.get(0);
            String query = "UPDATE SERVICES SET Progress = 'Completed' WHERE SERVICES_ID='"+pk1+"'";
            Dbms.statement.executeUpdate(query);
            show_table();
        } catch (Exception e) {
        }
 
            
    }
    
     public void save_details(ActionEvent event) throws SQLException
    {
        String Name = pro_name.getText();
        String Email = pro_email.getText();
        String age = pro_age.getText();
        
        String gender = gen.get(pro_gender.getSelectionModel().getSelectedIndex());
        
        String mob = pro_mob.getText();
        String add = pro_add.getText();
        //String location = loc.get(pro_loc.getSelectionModel().getSelectedIndex());
        String fees = pro_fees.getText();
        String ID = Dbms.professional_looged.getString("ID");
        String category = Dbms.professional_looged.getString("Service_Category");
        
        String q = "UPDATE Service_Provider SET Full_Name = '"+Name+"', Email = '"+Email+"' ,Age = '"+age+"', Gender ='"+gender+"', Phone_Number = '"+mob+"',Address ='"+add+"' where ID = '"+ID+"'";
        System.out.println(q);
        Dbms.statement.executeUpdate(q);
        q = "Update "+profession[Integer.parseInt(category)]+" SET Fees_Per_Visit = '"+fees+"'";
        System.out.println(q);
        Dbms.statement.executeUpdate(q);
        
        Dbms.professional_looged = Dbms.pstatement.executeQuery("select * from Customers where ID="+ID);
        Dbms.professional_looged.next();
        
    }
    
     @FXML
     public Tab AddBooks;
     @FXML
     public TextField book_name;
     @FXML
     public Button book_add;
     @FXML
     public void adding_book(ActionEvent event) throws SQLException
     {
         String Name = book_name.getText();
         String Id = Dbms.professional_looged.getString("ID");
         String query = "insert into Books values('"+Name+"')";
         System.out.println(query);
         Dbms.statement.executeUpdate(query);
         String query2 = "insert into Keeps values('"+Id+"','"+Name+"')";
         System.out.println(query2);
         Dbms.statement.executeUpdate(query2);
     }
     
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        
         try {
             if(Dbms.professional_looged.getString("Service_Category").equals("7"))
            {
                AddBooks.setDisable(false);
            }
            pro_name.setText(Dbms.professional_looged.getString("Full_Name"));
            pro_age.setText(Dbms.professional_looged.getString("Age"));
            pro_email.setText(Dbms.professional_looged.getString("email"));
            pro_mob.setText(Dbms.professional_looged.getString("Phone_Number"));
            pro_add.setText(Dbms.professional_looged.getString("Address"));
            pro_gender.setItems(gen);
            pro_gender.getSelectionModel().select(gen.indexOf(Dbms.professional_looged.getString("Gender")));
            //pro_loc.setItems(loc);
//            pro_loc.getSelectionModel().select(gen.indexOf(Dbms.professional_looged.getString("L")));
            String category = Dbms.professional_looged.getString("Service_Category");
            String ID = Dbms.professional_looged.getString("ID");
            int curr_fees = 0;
            if(category.equals("7") || category.equals("8")){
                pro_fees.setVisible(false);;
            }
            else{
                System.out.println("select Fees_Per_Visit from "+profession[Integer.parseInt(category)]+" where ID = '"+ID+"'");
                Dbms.resultSet = Dbms.statement.executeQuery("select Fees_Per_Visit from "+profession[Integer.parseInt(category)]+" where ID = "+ID);
                if(Dbms.resultSet.next()){
                    curr_fees = Dbms.resultSet.getInt(1);
                }
                pro_fees.setText(Integer.toString(curr_fees));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CustomerController.class.getName()).log(Level.SEVERE, null, ex);
        }
        show_table();
        
        
    }    
    
}
