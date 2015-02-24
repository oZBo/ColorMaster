package com.colormaster.game.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.colormaster.game.GameHelper;
import com.colormaster.game.R;
import com.colormaster.game.Toaster;
import com.github.fernandodev.easyratingdialog.library.EasyRatingDialog;
import com.google.android.gms.games.Games;

public class LevelChooser extends GooglePlayAuthorization implements View.OnClickListener {

    private int gameDifficalty = 1; //value from 1 to 3
    private boolean isSoundsOn = true;

    private ImageButton btnGameDifficalty, btnHelp, btnMarkapp, btnPlay, btnLedaerboard, btnSounds;
    private Toaster toaster;

    private EasyRatingDialog easyRatingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_screen);
        toaster = Toaster.init(this);
        easyRatingDialog = new EasyRatingDialog(this);
        btnGameDifficalty = (ImageButton) findViewById(R.id.level_chooser_btn_difficalty);
        btnGameDifficalty.setOnClickListener(this);
        btnHelp = (ImageButton) findViewById(R.id.level_chooser_btn_help);
        btnHelp.setOnClickListener(this);
        btnMarkapp = (ImageButton) findViewById(R.id.level_chooser_btn_markapp);
        btnMarkapp.setOnClickListener(this);
        btnPlay = (ImageButton) findViewById(R.id.level_chooser_btn_play);
        btnPlay.setOnClickListener(this);
        btnLedaerboard = (ImageButton) findViewById(R.id.level_chooser_btn_leaderboard);
        btnLedaerboard.setOnClickListener(this);
        btnSounds = (ImageButton) findViewById(R.id.level_chooser_btn_sounds);
        btnSounds.setOnClickListener(this);
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
                        toaster.toast(getString(R.string.level_medium));
                        break;
                    case 2:
                        btnGameDifficalty.setImageResource(R.drawable.game_difficalty_1);
                        gameDifficalty = 1;
                        toaster.toast(getString(R.string.level_easy));
                        break;
                    case 3:
                        toaster.toast(getString(R.string.level_hard));
                        break;
                }
                break;
            case R.id.level_chooser_btn_help:
                nextActivity = new Intent(this, ColorLibrary.class);
                startActivity(nextActivity);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                break;
            case R.id.level_chooser_btn_markapp:
                //TODO uncomment "go to the market" functionality
//                final String appPackageName = getPackageName(); // getPackageName() from Context or Activity object
//                try {
//                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
//                } catch (android.content.ActivityNotFoundException anfe) {
//                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
//                }
                break;
            case R.id.level_chooser_btn_play:
                switch (gameDifficalty) {
                    case 1:
                        nextActivity = new Intent(this, LevelEasy.class);
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
            case R.id.level_chooser_btn_leaderboard:
                if (GameHelper.haveNetworkConnection(this)) {
                    try {
                        startActivityForResult(Games.Leaderboards.getAllLeaderboardsIntent(mGoogleApiClient),
                                RC_UNUSED);
                    } catch (Exception ex) {
                        mGoogleApiClient.connect();
                    }
                } else {
                    toaster.toast(getString(R.string.check_internet));
                }
                break;
            case R.id.level_chooser_btn_sounds:
                if (isSoundsOn) {
                    isSoundsOn = false;
                    btnSounds.setImageResource(R.drawable.selector_sound_off);
                } else {
                    isSoundsOn = true;
                    btnSounds.setImageResource(R.drawable.selector_sound_on);
                }
        break;
    }

}

    @Override
    protected void onStart() {
        super.onStart();
        easyRatingDialog.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        easyRatingDialog.showIfNeeded();
    }
}
