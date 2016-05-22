package prt.theballgame;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

public class FXMLController implements Initializable {

    @FXML
    private javafx.scene.control.Button closeButton;
    
    @FXML
    private javafx.scene.control.Button startButton;
    
    @FXML
    private javafx.scene.control.Button optionButton;

    @FXML
    public void startButtonAction() {
       MainApp.getInstance().game();
    }

    @FXML
    private void optionButtonAction() {

    }

    @FXML
    private void closeButtonAction() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
}

