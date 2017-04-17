/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prt.theballgame;

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
     *

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
