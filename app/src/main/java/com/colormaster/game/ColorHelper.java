package com.colormaster.game;

import android.content.Context;
import android.graphics.drawable.Drawable;

import java.util.Random;

class ColorHelper {

    private static Random random = new Random();

    public static String getColorName(Context mContext, int colorNumber) {
        switch (colorNumber) {
            case 0:
                return mContext.getResources().getString(R.string.color_red);
            case 1:
                return mContext.getResources().getString(R.string.color_pink);
            case 2:
                return mContext.getResources().getString(R.string.color_purple);
            case 3:
                return mContext.getResources().getString(R.string.color_deep_purple);
            case 4:
                return mContext.getResources().getString(R.string.color_indigo);
            case 5:
                return mContext.getResources().getString(R.string.color_blue);
            case 6:
                return mContext.getResources().getString(R.string.color_cyan);
            case 7:
                return mContext.getResources().getString(R.string.color_teal);
            case 8:
                return mContext.getResources().getString(R.string.color_green);
            case 9:
                return mContext.getResources().getString(R.string.color_light_green);
            case 10:
                return mContext.getResources().getString(R.string.color_lime);
            case 11:
                return mContext.getResources().getString(R.string.color_yellow);
            case 12:
                return mContext.getResources().getString(R.string.color_amber);
            case 13:
                return mContext.getResources().getString(R.string.color_orange);
            case 14:
                return mContext.getResources().getString(R.string.color_deep_orange);
            case 15:
                return mContext.getResources().getString(R.string.color_brown);
            case 16:
                return mContext.getResources().getString(R.string.color_grey);
            default:
                return "WTF???";
        }
    }

    public static int getColorValue(Context mContext, int colorNumber) {
        switch (colorNumber) {
            case 0:
                return mContext.getResources().getColor(R.color.red);
            case 1:
                return mContext.getResources().getColor(R.color.pink);
            case 2:
                return mContext.getResources().getColor(R.color.purple);
            case 3:
                return mContext.getResources().getColor(R.color.deep_purple);
            case 4:
                return mContext.getResources().getColor(R.color.indigo);
            case 5:
                return mContext.getResources().getColor(R.color.blue);
            case 6:
                return mContext.getResources().getColor(R.color.cyan);
            case 7:
                return mContext.getResources().getColor(R.color.teal);
            case 8:
                return mContext.getResources().getColor(R.color.green);
            case 9:
                return mContext.getResources().getColor(R.color.light_green);
            case 10:
                return mContext.getResources().getColor(R.color.lime);
            case 11:
                return mContext.getResources().getColor(R.color.yellow);
            case 12:
                return mContext.getResources().getColor(R.color.amber);
            case 13:
                return mContext.getResources().getColor(R.color.orange);
            case 14:
                return mContext.getResources().getColor(R.color.deep_orange);
            case 15:
                return mContext.getResources().getColor(R.color.brown);
            case 16:
                return mContext.getResources().getColor(R.color.grey);
            default:
                return 505;
        }
    }

    public static Drawable getColorImageBackground(Context mContext, int colorNumber){
        int randomSeedBackground = random.nextInt(3);
        switch (colorNumber){
            case 0: //RED
                switch(randomSeedBackground){
                    case 0:
                        return mContext.getResources().getDrawable(R.drawable.red_bg_1);
                    case 1:
                        return mContext.getResources().getDrawable(R.drawable.red_bg_2);
                    case 2:
                        return mContext.getResources().getDrawable(R.drawable.red_bg_3);
                }
                break;
            case 1: // ORANGE
                switch(randomSeedBackground){
                    case 0:
                        return mContext.getResources().getDrawable(R.drawable.orange_bg_1);
                    case 1:
                        return mContext.getResources().getDrawable(R.drawable.orange_bg_2);
                    case 2:
                        return mContext.getResources().getDrawable(R.drawable.orange_bg_3);
                }
                break;
            case 2: //YELLOW
                switch(randomSeedBackground){
                    case 0:
                        return mContext.getResources().getDrawable(R.drawable.yellow_bg_1);
                    case 1:
                        return mContext.getResources().getDrawable(R.drawable.yellow_bg_2);
                    case 2:
                        return mContext.getResources().getDrawable(R.drawable.yellow_bg_3);
                }
                break;
            case 3: //GREEN
                switch(randomSeedBackground){
                    case 0:
                        return mContext.getResources().getDrawable(R.drawable.green_bg_1);
                    case 1:
                        return mContext.getResources().getDrawable(R.drawable.green_bg_2);
                    case 2:
                        return mContext.getResources().getDrawable(R.drawable.green_bg_3);
                }
                break;
            case 4: //BLUE
                switch(randomSeedBackground){
                    case 0:
                        return mContext.getResources().getDrawable(R.drawable.blue_bg_1);
                    case 1:
                        return mContext.getResources().getDrawable(R.drawable.blue_bg_2);
                    case 2:
                        return mContext.getResources().getDrawable(R.drawable.blue_bg_3);
                }
                break;
            case 5: //INDIGO
                switch(randomSeedBackground){
                    case 0:
                        return mContext.getResources().getDrawable(R.drawable.indigo_bg_1);
                    case 1:
                        return mContext.getResources().getDrawable(R.drawable.indigo_bg_2);
                    case 2:
                        return mContext.getResources().getDrawable(R.drawable.indigo_bg_3);
                }
                break;
            case 6: //VIOLET
                switch(randomSeedBackground){
                    case 0:
                        return mContext.getResources().getDrawable(R.drawable.violet_bg_1);
                    case 1:
                        return mContext.getResources().getDrawable(R.drawable.violet_bg_2);
                    case 2:
                        return mContext.getResources().getDrawable(R.drawable.violet_bg_3);
                }
                break;
            default:
                return mContext.getResources().getDrawable(R.drawable.abc_btn_radio_material);
        }
        return null;
    }
}

