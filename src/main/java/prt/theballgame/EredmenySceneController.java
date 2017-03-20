package prt.theballgame;
//CHECKSTYLE:OFF
/*
 * #%L
 * TheBallGame
 * %%
 * Copyright (C) 2016 Debreceni Egyetem, Informatikai Kar
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 * #L%
 */

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
import static prt.theballgame.MainApp.BP;

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
        //BallPane BP = new BallPane();        
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
