package com.colormaster.game.activities;

import android.os.Bundle;

import com.colormaster.game.R;

public class LevelMedium extends LevelParent {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.level_mirror);
        initViews();
        initAnimations();
        initTutorialView();
    }
}