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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author daniel
 */
public class FXMLController implements Initializable {

    @FXML
    private javafx.scene.control.Button closeButton;
    
    @FXML
    private javafx.scene.control.Button startButton;
    
    @FXML
    private javafx.scene.control.Button optionButton;

    @FXML
    public void startButtonAction() {
        if(BallSettings.getMod() == 1){
        GamesLauncher.game();
        }
        else{
            GamesLauncher.game2();
        }
    }
    
    @FXML
    public void optionButtonAction() {
        Stage stage;
        Parent root;

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/OptionSchene.fxml"));
            root = loader.load(); 
            
            stage = (Stage) optionButton.getScene().getWindow();
            Scene scene = new Scene(root);
            scene.getStylesheets().add("/styles/Styles.css");
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {
            Logger.getLogger(OptionScheneController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void closeButtonAction() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
        System.exit(0);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
}

