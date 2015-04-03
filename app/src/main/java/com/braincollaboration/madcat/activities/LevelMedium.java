package com.braincollaboration.madcat.activities;

import android.os.Bundle;

import com.braincollaboration.madcat.R;

public class LevelMedium extends LevelParent {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.level_medium);
        initViews();
        initAnimations();
        initTutorialView();
    }
}