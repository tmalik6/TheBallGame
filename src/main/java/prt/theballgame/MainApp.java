package prt.theballgame;
//CHECKSTYLE:ON
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

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.slf4j.LoggerFactory;

/**
 * A program alapját képező osztály.
 *
 */
public class MainApp extends Application {

    private static MainApp instance;
    public static boolean isPause = false;
    public static BallPane BP;
    public static Stage Mainstage = new Stage();
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(MainApp.class);
    
    /**
     * 
     * Konstruktor, amely az objektumot egy static változóba helyezi.
     * 
     */
    public MainApp() {
        instance = this;
    }
    /**
     * Visszadja ezt a static-us változót.
     *
     * @return static-us változó
     */
    public static MainApp getInstance() {
        return instance;
    }
    
    /**
     * Elinditja az első Scene-t.
     *
     * @param stage a stage amire a Scene kerül
     *  
     */
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Scene.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
        Mainstage.setTitle("Main Menu");
        Mainstage.setScene(scene);
        Mainstage.show();
    }
    
    @SuppressWarnings("checkstyle:javadocmethod")
    private void initGame()
    {
        BP = new BallPane();
    }
    
    /**
     *
     * Elinditja az a Játék Scene-t.
     * 
     */
    public void game() {
        initGame();
        final Group group = new Group(createInstructions(), BP);
        Scene scene = new Scene(group, 750, 650);
        scene.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
            if (key.getCode() == KeyCode.UP) {
                int sign = 1;
                BP.controllBall(sign);
            }
            else if (key.getCode() == KeyCode.DOWN) {
                int sign = 2;
                BP.controllBall(sign);
            } 
            else if (key.getCode() == KeyCode.LEFT) {
                int sign = 3;
                BP.controllBall(sign);
            }
            else if (key.getCode() == KeyCode.RIGHT) {
                int sign = 4;
                BP.controllBall(sign);
            }
            else if (key.getCode() == KeyCode.P) {
                if (isPause){
                    BP.Start(BP.animation);
                    isPause = false;
                }
                else
                {
                    BP.Pause(BP.animation);
                    isPause = true;
                }
            }else {
                logger.info("not usuable key used");
                String badkey = key.getCode() + "";
                logger.info(badkey);
            }
        });
        Mainstage.setTitle("TheGame");
        Mainstage.setScene(scene);
        Mainstage.show();
    }
    @SuppressWarnings("checkstyle:javadocmethod")
    private Label createInstructions() {
        Label instructions = new Label(
                "Use the arrow keys to move the red circle\n"
                + "You win if you get more green circle then the computer\n"
                + "Hit P for Pause"
        );
        instructions.setTextFill(Color.DARKBLUE);
        return instructions;
    }
    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
