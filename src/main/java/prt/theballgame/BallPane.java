package prt.theballgame;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

/**
 *
 * @author daniel
 */
public class BallPane extends Pane {

    public final Timeline animation;
    public final List<MovableCircle> circles;
    Spawner SP = new Spawner();
    public final MovableCircle predator = new MovableCircle(Color.RED);
    private boolean bounce = false;

    public BallPane() {
        circles = IntStream.range(0, 10).mapToObj(i -> new MovableCircle(Color.GREEN)).collect(Collectors.toList());
        getChildren().addAll(circles);
        predator.setFill(Color.RED);
        getChildren().add(predator);

        setWidth(SP.WIDTH);
        setHeight(SP.HEIGHT);

        animation = new Timeline(new KeyFrame(Duration.millis(10), e -> moveBall()));
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.play();

    }

    private void moveBall() {
        circles.forEach(x -> x.moveBall(bounce));
        predator.moveBall(bounce);        
        for (int i = 0; i < circles.size(); i++) {
            bounce = false;
            if (dist2(circles.get(i).x, circles.get(i).y, predator.x, predator.y) <= (2 * SP.radius)) {
                getChildren().remove(circles.get(i));
                circles.remove(i);
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
    public void Pause(Timeline animation)
    {
        this.animation.pause();
    }
    public void Start(Timeline animation)
    {
        this.animation.play();
    }
}
