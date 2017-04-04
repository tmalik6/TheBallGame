/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prt.theballgame;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.paint.Color;

/**
 *
 * @author daniel
 */
public class BallSettings {
    public  static  ObservableList<String> datos = FXCollections.observableArrayList("RED", "BLUE", "YELLOW", "GREEN", "BLACK","BROWN", "GOLD","VIOLET","ORANGE","CHOCOLATE");
    public  static  Color PredatorColor = Color.RED;
    public  static  Color PredatorColor2 = Color.YELLOW;
    public  static  Color CirclesColor = Color.GREEN;
    public  static  int KorSzama = 10;
    public  static  boolean multiplayer = false;
    public  static  int radius = 20;
    public  static  int[] eredmeny = new int[2];
    
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
