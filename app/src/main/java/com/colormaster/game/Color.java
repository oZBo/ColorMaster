package com.colormaster.game;

import android.content.Context;
import android.graphics.drawable.Drawable;

import java.util.Random;

public class Color {

    private static final int COLORS_NUMBER = 16;

    private int colorValue;
    private Drawable parentLayoutBgImage;
    private int textColorInactive;
    private int parentLayoutColorInactive;
    private String colorText;
    private boolean colorSameAsText;
    private int randomColor;
    private Context mContext;
    private Random random = new Random();

    public Color(Context mContext) {
        this.mContext = mContext;
        randomColor = random.nextInt(COLORS_NUMBER);
        colorSameAsText = random.nextBoolean();
        colorValue = ColorHelper.getColorValue(mContext, randomColor);
        colorText = ColorHelper.getColorName(mContext, randomColor);
        textColorInactive = mContext.getResources().getColor(R.color.green_200);
        parentLayoutColorInactive = mContext.getResources().getColor(R.color.green_500);
        configureColors();
    }

    private void configureColors() {
        int tempRandColor = random.nextInt(COLORS_NUMBER);
        if (!colorSameAsText) {
            while (tempRandColor == randomColor)
                tempRandColor = random.nextInt(COLORS_NUMBER);
            colorValue = ColorHelper.getColorValue(mContext, tempRandColor);
        }

        int tempLayoutColor = random.nextInt(COLORS_NUMBER);
        while (tempLayoutColor == randomColor || tempLayoutColor == tempRandColor)
            tempLayoutColor = random.nextInt(COLORS_NUMBER);
        parentLayoutBgImage = ColorHelper.getColorImageBackground(mContext, tempLayoutColor);
    }

    public int getTextColorInactive() {
        return textColorInactive;
    }

    public int getParentLayoutColorInactive() {
        return parentLayoutColorInactive;
    }

    public int getColorValue() {
        return colorValue;
    }

    public String getColorText() {
        return colorText;
    }

    public Drawable getParentLayoutBgImage() {
        return parentLayoutBgImage;
    }

    public boolean isColorSameAsText(){

        return colorSameAsText;
    }

}
