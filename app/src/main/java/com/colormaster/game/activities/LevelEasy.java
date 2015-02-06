package com.colormaster.game.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.colormaster.game.Color;
import com.colormaster.game.R;

import java.util.Random;

public class LevelEasy extends Activity implements View.OnTouchListener, View.OnClickListener {

    private TextView tvLeftColor, tvRightColor, tvScore;
    private LinearLayout layoutLeftSide, layoutRightSide;
    private RelativeLayout rlGameTutorial;

    private Color leftColor, rightColor;

    private float leftSideYStart = 0, leftSideYEnd = 0;
    private float rightSideYStart = 0, rightSideYEnd = 0;
    private static int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.level_screen);
        initViews();
        generateRightColor();
        generateLeftColor();
        nextLevel();
    }

    private void initViews() {
        tvScore = (TextView) findViewById(R.id.tv_score);
        tvLeftColor = (TextView) findViewById(R.id.tv_left_side);
        tvRightColor = (TextView) findViewById(R.id.tv_right_side);
        layoutLeftSide = (LinearLayout) findViewById(R.id.layout_left_side);
        layoutRightSide = (LinearLayout) findViewById(R.id.layout_right_side);
        rlGameTutorial = (RelativeLayout) findViewById(R.id.layout_tutorial);
        rlGameTutorial.setOnClickListener(this);
    }

    private void generateLeftColor() {
        leftColor = new Color(this);
        tvLeftColor.setTextColor(leftColor.getTextColorInactive());
        tvLeftColor.setText(leftColor.getColorText());
        layoutLeftSide.setBackgroundColor(leftColor.getParentLayoutColorInactive());
        layoutLeftSide.setOnTouchListener(null);
    }

    private void generateRightColor() {
        rightColor = new Color(this);
        tvRightColor.setTextColor(rightColor.getTextColorInactive());
        tvRightColor.setText(rightColor.getColorText());
        layoutRightSide.setBackgroundColor(rightColor.getParentLayoutColorInactive());
        layoutRightSide.setOnTouchListener(null);
    }

    private void nextLevel() {
        Random random = new Random();
        int side = random.nextInt(2);

        switch (side) {
            case 0: //Left side
                tvLeftColor.setTextColor(leftColor.getColorValue());
                layoutLeftSide.setBackgroundDrawable(leftColor.getParentLayoutBgImage());
                layoutLeftSide.setOnTouchListener(this);
                break;
            case 1: // Right side
                tvRightColor.setTextColor(rightColor.getColorValue());
                layoutRightSide.setBackgroundDrawable(rightColor.getParentLayoutBgImage());
                layoutRightSide.setOnTouchListener(this);
                break;
        }
    }

    private void calculateLeftSide() {
        if (leftSideYStart >= leftSideYEnd) {
            if (leftColor.isColorSameAsText())
                score++;
            else
                score = 0;
        } else {
            if (!leftColor.isColorSameAsText())
                score++;
            else
                score = 0;
        }
        generateLeftColor();
        tvScore.setText("" + score);
    }

    private void calculateRightSide() {
        if (rightSideYStart >= rightSideYEnd) {
            if (rightColor.isColorSameAsText())
                score++;
            else
                score = 0;
        } else {
            if (!rightColor.isColorSameAsText())
                score++;
            else
                score = 0;

        }
        generateRightColor();
        tvScore.setText("" + score);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (v.getId()) {
            case R.id.layout_left_side:
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        leftSideYStart = event.getY();
                        break;
                    case MotionEvent.ACTION_UP:
                        leftSideYEnd = event.getY();
                        calculateLeftSide();
                        nextLevel();
                        break;
                }
                break;
            case R.id.layout_right_side:
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        rightSideYStart = event.getY();
                        break;
                    case MotionEvent.ACTION_UP:
                        rightSideYEnd = event.getY();
                        calculateRightSide();
                        nextLevel();
                        break;
                }
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.layout_tutorial:
                rlGameTutorial.setVisibility(View.GONE);
                break;
        }
    }
}
