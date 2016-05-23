package prt.theballgame;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author daniel
 */
public class BallPane extends Pane {

    public static Timeline animation;
    public final List<MovableCircle> circles;
    Spawner SP = new Spawner();
    public final MovableCircle predator = new MovableCircle(Color.RED);
    public final MovableCircle predator2 = new MovableCircle(Color.YELLOW);
    private boolean bounce = false;
    public static int[] eredmeny = new int[2];

    public BallPane() {
        circles = IntStream.range(0, 10).mapToObj(i -> new MovableCircle(Color.GREEN)).collect(Collectors.toList());
        getChildren().addAll(circles);
        predator.setFill(Color.RED);
        getChildren().add(predator);
        predator2.setFill(Color.YELLOW);
        getChildren().add(predator2);

        setWidth(SP.WIDTH);
        setHeight(SP.HEIGHT);

        animation = new Timeline(new KeyFrame(Duration.millis(7), e -> moveBall()));
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.play();

    }

    private void moveBall() {
        circles.forEach(x -> x.moveBall(bounce));
        predator.moveBall(bounce);
        predator2.moveBall(bounce);
        for (int i = 0; i < circles.size(); i++) {
            bounce = false;
            if (dist2(circles.get(i).x, circles.get(i).y, predator.x, predator.y) <= (2 * SP.radius)) {
                getChildren().remove(circles.get(i));
                circles.remove(i);
                eredmeny[0] = eredmeny[0] + 1;
            }
            try {
                if (dist2(circles.get(i).x, circles.get(i).y, predator2.x, predator2.y) <= (2 * SP.radius)) {
                    getChildren().remove(circles.get(i));
                    circles.remove(i);
                    eredmeny[1] = eredmeny[1] + 1;
                }
                if(circles.isEmpty()){
                    animation.pause();
                    Move();
                }
            } catch (IndexOutOfBoundsException | NullPointerException in) {
                System.out.println("what"); 
                if(circles.isEmpty()){
                    animation.pause();
                    Move();
                }
            }            
            if (dist2(predator.x, predator.y, predator2.x, predator2.y) <= (2 * SP.radius)) {
                bounce = true;
                predator.moveBall(bounce);
                predator2.moveBall(bounce);
                bounce = false;
            }
            for (int j = i + 1; j < circles.size(); j++) {
                if (dist2(circles.get(i).x, circles.get(i).y, circles.get(j).x, circles.get(j).y) <= (2 * SP.radius + 2)) {
                    bounce = true;
                    circles.get(i).moveBall(bounce);
                    circles.get(j).moveBall(bounce);
                    //System.out.println(i+" "+j+" "+circles.size()); -> logba
                }
                bounce = false;
            }
        }

    }

    public double dist2(double x1, double y1, double x2, double y2) {
        double t1 = x1 - x2;
        double t2 = y1 - y2;
        return Math.sqrt(t1 * t1 + t2 * t2);
    }

    @FXML
    private void Move() {
         System.out.println(eredmeny[0]);
         System.out.println(eredmeny[1]);
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/EredmenyScene.fxml"));
            Scene scene = new Scene(root);
            scene.getStylesheets().add("/styles/Styles.css");
            MainApp.Mainstage.setScene(scene);
            MainApp.Mainstage.show();

        } catch (IOException ex) {
            Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void controllBall(int sign) {
        switch (sign) {
            case 1:
                predator.controllBall(1);
            case 2:
                predator.controllBall(2);
            case 3:
                predator.controllBall(3);
            case 4:
                predator.controllBall(4);
        }
    }

    public void Pause(Timeline animation) {
        this.animation.pause();
    }

    public void Start(Timeline animation) {
        this.animation.play();
    }

    public int initdata() {
        if (eredmeny[0] == eredmeny[1]) {
            return 0;
        }
        if (eredmeny[0] > eredmeny[1]) {
            return 1;
        } else {
            return 2;
        }
    }
}
