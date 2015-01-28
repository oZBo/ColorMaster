package com.colormaster.game;

public class GameHelper {


    /**
     * @param score
     * @return time in millis for level, according to game score.
     */
    public static int getTimeForLevel(int score) {

        if (score <= 5 && score >= 0) {
            return 4700;
        } else if (score <= 10 && score > 5) {
            return 4500;
        } else if (score <= 15 && score > 10) {
            return 4200;
        } else if (score <= 20 && score > 15) {
            return 3700;
        } else if (score <= 25 && score > 20) {
            return 3200;
        } else if (score <= 30 && score > 25) {
            return 2700;
        } else if (score <= 35 && score > 30) {
            return 2200;
        } else if (score <= 40 && score > 35) {
            return 2000;
        } else if (score <= 45 && score > 40) {
            return 1500;
        } else if (score <= 50 && score > 45) {
            return 1500;
        } else if (score <= 55 && score > 50) {
            return 1400;
        } else if (score <= 60 && score > 55) {
            return 1350;
        } else if (score <= 65 && score > 60) {
            return 1300;
        } else if (score <= 70 && score > 65) {
            return 1250;
        }
        return 1;
    }
}
