package prt.theballgame;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import static prt.theballgame.MainApp.BP;
/**
 * FXML Controller class
 *
 * @author daniel
 */
public class OptionScheneController implements Initializable {
    
    private String KorSzamChange;
    
    @FXML
    private Label KorSzamLabel;
    
    @FXML
    private Label HibaLabel;
    
    @FXML
    private javafx.scene.control.Button SaveButton;
    
    @FXML
    private javafx.scene.control.Button BackButton;
    
    @FXML
    private TextField KorSzamTF;
    
    @FXML
    private void SaveButtonAction() {
        if(KorSzamTF.getText() != null){
           KorSzamChange = KorSzamTF.getText();
        }
        try{ 
            int foo = Integer.parseInt(KorSzamChange);
            BP.setKorSzama(foo);
        }catch(NumberFormatException ex){
             Logger.getLogger(OptionScheneController.class.getName()).log(Level.SEVERE, null, ex);
             HibaLabel.setText("Ez nem szám");
        }
        KorSzamLabel.setText(BP.getKorSzama()+ "");
    }
    
    @FXML
    private void BackButtonAction() {
         Stage stage;
        Parent root;

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Scene.fxml"));
            root = loader.load(); 
            
            stage = (Stage) BackButton.getScene().getWindow();
            Scene scene = new Scene(root);
            scene.getStylesheets().add("/styles/Styles.css");
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {
            Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        KorSzamLabel.setText(BP.getKorSzama()+ "");
    }    
    
}
