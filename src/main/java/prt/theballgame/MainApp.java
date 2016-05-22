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

    public void game() {
        BallPane BP = new BallPane();
        Scene scene = new Scene(BP, 500, 500);
        Stage stage = new Stage();
        stage.setTitle("TheGame");
        stage.setScene(scene);
        stage.show();
    }

//    public void handler(Scene scene) {
//        scene.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
//            switch (key.getCode()) {
//                case KeyCode.UP:
//                    statements // they are executed if variable == c1
//                    break;
//                case c2:
//                    statements // they are executed if variable == c2
//                    break;
//                case c3:
//                case c4:
//                    statements // they are executed if variable ==  any of the above c's
//                    break;
//                    . . .
//                default:
//                    statements // they are executed if none of the above case is satisfied
//                    break;
//            }
//            if ( == ) {
//                System.out.println("You pressed enter");
//            }
//        });
//    }

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
