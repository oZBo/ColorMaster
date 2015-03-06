package com.colormaster.game.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.colormaster.game.ColorsGridViewAdapter;
import com.colormaster.game.LibraryColorObject;
import com.colormaster.game.R;

import java.util.ArrayList;

public class ColorNamesFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.color_library, container, false);

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

        GridView gridView = (GridView)v.findViewById(R.id.gridview_color_library);
        ColorsGridViewAdapter adapter = new ColorsGridViewAdapter(getActivity(), colorsList);
        gridView.setAdapter(adapter);

        return v;
    }

    public static ColorNamesFragment newInstance(String text) {

        ColorNamesFragment f = new ColorNamesFragment();
        Bundle b = new Bundle();
        b.putString("msg", text);

        f.setArguments(b);

        return f;
    }
}