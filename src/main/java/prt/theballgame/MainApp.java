package prt.theballgame;

import javafx.animation.TranslateTransition;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Scene.fxml"));
        BallPane BP = new BallPane();
        
        Scene scene = new Scene(BP, 500, 500);
        
        //scene.getStylesheets().add("/styles/Styles.css");
        
        stage.setTitle("JavaFX and Maven");
        stage.setScene(scene);
        stage.show();
        //*********
//        final Group group = new Group(createInstructions(), circle);
//        Scene scene2 = new Scene(group, 600, 400, Color.CORNSILK);
//        final TranslateTransition transition = createTranslateTransition(circle);
//        Stage stage = new Stage();
//        moveCircleOnKeyPress(scene2, circle);
//        moveCircleOnMousePress(scene2, circle, transition);
//        stage.setScene(scene2);
//        stage.show();
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
