package prt.theballgame;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class MainApp extends Application {

    private static MainApp instance;
    public static boolean isPause = false;
    public static BallPane BP;

    public MainApp() {
        instance = this;
    }

    public static MainApp getInstance() {
        return instance;
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Scene.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
        stage.setTitle("Main Menu");
        stage.setScene(scene);
        stage.show();
    }
    
    public void initGame()
    {
        BP = new BallPane();
    }
    
    public void game() {
        initGame();
        Scene scene = new Scene(BP, 500, 500);
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
                System.out.println("not usuable key used"); //->log
                System.out.println(key.getCode());
            }
        });
        Stage stage = new Stage();
        stage.setTitle("TheGame");
        stage.setScene(scene);
        stage.show();
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
