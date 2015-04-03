package com.braincollaboration.madcat.activities;

import android.os.Bundle;

import com.braincollaboration.madcat.R;

public class LevelEasy extends LevelParent {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.level_easy);
        initViews();
        initAnimations();
        initTutorialView();
    }
}