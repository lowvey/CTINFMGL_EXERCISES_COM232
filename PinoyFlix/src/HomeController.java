import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HomeController {
    
    @FXML
    Label welcomelabel;

    public void displayName(String username) {
        welcomelabel.setText("Welcome, " + username);
    }
}
