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

    public static Drawable getColorImageBackground(Context mContext, int colorNumber) {
        int randomSeedBackground = random.nextInt(18);
        switch (randomSeedBackground) {
            case 0:
                return mContext.getResources().getDrawable(R.drawable.bg_1);
            case 1:
                return mContext.getResources().getDrawable(R.drawable.bg_2);
            case 2:
                return mContext.getResources().getDrawable(R.drawable.bg_3);
            case 3:
                return mContext.getResources().getDrawable(R.drawable.bg_4);
            case 4:
                return mContext.getResources().getDrawable(R.drawable.bg_5);
            case 5:
                return mContext.getResources().getDrawable(R.drawable.bg_6);
            case 6:
                return mContext.getResources().getDrawable(R.drawable.bg_7);
            case 7:
                return mContext.getResources().getDrawable(R.drawable.bg_8);
            case 8:
                return mContext.getResources().getDrawable(R.drawable.bg_9);
            case 9:
                return mContext.getResources().getDrawable(R.drawable.bg_10);
            case 10:
                return mContext.getResources().getDrawable(R.drawable.bg_11);
            case 11:
                return mContext.getResources().getDrawable(R.drawable.bg_12);
            case 12:
                return mContext.getResources().getDrawable(R.drawable.bg_13);
            case 13:
                return mContext.getResources().getDrawable(R.drawable.bg_14);
            case 14:
                return mContext.getResources().getDrawable(R.drawable.bg_15);
            case 15:
                return mContext.getResources().getDrawable(R.drawable.bg_16);
            case 16:
                return mContext.getResources().getDrawable(R.drawable.bg_17);
            case 17:
                return mContext.getResources().getDrawable(R.drawable.bg_18);
            case 18:
                return  mContext.getResources().getDrawable(R.drawable.bg_19);
            default:
                return null;
        }
    }
}

