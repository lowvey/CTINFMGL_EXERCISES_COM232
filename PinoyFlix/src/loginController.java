import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import java.io.IOException;
import com.jfoenix.controls.JFXButton;
import java.time.Duration;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javax.management.Notification;
import javax.management.NotificationFilterSupport;
import javax.swing.text.Position;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

public class loginController {

    @FXML
    TextField userfield;

    @FXML 
    PasswordField passfield; 

    @FXML
    JFXButton loginbtn;

    private Stage stage;
    private Scene scene;
    private Parent root;


    public void loginbtnHandler(ActionEvent event) throws IOException {
       String uname = userfield.getText();
       String pword = passfield.getText();

       if (dataBaseHandler.validateLogin(uname, pword)){
         FXMLLoader loader = new FXMLLoader(getClass().getResource("home.fxml")); 

         root = loader.load();
         
         HomeController homeController = loader.getController();
         homeController.displayName(uname);

         stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
         scene = new Scene(root);
         stage.setScene(scene);
         stage.show();
     
        
        } else {
        error();
       }
       
    }
    public void error(){
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Login Error");
        alert.setHeaderText(null);
        alert.setContentText("Username or password is incorrect.");
        alert.showAndWait();
    }
}

