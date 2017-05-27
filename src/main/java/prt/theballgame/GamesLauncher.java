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

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.slf4j.LoggerFactory;

/**
 *
 * @author daniel
 */
public class GamesLauncher {
    private static GamesLauncher instance;
    public  static boolean isPause = false;   
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(MainApp.class); 
    public  static Stage Mainstage = new Stage();
    
    /**
     *
     * Elinditja az 1es.Játék Scene-t.
     *
     */
    public static void game() {
        BallPane BP = new BallPane();
        final Group group = new Group(createInstructions(), BP);
        Scene scene;
        if (!BallSettings.isFulldisplay()) {
            scene = new Scene(group, 750, 650);
        } else {
            scene = new Scene(group);
        }
        scene.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
            if (key.getCode() == KeyCode.UP) {
                int sign = 1;
                BP.controllBall(sign, BallPane.predator);
            } else if (key.getCode() == KeyCode.DOWN) {
                int sign = 2;
                BP.controllBall(sign, BallPane.predator);
            } else if (key.getCode() == KeyCode.LEFT) {
                int sign = 3;
                BP.controllBall(sign, BallPane.predator);
            } else if (key.getCode() == KeyCode.RIGHT) {
                int sign = 4;
                BP.controllBall(sign, BallPane.predator);
            } else if (key.getCode() == KeyCode.W && BallSettings.multiplayer) {
                int sign = 1;
                BP.controllBall(sign, BallPane.predator2);
            } else if (key.getCode() == KeyCode.S && BallSettings.multiplayer) {
                int sign = 2;
                BP.controllBall(sign, BallPane.predator2);
            } else if (key.getCode() == KeyCode.A && BallSettings.multiplayer) {
                int sign = 3;
                BP.controllBall(sign, BallPane.predator2);
            } else if (key.getCode() == KeyCode.D && BallSettings.multiplayer) {
                int sign = 4;
                BP.controllBall(sign, BallPane.predator2);
            } else if (key.getCode() == KeyCode.Z) {
                BP.instaend();
            } else if (key.getCode() == KeyCode.P) {
                if (isPause) {
                    BP.Start();
                    isPause = false;
                } else {
                    BP.Pause();
                    isPause = true;
                }
            } else {
                logger.info("not usuable key used");
                String badkey = key.getCode() + "";
                logger.info(badkey);
            }
        });
        Mainstage.setTitle("TheGame-Normal");
        Mainstage.setScene(scene);        
        if(BallSettings.isFulldisplay()){Mainstage.setFullScreen(true);}
        Mainstage.show();        
    }
    public static void game2() {
        int k = 0;
        BallPane BP2 = new BallPane(k);
        final Group group = new Group(createInstructions(), BP2);
        Scene scene;
        if (!BallSettings.isFulldisplay()) {
            scene = new Scene(group, 750, 650);
        } else {
            scene = new Scene(group);
        }
        scene.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
            if (key.getCode() == KeyCode.UP) {
                int sign = 1;
                BP2.controllBall(sign, BallPane.predator);
            } else if (key.getCode() == KeyCode.DOWN) {
                int sign = 2;
                BP2.controllBall(sign, BallPane.predator);
            } else if (key.getCode() == KeyCode.LEFT) {
                int sign = 3;
                BP2.controllBall(sign, BallPane.predator);
            } else if (key.getCode() == KeyCode.RIGHT) {
                int sign = 4;
                BP2.controllBall(sign, BallPane.predator);
            } else if (key.getCode() == KeyCode.Z) {
                BP2.instaend();
            } else if (key.getCode() == KeyCode.P) {
                if (isPause) {
                    BP2.Start();
                    isPause = false;
                } else {
                    BP2.Pause();
                    isPause = true;
                }
            } else {
                logger.info("not usuable key used");
                String badkey = key.getCode() + "";
                logger.info(badkey);
            }
        });
        Mainstage.setTitle("TheGame-ReverseMod");
        Mainstage.setScene(scene);        
        if(BallSettings.isFulldisplay()){Mainstage.setFullScreen(true);}
        Mainstage.show();        
    }

    @SuppressWarnings("checkstyle:javadocmethod")
    private static Label createInstructions() {
        Label instructions = new Label(
                "Use the arrow keys to move the red circle\n"
                + "You win if you get more green circle then the computer\n"
                + "Hit P for Pause"
        );
        instructions.setTextFill(Color.DARKBLUE);
        return instructions;
    }
}
