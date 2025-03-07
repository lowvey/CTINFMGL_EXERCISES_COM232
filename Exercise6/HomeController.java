import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javax.naming.spi.DirStateFactory;
import javax.xml.transform.Templates;

public class HomeController implements Initializable{

    ObservableList<User> userlist = FXCollections.observableArrayList();

    @FXML
    private Label welcomelabel;

    @FXML
    private TableView<User> mytable;

    @FXML
    private TableColumn<User, String> passwordcolumn;

    @FXML
    private TableColumn<User, String> usernamecolumn;

    @FXML
    private Button createbutton;

    @FXML
    private Button deletebutton;

    @FXML
    private Button updatebutton;
    
    @FXML
    private TextField nametextfield;

    @FXML
    private TextField passwordtextfield;

    public void displayName(String username){
        welcomelabel.setText("Welcome back, " + username);
    }


    @Override
    public void initialize(URL url, ResourceBundle rb){
        initializeCol();
        displayUsers();
    }

    private void initializeCol(){
        usernamecolumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        passwordcolumn.setCellValueFactory(new PropertyValueFactory<>("password"));
    }

    private void displayUsers(){

        userlist.clear();

        ResultSet result = DatabaseHandler.getUsers();

        try {
            while (result.next())
            {
                String username = result.getString("Username");
                String password = result.getString("Password");

                userlist.add(new User(username, password));
            }

        } catch (SQLException e){
            e.printStackTrace();
        }

        mytable.setItems(userlist);

    }

    @FXML
    private void createUser(ActionEvent event) {

        String username = nametextfield.getText();
        String password = passwordtextfield.getText();

        username = username.trim();
        password = password.trim();

        if (username.length() == 0){
            Alert alert = new Alert(AlertType.ERROR);
            alert.setContentText("Empty username");
            alert.showAndWait();
            
        }

        if (password.length() == 0){
            Alert alert = new Alert(AlertType.ERROR);
            alert.setContentText("Empty password");
            alert.showAndWait();
            
        }

        User user = new User (username, password);

        if(DatabaseHandler.addUser(user)){
            
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setContentText("Account Created");
            alert.showAndWait();

        } else {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setContentText("Account creation failed");
                alert.showAndWait();
            }

        displayUsers();

    }     

    @FXML
    private void deleteUser(ActionEvent event) {

       User user = mytable.getSelectionModel().getSelectedItem();

        String username = user.getUsername();

        if (DatabaseHandler.deleteUser(user)){

            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setContentText("Account Deleted");
            alert.showAndWait();
            displayUsers(); 
        

        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setContentText("Account deletion failed");
            alert.showAndWait();
        }
    }

    @FXML
    private void updateUser(ActionEvent event) {

        String username = nametextfield.getText();

        String password = passwordtextfield.getText();  

        username = username.trim();
        password = password.trim();

        if(username.length() == 0)
        {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setContentText("Empty username");
            alert.showAndWait();
            
        }

        if(password.length() == 0)
        {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setContentText("Empty password");
            alert.showAndWait();
           
        }

       User user = new User(username, password);

        if(DatabaseHandler.updateUser(user)){
            
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setContentText("Account Updated");
            alert.showAndWait();
        
            
        }
        else
        {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setContentText("Can't update account");
            alert.showAndWait();
           
        }

        displayUsers(); 
    }
       
}


