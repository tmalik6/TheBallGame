package prt.theballgame;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author daniel
 */
public class OptionScheneController implements Initializable {

    private String KorSzamChange;
    
    private Color ColorChange;
    
    private String Valasztott;
    
    private boolean mehet = true;

    @FXML
    private Label KorSzamLabel;

    @FXML
    private Label PredatorSzinLabel;

    @FXML
    private Label Predator2SzinLabel;

    @FXML
    private Label CirclesSzinLabel;
    
    @FXML
    private Label DisplayLabel;

    @FXML
    private Label HibaLabel;
    
    @FXML
    private RadioButton SingleRadio;
    
    @FXML
    private RadioButton MultiRadio;  

    @FXML
    private javafx.scene.control.Button SaveButton;

    @FXML
    private javafx.scene.control.Button BackButton;

    @FXML
    private TextField KorSzamTF;

    @FXML
    private ChoiceBox PredatorColor;

    @FXML
    private ChoiceBox Predator2Color;
    
    @FXML
    private ChoiceBox CirclesColor;
    
    @FXML
    private ChoiceBox DisplayChoice;
    
    private final ToggleGroup group = new ToggleGroup();

    @FXML
    private void SaveButtonAction() {
        if (!KorSzamTF.getText().isEmpty() && mehet) {
            KorSzamChange = KorSzamTF.getText();
            try {
                int foo = Integer.parseInt(KorSzamChange);
                BallSettings.setKorSzama(foo);
            } catch (NumberFormatException ex) {
                Logger.getLogger(OptionScheneController.class.getName()).log(Level.SEVERE, null, ex);
                HibaLabel.setText("Ez nem szám!");
                mehet = false;
            }
            KorSzamLabel.setText(BallSettings.getKorSzama() + "");
        }        
        if(group.getSelectedToggle().getUserData().toString().equals("Multi")){
            BallSettings.setMultiplayer(true);
        }
        else{
            BallSettings.setMultiplayer(false);
        }
        //predator
        if(mehet){
        Valasztott = (String) PredatorColor.getValue();
        ColorChange = Color.valueOf(Valasztott);
        PredatorSzinLabel.setText(BallSettings.getColourName(ColorChange) + "");
        BallSettings.setPredatorColor(ColorChange);
        //predator2
        Valasztott = (String) Predator2Color.getValue();
        ColorChange = Color.valueOf(Valasztott);
        Predator2SzinLabel.setText(BallSettings.getColourName(ColorChange) + "");
        BallSettings.setPredatorColor2(ColorChange);
        //circles
        Valasztott = (String) CirclesColor.getValue();
        ColorChange = Color.valueOf(Valasztott);
        CirclesSzinLabel.setText(BallSettings.getColourName(ColorChange) + "");
        BallSettings.setCirclesColor(ColorChange);
        //display 
        Valasztott = (String) DisplayChoice.getValue();
        Boolean b = Boolean.valueOf(Valasztott);
        BallSettings.setFulldisplay(b);        
        DisplayLabel.setText(BallSettings.isFulldisplay()+ "");
        HibaLabel.setText("Mentve");        
        }
        mehet = true;
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
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //korszam
        KorSzamLabel.setText(BallSettings.getKorSzama() + "");
        //colors
        PredatorSzinLabel.setText(BallSettings.getColourName(BallSettings.getPredatorColor()) + "");
        Predator2SzinLabel.setText(BallSettings.getColourName(BallSettings.getPredatorColor2()) + "");
        CirclesSzinLabel.setText(BallSettings.getColourName(BallSettings.getCirclesColor()) + "");
        PredatorColor.setItems(BallSettings.datos);
        Predator2Color.setItems(BallSettings.datos);
        CirclesColor.setItems(BallSettings.datos);
        PredatorColor.getSelectionModel().select(0);
        Predator2Color.getSelectionModel().select(2);
        CirclesColor.getSelectionModel().select(3);  
        //multi
        SingleRadio.setToggleGroup(group);
        MultiRadio.setToggleGroup(group);
        if(!BallSettings.isMultiplayer())SingleRadio.setSelected(true);
        else MultiRadio.setSelected(true);
        SingleRadio.setUserData("Single");
        MultiRadio.setUserData("Multi");
        //fulldisplay
        DisplayLabel.setText(BallSettings.isFulldisplay()+ "");
        DisplayChoice.setItems(BallSettings.igaz_hamis);
        if(!BallSettings.isFulldisplay())DisplayChoice.getSelectionModel().select(1);
        else DisplayChoice.getSelectionModel().select(0);
    }   
}
