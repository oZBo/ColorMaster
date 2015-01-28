package com.colormaster.game.activities;

import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.colormaster.game.Color;
import com.colormaster.game.GameHelper;
import com.colormaster.game.R;

public class LevelParent extends Activity implements View.OnTouchListener, View.OnClickListener {

    private final static int START_TIME = 6000; //set up start time to level. MilliSeconds
    private final static int TAP_RADIUS_RANGE = 10; //set up value to definate tap or not
    private final static int COUNT_DOWN_INTERVAL = 10; //Interval to update timers. MilliSeconds
    private final static int GAME_OVER_ANIM_DURATION = 1100; //Anim duration of the game over overlay. Milliseconds

    private final static int LEFT_SIDE = 101;
    private final static int RIGHT_SIDE = 102;

    private static int score = 0;

    private float leftSideYStart = 0, leftSideYEnd = 0;
    private float rightSideYStart = 0, rightSideYEnd = 0;

    private TextView tvLeftColor, tvRightColor, tvScore;
    private TextView tvGameOverScore, tvGameOverBest;
    private LinearLayout llLeftSide, llRightSide, llGameOver;
    private ImageButton btnReplay;

    private RelativeLayout rlGameTutorial;

    private Color colorLeft, colorRight;

    CountDownTimer countDownTimerLeft, countDownTimerRight;
    ProgressBar progressBarLeft, progressBarRight;

    private Animation fadeIn, fadeOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.level_mirror);
        initViews();
        initAnimations();
    }

    protected void initViews() {
        tvScore = (TextView) findViewById(R.id.tv_score);
        tvLeftColor = (TextView) findViewById(R.id.tv_left_side);
        tvRightColor = (TextView) findViewById(R.id.tv_right_side);
        llLeftSide = (LinearLayout) findViewById(R.id.layout_left_side);
        llLeftSide.setOnTouchListener(this);
        llRightSide = (LinearLayout) findViewById(R.id.layout_right_side);
        llRightSide.setOnTouchListener(this);
        rlGameTutorial = (RelativeLayout) findViewById(R.id.layout_tutorial);
        rlGameTutorial.setOnClickListener(this);
        progressBarLeft = (ProgressBar) findViewById(R.id.progress_left);
        progressBarRight = (ProgressBar) findViewById(R.id.progress_right);

        llGameOver = (LinearLayout) findViewById(R.id.layout_game_over);
        llGameOver.setVisibility(View.GONE);
        tvGameOverScore = (TextView) findViewById(R.id.game_over_tv_score);
        tvGameOverBest = (TextView) findViewById(R.id.game_over_tv_best);
        btnReplay = (ImageButton) findViewById(R.id.game_over_btn_replay);
        btnReplay.setOnClickListener(this);

    }

    protected void initAnimations() {

        fadeIn = AnimationUtils.loadAnimation(this, android.R.anim.fade_in);
        fadeIn.setDuration(GAME_OVER_ANIM_DURATION);
        fadeIn.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                llLeftSide.setOnTouchListener(null);
                llRightSide.setOnTouchListener(null);
                cancellCountDownTimers();
                showScoreDialog();
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                btnReplay.setClickable(true);
                score = 0;
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        fadeOut = AnimationUtils.loadAnimation(this, android.R.anim.fade_out);
        fadeOut.setDuration(GAME_OVER_ANIM_DURATION);
        fadeOut.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                tvScore.setText(""+score);
                btnReplay.setClickable(false);
                progressBarLeft.setProgress(progressBarLeft.getMax());
                progressBarRight.setProgress(progressBarRight.getMax());
                generateLeftColor();
                generateRightColor();
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                hideScoreDialog();
                llLeftSide.setOnTouchListener(LevelParent.this);
                llRightSide.setOnTouchListener(LevelParent.this);
                startSideTimer(LEFT_SIDE, GameHelper.getTimeForLevel(score));
                startSideTimer(RIGHT_SIDE, GameHelper.getTimeForLevel(score));
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    private void generateLeftColor() {
        colorLeft = new Color(this);
        tvLeftColor.setTextColor(colorLeft.getColorValue());
        tvLeftColor.setText(colorLeft.getColorText());
        llLeftSide.setBackgroundDrawable(colorLeft.getParentLayoutBgImage());
    }

    private void generateRightColor() {
        colorRight = new Color(this);
        tvRightColor.setTextColor(colorRight.getColorValue());
        tvRightColor.setText(colorRight.getColorText());
        llRightSide.setBackgroundDrawable(colorRight.getParentLayoutBgImage());
    }

    private void startSideTimer(int side, final int levelTime) {

        switch (side) {
            case LEFT_SIDE:
                progressBarLeft.setMax(levelTime);
                if (countDownTimerLeft != null) {
                    countDownTimerLeft.cancel();
                }
                countDownTimerLeft = new CountDownTimer(levelTime, COUNT_DOWN_INTERVAL) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        progressBarLeft.setProgress((int) millisUntilFinished);

                    }

                    @Override
                    public void onFinish() {
                        progressBarLeft.setProgress(0);
                        endLevel();
                    }
                };
                countDownTimerLeft.start();
                break;
            case RIGHT_SIDE:
                progressBarRight.setMax(levelTime);
                if (countDownTimerRight != null) {
                    countDownTimerRight.cancel();
                }
                countDownTimerRight = new CountDownTimer(levelTime, COUNT_DOWN_INTERVAL) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        progressBarRight.setProgress((int) millisUntilFinished);
                    }

                    @Override
                    public void onFinish() {
                        progressBarRight.setProgress(0);
                        endLevel();
                    }
                };
                countDownTimerRight.start();
                break;
        }
    }

    private void calculateLeftSide() {
        if (leftSideYStart > leftSideYEnd && leftSideYStart > leftSideYEnd + TAP_RADIUS_RANGE) {
            if (colorLeft.isColorSameAsText()) {
                nextLevel(LEFT_SIDE);
            } else {
                endLevel();
            }

        } else if (leftSideYStart < leftSideYEnd && leftSideYStart < leftSideYEnd - TAP_RADIUS_RANGE) {
            if (colorLeft.isColorSameAsText()) {
                endLevel();
            } else {
                nextLevel(LEFT_SIDE);
            }
        }

        tvScore.setText("" + score);
    }

    private void calculateRightSide() {
        if (rightSideYStart > rightSideYEnd && rightSideYStart > rightSideYEnd + TAP_RADIUS_RANGE) {
            if (colorRight.isColorSameAsText()) {
                nextLevel(RIGHT_SIDE);
            } else {
                endLevel();
            }
        } else if (rightSideYStart < rightSideYEnd && rightSideYStart < rightSideYEnd - TAP_RADIUS_RANGE) {
            if (colorRight.isColorSameAsText()) {
                endLevel();
            } else {
                nextLevel(RIGHT_SIDE);
            }
        }
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
                        break;
                }
        }
        return true;
    }

    private void hideScoreDialog() {
        llGameOver.setVisibility(View.GONE);
    }

    private void startLevel() {
        hideScoreDialog();
        generateRightColor();
        generateLeftColor();
        startSideTimer(LEFT_SIDE, GameHelper.getTimeForLevel(score));
        startSideTimer(RIGHT_SIDE, GameHelper.getTimeForLevel(score));
    }

    private void showScoreDialog() {
        if (!llGameOver.isShown()) {
            llGameOver.setVisibility(View.VISIBLE);
            tvGameOverBest.setText("0");
            tvGameOverScore.setText("" + score);
        }
    }

    @Override
    public void onBackPressed() {
        if (llGameOver.isShown()) {
            finish();
        } else {
            llGameOver.startAnimation(fadeIn);
        }
    }

    private void endLevel() {
        llGameOver.startAnimation(fadeIn);
    }

    private void cancellCountDownTimers() {
        if (countDownTimerRight != null && countDownTimerLeft != null) {
            countDownTimerLeft.cancel();
            countDownTimerRight.cancel();
        }
    }

    private void nextLevel(int side) {

        switch (side) {
            case LEFT_SIDE:
                score++;
                generateLeftColor();
                startSideTimer(LEFT_SIDE, GameHelper.getTimeForLevel(score));
                break;
            case RIGHT_SIDE:
                score++;
                generateRightColor();
                startSideTimer(RIGHT_SIDE, GameHelper.getTimeForLevel(score));
                break;
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layout_tutorial:
                rlGameTutorial.setVisibility(View.GONE);
                startLevel();
                break;
            case R.id.game_over_btn_replay:
                llGameOver.startAnimation(fadeOut);
                break;
        }
    }

}

