/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prt.theballgame;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

/**
 *
 * @author daniel
 */
public class BallPane extends Pane{
    
    
    private final Timeline animation;
    private final List<MovableCircle> circles;
    Spawner SP = new Spawner();
    private final MovableCircle predator = new MovableCircle(Color.RED);
    
    public BallPane() {
        //circles.stream().map(i ->new MovableCircle(radius, Color.CORAL)).collect(Collectors.toList());
        circles = IntStream.range(0,10).mapToObj(i->new MovableCircle( Color.GREEN)).collect(Collectors.toList());
        getChildren().addAll(circles);
        predator.setFill(Color.RED);
        getChildren().add(predator);

        setWidth(SP.WIDTH);
        setHeight(SP.HEIGHT);

        // Create an animation for moving the ball
        animation = new Timeline(new KeyFrame(Duration.millis(20), e -> moveBall()));
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.play(); // Start animation
        
    }

    private void moveBall() {
        circles.forEach(x->x.moveBall());
        predator.moveBall();
    }
}
