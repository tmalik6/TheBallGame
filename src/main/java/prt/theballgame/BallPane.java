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
public class BallPane extends Pane {

    private final Timeline animation;
    public final List<MovableCircle> circles;
    Spawner SP = new Spawner();
    public final MovableCircle predator = new MovableCircle(Color.RED);
    private boolean bounce = false;

    public BallPane() {
        //circles.stream().map(i ->new MovableCircle(radius, Color.CORAL)).collect(Collectors.toList());
        circles = IntStream.range(0, 10).mapToObj(i -> new MovableCircle(Color.GREEN)).collect(Collectors.toList());
        getChildren().addAll(circles);
        predator.setFill(Color.RED);
        getChildren().add(predator);

        setWidth(SP.WIDTH);
        setHeight(SP.HEIGHT);

        // Create an animation for moving the ball
        animation = new Timeline(new KeyFrame(Duration.millis(10), e -> moveBall()));
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.play(); // Start animation

    }

    private void moveBall() {
        circles.forEach(x -> x.moveBall(bounce));
        predator.moveBall(bounce);
        for (int i = 0; i < circles.size(); i++) {
            bounce = false;
//            if ((predator.intersects(circles.get(i).getBoundsInLocal()))) {
//                getChildren().remove(circles.get(i));
//            }
            if (dist2(circles.get(i).x, circles.get(i).y, predator.x, predator.y) <= (2 * SP.radius)) {
                getChildren().remove(circles.get(i));
                circles.remove(i);
            }
            for (int j = i + 1; j < circles.size(); j++) {
                if (dist2(circles.get(i).x, circles.get(i).y, circles.get(j).x, circles.get(j).y) <= (2 * SP.radius + 2)) {
                    bounce = true;
                    circles.get(i).moveBall(bounce);
                    circles.get(j).moveBall(bounce);
                    System.out.println(i+" "+j+" "+circles.size());

                }
                bounce = false;
            }
        }
//        for (int i = 0; i < 19; i++) {
//            bounce = false;
//            for (int j = i+1; j < 20; j++) {
//                if (dist2(circles.get(i).x, circles.get(i).y, circles.get(j).x, circles.get(j).y) <= (2*SP.radius+2) ) // Check boundaries
//                {
//                    bounce = true;
//                    circles.get(i).moveBall(bounce);
//                    //docalc
//                } 
//                if(!bounce){
//                    circles.forEach(x->x.moveBall(bounce));
//                    predator.moveBall(bounce);
//                }
//            }
//        }
    }

    public double dist2(double x1, double y1, double x2, double y2) {
        double t1 = x1 - x2;
        double t2 = y1 - y2;
        //System.out.println(Math.sqrt(t1 * t1 + t2 * t2));
        return Math.sqrt(t1 * t1 + t2 * t2);
    }
}
