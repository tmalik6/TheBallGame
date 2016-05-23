package prt.theballgame;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author daniel
 */
public class EredmenySceneController implements Initializable {

    @FXML
    private javafx.scene.control.Button exitbutton;
    
    @FXML
    private javafx.scene.control.Button backbutton;
    
    @FXML
    private javafx.scene.control.Button eredmeny;
    
    @FXML
    private Label winnerlabel;
    
    @FXML
    private void exitButtonAction() {
        Stage stage = (Stage) exitbutton.getScene().getWindow();
        stage.close();
    }
    
    @FXML
    private void backButtonAction(ActionEvent event) {
        Stage stage;
        Parent root;

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Scene.fxml"));
            root = loader.load(); 
            
            stage = (Stage) backbutton.getScene().getWindow();
            Scene scene = new Scene(root);
            scene.getStylesheets().add("/styles/Styles.css");
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {
            Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
     @FXML
    private void showeredmeny(ActionEvent event) {
        BallPane BP = new BallPane();        
        if (BP.eredmeny[0] == BP.eredmeny[1]) {
           winnerlabel.setText("Equal  :|"); 
        }
        if (BP.eredmeny[0] > BP.eredmeny[1]) {
           winnerlabel.setText("You");
        } else {
            winnerlabel.setText("The Computer");
        }

    }    
}
