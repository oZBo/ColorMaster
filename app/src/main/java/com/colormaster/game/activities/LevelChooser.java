package com.colormaster.game.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;

import com.colormaster.game.R;

public class LevelChooser extends Activity implements View.OnClickListener, Animation.AnimationListener, View.OnTouchListener {

    int gameDifficalty = 1; //value from 1 to 2

    private ImageButton btnGameDifficalty, btnHelp, btnMarkapp, btnPlay, btnShare, btnLeaderboard;
    private Animation zoomIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_screen);

        btnGameDifficalty = (ImageButton) findViewById(R.id.level_chooser_btn_difficalty);
        btnGameDifficalty.setOnClickListener(this);
//        btnGameDifficalty.setOnTouchListener(this);
        btnHelp = (ImageButton) findViewById(R.id.level_chooser_btn_help);
        btnHelp.setOnClickListener(this);
//        btnHelp.setOnTouchListener(this);
        btnMarkapp = (ImageButton) findViewById(R.id.level_chooser_btn_markapp);
        btnMarkapp.setOnClickListener(this);
//        btnMarkapp.setOnTouchListener(this);
        btnPlay = (ImageButton) findViewById(R.id.level_chooser_btn_play);
        btnPlay.setOnClickListener(this);
//        btnPlay.setOnTouchListener(this);
        btnShare = (ImageButton) findViewById(R.id.level_chooser_btn_share);
        btnShare.setOnClickListener(this);
//        btnShare.setOnTouchListener(this);
        btnLeaderboard = (ImageButton) findViewById(R.id.level_chooser_btn_leaderboard);
        btnLeaderboard.setOnClickListener(this);
//        btnLeaderboard.setOnTouchListener(this);

        zoomIn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom_in);
    }

    @Override
    public void onClick(View v) {
        Intent nextActivity = null;
        switch (v.getId()) {
            case R.id.level_chooser_btn_difficalty:
                switch (gameDifficalty) {
                    case 1:
                        btnGameDifficalty.setImageResource(R.drawable.game_difficalty_2);
                        gameDifficalty = 2;
                        break;
                    case 2:
                        btnGameDifficalty.setImageResource(R.drawable.game_difficalty_1);
                        gameDifficalty = 1;
                        break;
                }
                break;
            case R.id.level_chooser_btn_help:
                nextActivity = new Intent(this, ColorLibrary.class);
                startActivity(nextActivity);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                break;
            case R.id.level_chooser_btn_markapp:
                break;
            case R.id.level_chooser_btn_play:
                switch (gameDifficalty) {
                    case 1:
                        nextActivity = new Intent(this, LevelSurvival.class);
                        nextActivity.putExtra(getString(R.string.prefkey_game_difficalty), gameDifficalty);
                        break;
                    case 2:
                        nextActivity = new Intent(this, LevelMirrored.class);
                        nextActivity.putExtra(getString(R.string.prefkey_game_difficalty), gameDifficalty);
                        break;
                }
                startActivity(nextActivity);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                break;
            case R.id.level_chooser_btn_share:
                break;
            case R.id.level_chooser_btn_leaderboard:
                break;
        }
    }

    @Override
    public void onAnimationStart(Animation animation) {
    }

    @Override
    public void onAnimationEnd(Animation animation) {

    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }

    @Override
    protected void onPause() {
        super.onPause();
        zoomIn.cancel();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                zoomIn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom_in);
                zoomIn.setAnimationListener(LevelChooser.this);
                v.startAnimation(zoomIn);
                break;
        }
        return false;
    }
}
