package prt.theballgame;
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

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.paint.Color;
import org.slf4j.LoggerFactory;

/**
 *
 * @author daniel
 */
public class BallSettings {
    public  static  ObservableList<String> datos = FXCollections.observableArrayList("RED", "BLUE", "YELLOW", "GREEN", "BLACK","BROWN", "GOLD","VIOLET","ORANGE","CHOCOLATE");
    public  static  ObservableList<String> igaz_hamis = FXCollections.observableArrayList("true", "false");
    public  static  Color PredatorColor = Color.RED;
    public  static  Color PredatorColor2 = Color.YELLOW;
    public  static  Color CirclesColor = Color.GREEN;
    public  static  int KorSzama = 10;
    public  static  boolean multiplayer = false;
    public  static  int radius = 20;
    public  static  int[] eredmeny = new int[2];
    public  static  boolean fulldisplay = false;
    private static  final org.slf4j.Logger logger = LoggerFactory.getLogger(BallSettings.class);
    private static  final GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice(); 
    public  static  int Width;   //750;//MainApp.Width; //-1920 -750
    public  static  int Height;  //650;//MainApp.Height; //-1080 -650

    public static void setKorSzama(int KorSzama) {
        BallSettings.KorSzama = KorSzama;
    }
    public static void setPredatorColor(Color PredatorColor) {
        BallSettings.PredatorColor = PredatorColor;
    }
    public static void setPredatorColor2(Color PredatorColor2) {
        BallSettings.PredatorColor2 = PredatorColor2;
    }
    public static void setCirclesColor(Color CirclesColor) {
        BallSettings.CirclesColor = CirclesColor;
    }
    public static int getKorSzama() {
        return KorSzama;
    }
    public static Color getPredatorColor() {
        return PredatorColor;
    }
    public static Color getPredatorColor2() {
        return PredatorColor2;
    }
    public static Color getCirclesColor() {
        return CirclesColor;
    }
    public static boolean isMultiplayer() {
        return multiplayer;
    }
    public static int getRadius() {
        return radius;
    }
    public static void setMultiplayer(boolean multiplayer) {
        BallSettings.multiplayer = multiplayer;
    }
    public static void setRadius(int radius) {
        BallSettings.radius = radius;
    }
    public static int getEredmeny() {        
        if (eredmeny[0] == eredmeny[1]) {
            return 0;
        }
        if (eredmeny[0] > eredmeny[1]) {
            return 1;
        } else {
            return 2;
        }
    }
    public static void setEredmeny(int[] eredmeny) {
        BallSettings.eredmeny = eredmeny;
    }
        public static void setFulldisplay(boolean fulldisplay) {
        BallSettings.fulldisplay = fulldisplay;
    }
    public static boolean isFulldisplay() {
        return fulldisplay;
    }
    public static int getWidth() {
        if(!isFulldisplay()){
        Width = 750;}
        else{
        Width = gd.getDisplayMode().getWidth();
        }
        logger.info(Width + "");
        return Width;
    }
    public static int getHeight() {
        if(!isFulldisplay()){
        Height = 650;}
        else{
        Height = gd.getDisplayMode().getHeight();
        }
        logger.info(Height + "");
        return Height;
    }
    public static void setWidth(int Width) {
        BallSettings.Width = Width;
    }
    public static void setHeight(int Height) {
        BallSettings.Height = Height;
    }    
    public static String getColourName(Color becolor) {
        double piros = becolor.getRed();
        double zud = becolor.getGreen();
        double kek = becolor.getBlue();

        if (piros == Color.RED.getRed() && zud == Color.RED.getGreen() && kek == Color.RED.getBlue()) {
            return "RED";
        }
        if (piros == Color.BLUE.getRed() && zud == Color.BLUE.getGreen() && kek == Color.BLUE.getBlue()) {
            return "BLUE";
        }
        if (piros == Color.YELLOW.getRed() && zud == Color.YELLOW.getGreen() && kek == Color.YELLOW.getBlue()) {
            return "YELLOW";
        }
        if (piros == Color.GREEN.getRed() && zud == Color.GREEN.getGreen() && kek == Color.GREEN.getBlue()) {
            return "GREEN";
        }
        if (piros == Color.BLACK.getRed() && zud == Color.BLACK.getGreen() && kek == Color.BLACK.getBlue()) {
            return "BLACK";
        }
        if (piros == Color.BROWN.getRed() && zud == Color.BROWN.getGreen() && kek == Color.BROWN.getBlue()) {
            return "BROWN";
        }
        if (piros == Color.GOLD.getRed() && zud == Color.GOLD.getGreen() && kek == Color.GOLD.getBlue()) {
            return "GOLD";
        }
        if (piros == Color.VIOLET.getRed() && zud == Color.VIOLET.getGreen() && kek == Color.VIOLET.getBlue()) {
            return "VIOLET";
        }
        if (piros == Color.CHOCOLATE.getRed() && zud == Color.CHOCOLATE.getGreen() && kek == Color.CHOCOLATE.getBlue()) {
            return "CHOCOLATE";
        }
        if (piros == Color.ORANGE.getRed() && zud == Color.ORANGE.getGreen() && kek == Color.ORANGE.getBlue()) {
            return "ORANGE";
        }
        return null;
    }
}
