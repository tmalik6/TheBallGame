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

import prt.theballgame.Spawners.*;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.slf4j.LoggerFactory;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

/**
 * Az Animációt és a háttérszámításokat végző osztály.
 */
public class BallPane extends Pane {

    public   static  Timeline animation;    
    private  final   int[] eredmeny = new int[2];
    private  static  Color PredatorColor;
    private  static  Color PredatorColor2;
    private  static  Color CirclesColor;
    private  boolean bounce = false;
    private  static  int KorSzama;
    private  final   org.slf4j.Logger logger = LoggerFactory.getLogger(BallPane.class);
    private  static  int radius;    
    public   static   MovableCircle predator;
    public   static   MovableCircle predator2;
    public            List<MovableCircle> circles;
    TimerScheduler scTimer = new TimerScheduler();
    /**
     *
     * Konstruktor, mely létrehoza a köröket, Pane-t és az Animációt.
     *
     */
    public BallPane() {
        Spawner.checker();
        KorSzama = BallSettings.getKorSzama();
        radius = BallSettings.getRadius();
        PredatorColor = BallSettings.getPredatorColor();
        PredatorColor2 = BallSettings.getPredatorColor2();
        CirclesColor = BallSettings.getCirclesColor();
        predator = new MovableCircle(PredatorColor);
        predator2 = new MovableCircle(PredatorColor2);
        circles = IntStream.range(0, KorSzama).mapToObj(i -> new MovableCircle(CirclesColor)).collect(Collectors.toList());
        getChildren().addAll(circles);
        getChildren().add(predator);
        getChildren().add(predator2);
        logger.info("Körök elkészültek");

        animation = new Timeline(new KeyFrame(Duration.millis(7), e -> moveBall()));
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.play();

    }
    
    public BallPane(int k) {
        scTimer.restart();
        scTimer.start();
        Spawner.checker();
        KorSzama = BallSettings.getKorSzama();
        radius = BallSettings.getRadius();
        PredatorColor = BallSettings.getPredatorColor();
        CirclesColor = BallSettings.getCirclesColor();
        predator = new MovableCircle(PredatorColor,0);
        circles = IntStream.range(0, KorSzama).mapToObj(i -> new MovableCircle(CirclesColor)).collect(Collectors.toList());
        getChildren().addAll(circles);
        getChildren().add(predator);
        logger.info("Körök elkészültek");
        animation = new Timeline(new KeyFrame(Duration.millis(7), e -> moveBall_2()));
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.play();
        logger.info(predator.getCenterX() + "");
        logger.info(predator.getCenterY() + "");
    }

    @SuppressWarnings("checkstyle:javadocmethod")
    private void moveBall() {
        GamesLauncher.Mainstage.setOnCloseRequest(e ->  System.exit(0));
        circles.forEach(x -> x.moveBall(bounce));
        predator.moveBall(bounce);
        predator2.moveBall(bounce);
        for (int i = 0; i < circles.size(); i++) {
            try {
                bounce = false;
                if (dist2(circles.get(i).x, circles.get(i).y, predator.x, predator.y) <= (2 * radius)) {
                    getChildren().remove(circles.get(i));
                    circles.remove(i);
                    eredmeny[0] = eredmeny[0] + 1;
                }

                if (dist2(circles.get(i).x, circles.get(i).y, predator2.x, predator2.y) <= (2 * radius)) {
                    getChildren().remove(circles.get(i));
                    circles.remove(i);
                    eredmeny[1] = eredmeny[1] + 1;
                }
                if (circles.isEmpty()) {
                    animation.pause();
                    Move();
                }

                if (dist2(predator.x, predator.y, predator2.x, predator2.y) <= (2 * radius)) {
                    bounce = true;
                    predator.moveBall(bounce);
                    predator2.moveBall(bounce);
                    bounce = false;
                }
                for (int j = i + 1; j < circles.size(); j++) {
                    if (dist2(circles.get(i).x, circles.get(i).y, circles.get(j).x, circles.get(j).y) <= (2 * radius + 2)) {
                        bounce = true;
                        circles.get(i).moveBall(bounce);
                        circles.get(j).moveBall(bounce);
                        //String utkoz = "Utkozos történt:" + i + ".számú kör és" +j + ".számú kör között. Maradék körök száma:" + circles.size(); 
                        //logger.info(utkoz); --> not vizsga any more, kivettem
                    }
                    bounce = false;
                }
            } catch (IndexOutOfBoundsException | NullPointerException in) {
                logger.info("unexpected");
                if (circles.isEmpty()) {
                    animation.pause();
                    Move();
                }
            }
        }

    }
    
    @SuppressWarnings("checkstyle:javadocmethod")
    private void moveBall_2() {
        GamesLauncher.Mainstage.setOnCloseRequest(e ->  System.exit(0));
        //animation.pause();
        //logger.info("X:" + predator.getCenterX() + "");
        //logger.info("Y:" + predator.getCenterY() + "");
        circles.forEach(x -> x.moveBall(bounce));
        predator.moveBall(bounce);
        for (int i = 0; i < circles.size(); i++) {
            try {
                bounce = false;
                if (dist2(circles.get(i).x, circles.get(i).y, predator.x, predator.y) <= (2 * radius)) {
                    while( !circles.isEmpty())
                    {                 
                        for (int j = 0; j < circles.size(); j++) {
                        circles.remove(j);
                        getChildren().remove(j);
                        }                   
                    }                    
                    if (circles.isEmpty()){
                    animation.pause();                    
                    scTimer.end();
                    Move();
                    }
                    eredmeny[0] = eredmeny[0] + 1;
                }
                if (circles.isEmpty()) {
                    scTimer.end();
                    animation.pause();
                    Move();
                }
                for (int j = i + 1; j < circles.size(); j++) {
                    if (dist2(circles.get(i).x, circles.get(i).y, circles.get(j).x, circles.get(j).y) <= (2 * radius + 2)) {
                        bounce = true;
                        circles.get(i).moveBall(bounce);
                        circles.get(j).moveBall(bounce);
                    }
                    bounce = false;
                }
            } catch (IndexOutOfBoundsException | NullPointerException in) {
                logger.info("unexpected");
                if (circles.isEmpty()) {
                    animation.pause();
                    Move();
                }
            }
        }
    }

    @SuppressWarnings("checkstyle:javadocmethod")
    private double dist2(double x1, double y1, double x2, double y2) {
        double t1 = x1 - x2;
        double t2 = y1 - y2;
        return Math.sqrt(t1 * t1 + t2 * t2);
    }
      
    @SuppressWarnings("checkstyle:javadocmethod")
    @FXML
    private void Move() {
        String ered1 = "A játékos eredménye:" + eredmeny[0] + "";
        String ered2 = "A gép eredménye:" + eredmeny[1] + "";
        logger.info("Végeredmény:");
        logger.info(ered1);
        logger.info(ered2);
        BallSettings.setEredmeny(eredmeny);
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/EredmenyScene.fxml"));
            Scene scene = new Scene(root);
            scene.getStylesheets().add("/styles/Styles.css");
            GamesLauncher.Mainstage.setScene(scene);
            GamesLauncher.Mainstage.setFullScreen(false);
            GamesLauncher.Mainstage.show();            

        } catch (IOException ex) {
            Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Beállítja a játékos/ok körének új koordinátáit és vektorait.
     *
     * @param sign melyik irányba kell mozdulni a körnek {@code 1,2} abban az
     * esetben, ha függőlegesen változtason irányt {@code 3,4} abban az esetben,
     * ha vízszintesen változtason irányt
     * @param labda amelyiket mozgatni szeretnénk
     */
    public void controllBall(int sign, MovableCircle labda) {
        switch (sign) {
            case 1:
                labda.controllBall(1);
            case 2:
                labda.controllBall(2);
            case 3:
                labda.controllBall(3);
            case 4:
                labda.controllBall(4);
        }
    }

    public void instaend()
    {
        while( !circles.isEmpty())
        {                 
            for (int j = 0; j < circles.size(); j++) {
            circles.remove(j);
            getChildren().remove(j);
            }                   
        } 
        if (circles.isEmpty()){
            animation.pause();
            if(BallSettings.getMod() == 2)
            {   
                scTimer.end();
            }
            Move();
        }
    }
    
    /**
     * Megállítja az Animációt.
     *
     */
    public void Pause() {
        animation.pause();
    }

    /**
     * Újraindítja az Animációt.
     *
     */
    public void Start() {
        animation.play();
    }  
}
