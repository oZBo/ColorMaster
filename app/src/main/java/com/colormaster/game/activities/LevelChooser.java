package com.colormaster.game.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.colormaster.game.R;
import com.colormaster.game.Toaster;
import com.github.fernandodev.easyratingdialog.library.EasyRatingDialog;

public class LevelChooser extends Activity implements View.OnClickListener {

    private int gameDifficalty = 1; //value from 1 to 3

    private ImageButton btnGameDifficalty, btnHelp, btnMarkapp, btnPlay;

    private EasyRatingDialog easyRatingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_screen);
        Toaster.init(this);
        easyRatingDialog = new EasyRatingDialog(this);
        btnGameDifficalty = (ImageButton) findViewById(R.id.level_chooser_btn_difficalty);
        btnGameDifficalty.setOnClickListener(this);
        btnHelp = (ImageButton) findViewById(R.id.level_chooser_btn_help);
        btnHelp.setOnClickListener(this);
        btnMarkapp = (ImageButton) findViewById(R.id.level_chooser_btn_markapp);
        btnMarkapp.setOnClickListener(this);
        btnPlay = (ImageButton) findViewById(R.id.level_chooser_btn_play);
        btnPlay.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        easyRatingDialog.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        easyRatingDialog.showIfNeeded();
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
                        Toaster.toast(getString(R.string.level_medium));
                        break;
                    case 2:
                        btnGameDifficalty.setImageResource(R.drawable.game_difficalty_1);
                        gameDifficalty = 1;
                        Toaster.toast(getString(R.string.level_easy));
                        break;
                    case 3:
                        Toaster.toast(getString(R.string.level_hard));
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
                        nextActivity = new Intent(this, LevelMedium.class);
                        nextActivity.putExtra(getString(R.string.prefkey_game_difficalty), gameDifficalty);
                        break;
                }
                startActivity(nextActivity);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                break;
        }
    }
}
