package com.colormaster.game.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.colormaster.game.R;

public class LevelChooser extends Activity implements View.OnClickListener {

    int gameDifficalty = 1; //value from 1 to 2

    private ImageButton btnGameDifficalty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_screen);

        btnGameDifficalty = (ImageButton) findViewById(R.id.level_chooser_btn_difficalty);
        btnGameDifficalty.setOnClickListener(this);
        findViewById(R.id.level_chooser_btn_help).setOnClickListener(this);
        findViewById(R.id.level_chooser_btn_markapp).setOnClickListener(this);
        findViewById(R.id.level_chooser_btn_play).setOnClickListener(this);
        findViewById(R.id.level_chooser_btn_share).setOnClickListener(this);
        findViewById(R.id.level_chooser_btn_leaderboard).setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.level_chooser_btn_difficalty:
                switch (gameDifficalty){
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
                break;
            case R.id.level_chooser_btn_markapp:
                break;
            case R.id.level_chooser_btn_play:
                Intent game = null;
                switch (gameDifficalty){
                    case 1:
                        game = new Intent(this, LevelSurvival.class);
                        break;
                    case 2:
                        game = new Intent(this, LevelMirrored.class);
                        break;
                }
                startActivity(game);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                break;
            case R.id.level_chooser_btn_share:
                break;
            case R.id.level_chooser_btn_leaderboard:
                break;
        }
    }
}
