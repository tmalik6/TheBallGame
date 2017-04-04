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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import static prt.theballgame.MainApp.BP;

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
    
    private final ObservableList<String> datos = FXCollections.observableArrayList("RED", "BLUE", "YELLOW", "GREEN", "BLACK","BROWN", "GOLD","VIOLET","ORANGE","CHOCOLATE");
    
    @FXML
    private ChoiceBox CirclesColor;
    
    private final ToggleGroup group = new ToggleGroup();

    @FXML
    private void SaveButtonAction() {
        if (!KorSzamTF.getText().isEmpty() && mehet) {
            KorSzamChange = KorSzamTF.getText();
            try {
                int foo = Integer.parseInt(KorSzamChange);
                BP.setKorSzama(foo);
            } catch (NumberFormatException ex) {
                Logger.getLogger(OptionScheneController.class.getName()).log(Level.SEVERE, null, ex);
                HibaLabel.setText("Ez nem szám!");
                mehet = false;
            }
            KorSzamLabel.setText(BP.getKorSzama() + "");
        }        
        if(group.getSelectedToggle().getUserData().toString().equals("Multi")){
            BP.multiplayer = true;
        }
        else{
            BP.multiplayer = false;
        }
        //predator
        if(mehet){
        Valasztott = (String) PredatorColor.getValue();
        ColorChange = Color.valueOf(Valasztott);
        PredatorSzinLabel.setText(getColourName(ColorChange) + "");
        BallPane.setPredatorColor(ColorChange);
        //predator2
        Valasztott = (String) Predator2Color.getValue();
        ColorChange = Color.valueOf(Valasztott);
        Predator2SzinLabel.setText(getColourName(ColorChange) + "");
        BallPane.setPredatorColor2(ColorChange);
        //circles
        Valasztott = (String) CirclesColor.getValue();
        ColorChange = Color.valueOf(Valasztott);
        CirclesSzinLabel.setText(getColourName(ColorChange) + "");
        BallPane.setCirclesColor(ColorChange);
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
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        KorSzamLabel.setText(BP.getKorSzama() + "");
        PredatorSzinLabel.setText(getColourName(BP.getPredatorColor()) + "");
        Predator2SzinLabel.setText(getColourName(BP.getPredatorColor2()) + "");
        CirclesSzinLabel.setText(getColourName(BP.getCirclesColor()) + "");
        PredatorColor.setItems(datos);
        Predator2Color.setItems(datos);
        CirclesColor.setItems(datos);
        PredatorColor.getSelectionModel().select(0);
        Predator2Color.getSelectionModel().select(2);
        CirclesColor.getSelectionModel().select(3);  
        SingleRadio.setToggleGroup(group);
        MultiRadio.setToggleGroup(group);
        SingleRadio.setSelected(true);
        SingleRadio.setUserData("Single");
        MultiRadio.setUserData("Multi");
    }

    public static String getColourName(Color becolor) {
        double piros = becolor.getRed();
        double zud = becolor.getGreen();
        double kek = becolor.getBlue();

        if (piros == Color.RED.getRed() && zud == Color.RED.getGreen() && kek == Color.RED.getBlue()) {
            return "RED";
        }
        if (piros == Color.BLUE.getRed() && zud == Color.BLUE.getGreen() && kek == Color.BLUE.getBlue()) {
            return "BLUE";
        }
        if (piros == Color.YELLOW.getRed() && zud == Color.YELLOW.getGreen() && kek == Color.YELLOW.getBlue()) {
            return "YELLOW";
        }
        if (piros == Color.GREEN.getRed() && zud == Color.GREEN.getGreen() && kek == Color.GREEN.getBlue()) {
            return "GREEN";
        }
        if (piros == Color.BLACK.getRed() && zud == Color.BLACK.getGreen() && kek == Color.BLACK.getBlue()) {
            return "BLACK";
        }
        if (piros == Color.BROWN.getRed() && zud == Color.BROWN.getGreen() && kek == Color.BROWN.getBlue()) {
            return "BROWN";
        }
        if (piros == Color.GOLD.getRed() && zud == Color.GOLD.getGreen() && kek == Color.GOLD.getBlue()) {
            return "GOLD";
        }
        if (piros == Color.VIOLET.getRed() && zud == Color.VIOLET.getGreen() && kek == Color.VIOLET.getBlue()) {
            return "VIOLET";
        }
        if (piros == Color.CHOCOLATE.getRed() && zud == Color.CHOCOLATE.getGreen() && kek == Color.CHOCOLATE.getBlue()) {
            return "CHOCOLATE";
        }
        if (piros == Color.ORANGE.getRed() && zud == Color.ORANGE.getGreen() && kek == Color.ORANGE.getBlue()) {
            return "ORANGE";
        }
        return null;
    }
}
