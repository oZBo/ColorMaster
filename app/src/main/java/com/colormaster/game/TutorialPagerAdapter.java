package com.colormaster.game;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.colormaster.game.activities.ColorNamesFragment;
import com.colormaster.game.activities.GameRulesFragment;

public class TutorialPagerAdapter extends FragmentPagerAdapter {

    public TutorialPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int pos) {
        switch(pos) {
            case 0: return GameRulesFragment.newInstance("SecondFragment, Instance 1");
            case 1: return ColorNamesFragment.newInstance("FirstFragment, Instance 1");
            default: return GameRulesFragment.newInstance("ThirdFragment, Default");
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
