package com.braincollaboration.madcat.activities;

import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.support.v4.app.FragmentActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.braincollaboration.madcat.Color;
import com.braincollaboration.madcat.GameHelper;
import com.braincollaboration.madcat.ColorsGridViewAdapter;
import com.braincollaboration.madcat.LibraryColorObject;
import com.braincollaboration.madcat.PreferenceUtil;
import com.braincollaboration.madcat.R;
import com.braincollaboration.madcat.SoundManager;
import com.braincollaboration.madcat.Toaster;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.games.Games;

import java.util.ArrayList;

public class LevelParent extends FragmentActivity implements View.OnTouchListener, View.OnClickListener {

    private final static int MAX_CLICK_DISTANCE = 30;//set up value to definate tap or not
    private final static int COUNT_DOWN_INTERVAL = 10; //Interval to update timers. MilliSeconds
    private final static int ANIM_DUARTION = 500; //Anim duration of the game over overlay. Milliseconds
    private final static int VIBRATOR_INTERVAL = 250; //Device vibration duration. Milliseconds

    private final static int LEFT_SIDE = 101;
    private final static int RIGHT_SIDE = 102;

    private int gameDifficalty;

    private static int score = 0;

    private float leftSideYStart = 0, leftSideYEnd = 0;
    private float rightSideYStart = 0, rightSideYEnd = 0;

    private TextView tvLeftColor, tvRightColor, tvScore;
    private TextView tvGameOverScore, tvGameOverBest;
    private LinearLayout layoutLeftSide, layoutRightSide, layoutGameOver;
    private ImageButton btnReplay, btnHome, btnShare, btnLeaderboard;
    private ProgressBar progressBarLeft, progressBarRight;
    private FrameLayout layoutGameTutorial, layoutColorLibrary;

    private Color colorLeft, colorRight;
    private CountDownTimer countDownTimerLeft, countDownTimerRight;
    private Animation fadeIn, fadeOut;

    private Vibrator vibrator;

    private Toaster toaster;
    private SoundManager soundManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gameDifficalty = getIntent().getIntExtra(getString(R.string.prefkey_game_difficalty), 1);
        setContentView(R.layout.level_medium);
        vibrator = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
        score = 0;
        toaster = Toaster.init(this);
        soundManager = SoundManager.getInstance(this);
        initViews();
        initAnimations();
        initTutorialView();
    }

    @Override
    protected void onPause() {
        cancellCountDownTimers();
        layoutGameOver.setVisibility(View.VISIBLE);
        super.onPause();
    }

    protected void initViews() {
        tvScore = (TextView) findViewById(R.id.tv_score);
        tvLeftColor = (TextView) findViewById(R.id.tv_left_side);
        tvRightColor = (TextView) findViewById(R.id.tv_right_side);
        layoutLeftSide = (LinearLayout) findViewById(R.id.layout_left_side);
        layoutLeftSide.setOnTouchListener(this);
        layoutRightSide = (LinearLayout) findViewById(R.id.layout_right_side);
        layoutRightSide.setOnTouchListener(this);
        progressBarLeft = (ProgressBar) findViewById(R.id.progress_left);
        progressBarRight = (ProgressBar) findViewById(R.id.progress_right);

        layoutGameOver = (LinearLayout) findViewById(R.id.layout_game_over);
        layoutGameOver.setVisibility(View.GONE);
        tvGameOverScore = (TextView) findViewById(R.id.game_over_tv_score);
        tvGameOverBest = (TextView) findViewById(R.id.game_over_tv_best);
        btnReplay = (ImageButton) findViewById(R.id.game_over_btn_replay);
        btnReplay.setOnClickListener(this);
        btnHome = (ImageButton) findViewById(R.id.game_over_btn_home);
        btnHome.setOnClickListener(this);
        btnShare = (ImageButton) findViewById(R.id.game_over_btn_share);
        btnShare.setOnClickListener(this);
        btnLeaderboard = (ImageButton) findViewById(R.id.game_over_leaderboard);
        btnLeaderboard.setOnClickListener(this);

    }

    protected void initTutorialView() {
        layoutGameTutorial = (FrameLayout) findViewById(R.id.layout_tutorial);
        layoutColorLibrary = (FrameLayout) findViewById(R.id.layout_color_library);

        if (!PreferenceUtil.getBoolean(LevelParent.this, getString(R.string.prefkey_dont_show_tutorial), false)) {
            layoutGameTutorial.setOnClickListener(this);
            ArrayList<LibraryColorObject> colorsList = new ArrayList<>();
            LibraryColorObject red = new LibraryColorObject(R.drawable.red, getString(R.string.color_red));
            LibraryColorObject pink = new LibraryColorObject(R.drawable.pink, getString(R.string.color_pink));
            LibraryColorObject purple = new LibraryColorObject(R.drawable.purple, getString(R.string.color_purple));
            LibraryColorObject indigo = new LibraryColorObject(R.drawable.indigo, getString(R.string.color_indigo));
            LibraryColorObject blue = new LibraryColorObject(R.drawable.blue, getString(R.string.color_blue));
            LibraryColorObject teal = new LibraryColorObject(R.drawable.teal, getString(R.string.color_teal));
            LibraryColorObject green = new LibraryColorObject(R.drawable.green, getString(R.string.color_green));
            LibraryColorObject yellow = new LibraryColorObject(R.drawable.yellow, getString(R.string.color_yellow));
            LibraryColorObject orange = new LibraryColorObject(R.drawable.orange, getString(R.string.color_orange));
            LibraryColorObject brown = new LibraryColorObject(R.drawable.brown, getString(R.string.color_brown));

            colorsList.add(red);
            colorsList.add(pink);
            colorsList.add(purple);
            colorsList.add(indigo);
            colorsList.add(blue);
            colorsList.add(teal);
            colorsList.add(green);
            colorsList.add(yellow);
            colorsList.add(orange);
            colorsList.add(brown);

            GridView gridView = (GridView) findViewById(R.id.gridview_color_library);
            gridView.setOnTouchListener(new View.OnTouchListener() {
                private static final int MAX_CLICK_DURATION = 1000;
                private static final int MAX_CLICK_DISTANCE = 15;

                private long pressStartTime;
                private float pressedX;
                private float pressedY;

                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN: {
                            pressStartTime = System.currentTimeMillis();
                            pressedX = event.getX();
                            pressedY = event.getY();
                            break;
                        }
                        case MotionEvent.ACTION_UP: {
                            long pressDuration = System.currentTimeMillis() - pressStartTime;
                            if (pressDuration < MAX_CLICK_DURATION && distance(pressedX, pressedY, event.getX(), event.getY()) < MAX_CLICK_DISTANCE) {
                                layoutColorLibrary.setVisibility(View.GONE);
                                PreferenceUtil.putBoolean(LevelParent.this, getString(R.string.prefkey_dont_show_tutorial), true);
                                startLevel();
                            }
                        }
                    }
                    return false;
                }
            });
            ColorsGridViewAdapter adapter = new ColorsGridViewAdapter(this, colorsList);
            gridView.setAdapter(adapter);
        } else {
            layoutGameTutorial.setVisibility(View.GONE);
            layoutColorLibrary.setVisibility(View.GONE);
            startLevel();
        }
    }

    private float distance(float x1, float y1, float x2, float y2) {
        float dx = x1 - x2;
        float dy = y1 - y2;
        float distanceInPx = (float) Math.sqrt(dx * dx + dy * dy);
        return pxToDp(distanceInPx);
    }

    private float pxToDp(float px) {
        return px / getResources().getDisplayMetrics().density;
    }

    protected void initAnimations() {

        fadeIn = AnimationUtils.loadAnimation(this, android.R.anim.fade_in);
        fadeIn.setDuration(ANIM_DUARTION);
        fadeIn.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                layoutLeftSide.setOnTouchListener(null);
                layoutRightSide.setOnTouchListener(null);
                cancellCountDownTimers();
                showScoreDialog();
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                btnReplay.setClickable(true);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        fadeOut = AnimationUtils.loadAnimation(this, android.R.anim.fade_out);
        fadeOut.setDuration(ANIM_DUARTION);
        fadeOut.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                score = 0;
                tvScore.setText("" + score);
                btnReplay.setClickable(false);
                progressBarLeft.setProgress(progressBarLeft.getMax());
                progressBarRight.setProgress(progressBarRight.getMax());
                generateLeftColor();
                generateRightColor();
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                hideGameOverDialog();
                layoutLeftSide.setOnTouchListener(LevelParent.this);
                layoutRightSide.setOnTouchListener(LevelParent.this);
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
        layoutLeftSide.setBackgroundDrawable(colorLeft.getParentLayoutBgImage());
    }

    private void generateRightColor() {
        colorRight = new Color(this);
        tvRightColor.setTextColor(colorRight.getColorValue());
        tvRightColor.setText(colorRight.getColorText());
        layoutRightSide.setBackgroundDrawable(colorRight.getParentLayoutBgImage());
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
        if (leftSideYStart > leftSideYEnd && leftSideYStart > leftSideYEnd + MAX_CLICK_DISTANCE) {
            if (colorLeft.isColorSameAsText()) {
                nextLevel(LEFT_SIDE);
            } else {
                endLevel();
            }

        } else if (leftSideYStart < leftSideYEnd && leftSideYStart < leftSideYEnd - MAX_CLICK_DISTANCE) {
            if (colorLeft.isColorSameAsText()) {
                endLevel();
            } else {
                nextLevel(LEFT_SIDE);
            }
        }

        tvScore.setText("" + score);
    }

    private void calculateRightSide() {
        if (rightSideYStart > rightSideYEnd && rightSideYStart > rightSideYEnd + MAX_CLICK_DISTANCE) {
            if (colorRight.isColorSameAsText()) {
                nextLevel(RIGHT_SIDE);
            } else {
                endLevel();
            }
        } else if (rightSideYStart < rightSideYEnd && rightSideYStart < rightSideYEnd - MAX_CLICK_DISTANCE) {
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

    private void hideGameOverDialog() {
        layoutGameOver.setVisibility(View.GONE);
    }

    private void startLevel() {
        hideGameOverDialog();
        generateRightColor();
        generateLeftColor();
        startSideTimer(LEFT_SIDE, GameHelper.getTimeForLevel(score));
        startSideTimer(RIGHT_SIDE, GameHelper.getTimeForLevel(score));
    }

    private void showScoreDialog() {
        if (!layoutGameOver.isShown()) {
            layoutGameOver.setVisibility(View.VISIBLE);
            tvGameOverBest.setText("" + GameHelper.loadBestScore(this, gameDifficalty));
            tvGameOverScore.setText("" + score);
        }
    }

    @Override
    public void onBackPressed() {
        if (layoutGameTutorial.isShown()) {
            finish();
        } else if (layoutGameOver.isShown()) {
            finish();
        } else if(layoutColorLibrary.isShown()){
            finish();
        }else{
            layoutGameOver.startAnimation(fadeIn);
        }
    }

    private void endLevel() {
        soundManager.play(R.raw.incorrect);
        if (score > GameHelper.loadBestScore(LevelParent.this, gameDifficalty)) {
            GameHelper.saveBestScore(LevelParent.this, gameDifficalty, score);
        }
        pushAccomplishments(score, false);
        vibrator.vibrate(VIBRATOR_INTERVAL);
        layoutGameOver.startAnimation(fadeIn);
    }

    private void cancellCountDownTimers() {
        if (countDownTimerRight != null && countDownTimerLeft != null) {
            countDownTimerLeft.cancel();
            countDownTimerRight.cancel();
        }
    }

    private void nextLevel(int side) {
        soundManager.play(R.raw.correct);
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
                layoutGameTutorial.setVisibility(View.GONE);
                break;
            case R.id.game_over_btn_replay:
                layoutGameOver.startAnimation(fadeOut);
                break;
            case R.id.game_over_btn_home:
                onBackPressed();
                break;
            case R.id.game_over_btn_share:
                GameHelper.shareScore(this, getString(R.string.share_dialog_part_1) + score + getString(R.string.share_dialog_part_2) + getString(R.string.share_dialog_part_3));
                break;
            case R.id.game_over_leaderboard:
                pushAccomplishments(score, true);
                break;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    void pushAccomplishments(int score, boolean canShowLeaderboard) {
        if (GameHelper.haveNetworkConnection(this)) {
            GoogleApiClient googleApiClient = GooglePlayAuthorization.getGoogleApiClient();
            try {
                switch (gameDifficalty) {
                    case 1:
                        Games.Leaderboards.submitScore(googleApiClient, getString(R.string.leaderboard_easy), score);
                        if (canShowLeaderboard)
                            startActivityForResult(Games.Leaderboards.getLeaderboardIntent(googleApiClient, getString(R.string.leaderboard_easy)), 5000);
                        break;
                    case 2:
                        Games.Leaderboards.submitScore(googleApiClient, getString(R.string.leaderboard_hard), score);
                        if (canShowLeaderboard)
                            startActivityForResult(Games.Leaderboards.getLeaderboardIntent(googleApiClient, getString(R.string.leaderboard_hard)), 5000);
                        break;
                }
            } catch (Exception ex) {
                toaster.toast(getString(R.string.signin_other_error));
            }
        } else {
            toaster.toast(getString(R.string.check_internet));
        }
    }

}

