package com.braincollaboration.madcat.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.braincollaboration.madcat.R;
import com.braincollaboration.madcat.views.CustomFontTextView;

public class GameRulesFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.game_tutorial, container, false);
        CustomFontTextView textView = (CustomFontTextView) v.findViewById(R.id.tutorial_tv_tap_to_cont);
        textView.setVisibility(View.INVISIBLE);
        return v;
    }

    public static GameRulesFragment newInstance(String text) {

        GameRulesFragment f = new GameRulesFragment();
        Bundle b = new Bundle();
        b.putString("msg", text);

        f.setArguments(b);

        return f;
    }
}