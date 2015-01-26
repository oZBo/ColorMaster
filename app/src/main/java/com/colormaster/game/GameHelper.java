package com.colormaster.game;

public class GameHelper {

    public static int getLevelTimeSurvivalMode(int score){

        switch(score){
            case 5:
                return 4700;
            case 10:
                return 4500;
            case 15:
                return 4200;
            case 20:
                return 3700;
            case 25:
                return 3200;
            case 30:
                return 2700;
            case 33:
                return 2200;
            case 36:
                return 2000;
            case 39:
                return 1500;
            case 42:
                return 1500;
            case 45:
                return 1500 ;
            case 48:
                return 1000;
            case 51:
                return 4700;
            case 55:
                return 4700;
        }

        return 1;
    }

}
