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

import prt.theballgame.Spawners.MovableCircle;
import prt.theballgame.Spawners.Spawner;
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
import prt.theballgame.Spawners.*;

/**
 * Az Animációt és a háttérszámításokat végző osztály.
 */
public class BallPane extends Pane {

    public static Timeline animation;
    private final List<MovableCircle> circles;
    Spawner SP = new Spawner();
    private final MovableCircle predator = new MovableCircle(Color.RED);
    private final MovableCircle predator2 = new MovableCircle(Color.YELLOW);
    private boolean bounce = false;
    public  static int KorSzama = 10;
    public static int[] eredmeny = new int[2];
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(BallPane.class);

    /**
     *
     * Konstruktor, mely létrehoza a köröket, Pane-t és az Animációt.
     *
     */
    public BallPane() {
        circles = IntStream.range(0, KorSzama).mapToObj(i -> new MovableCircle(Color.GREEN)).collect(Collectors.toList());
        getChildren().addAll(circles);
        predator.setFill(Color.RED);
        getChildren().add(predator);
        predator2.setFill(Color.YELLOW);
        getChildren().add(predator2);
        logger.info("Körök elkészültek");

        setWidth(SP.WIDTH);
        setHeight(SP.HEIGHT);

        animation = new Timeline(new KeyFrame(Duration.millis(7), e -> moveBall()));
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.play();

    }

    @SuppressWarnings("checkstyle:javadocmethod")
    private void moveBall() {
        circles.forEach(x -> x.moveBall(bounce));
        predator.moveBall(bounce);
        predator2.moveBall(bounce);
        for (int i = 0; i < circles.size(); i++) {
            try {
                bounce = false;
                if (dist2(circles.get(i).x, circles.get(i).y, predator.x, predator.y) <= (2 * SP.radius)) {
                    getChildren().remove(circles.get(i));
                    circles.remove(i);
                    eredmeny[0] = eredmeny[0] + 1;
                }

                if (dist2(circles.get(i).x, circles.get(i).y, predator2.x, predator2.y) <= (2 * SP.radius)) {
                    getChildren().remove(circles.get(i));
                    circles.remove(i);
                    eredmeny[1] = eredmeny[1] + 1;
                }
                if (circles.isEmpty()) {
                    animation.pause();
                    Move();
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
    private double dist2(double x1, double y1, double x2, double y2) {
        double t1 = x1 - x2;
        double t2 = y1 - y2;
        return Math.sqrt(t1 * t1 + t2 * t2);
    }

    public static void setKorSzama(int KorSzama) {
        BallPane.KorSzama = KorSzama;
    }

    public static int getKorSzama() {
        return KorSzama;
    }
    
    @SuppressWarnings("checkstyle:javadocmethod")
    @FXML
    private void Move() {
        String ered1 = "A játékos eredménye:" + eredmeny[0] + "";
        String ered2 = "A gép eredménye:" + eredmeny[1] + "";
        logger.info("Végeredmény:");
        logger.info(ered1);
        logger.info(ered2);
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

    /**
     * Beállítja a játékos körének új koordinátáit és vektorait.
     *
     * @param sign melyik irányba kell mozdulni a körnek {@code 1,2} abban az
     * esetben, ha függőlegesen változtason irányt {@code 3,4} abban az esetben,
     * ha vízszintesen változtason irányt
     */
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

    /**
     * Megállítja az Animációt.
     *
     * @param animation Maga az Animáció
     */
    public void Pause(Timeline animation) {
        this.animation.pause();
    }

    /**
     * Újraindítja az Animációt.
     *
     * @param animation Maga az Animáció
     */
    public void Start(Timeline animation) {
        this.animation.play();
    }

    /**
     * Kiszámítja a győztest.
     *
     * @return a győztes kódja {@code 0} ha döntetlen {@code 1} ha a játékos a
     * győztes {@code 2} ha a computer a győztes
     */
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
