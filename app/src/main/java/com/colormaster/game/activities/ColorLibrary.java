package com.colormaster.game.activities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.GridView;

import com.colormaster.game.GridViewAdapter;
import com.colormaster.game.LibraryColorObject;
import com.colormaster.game.R;

import java.util.ArrayList;

public class ColorLibrary extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.color_library);

        //TODO fixxed colorLibrary
        ArrayList<LibraryColorObject> colorsList = new ArrayList<>();
        LibraryColorObject red = new LibraryColorObject(R.drawable.red, getString(R.string.color_red));
        LibraryColorObject pink = new LibraryColorObject(R.drawable.pink, getString(R.string.color_pink));
        LibraryColorObject purple = new LibraryColorObject(R.drawable.purple, getString(R.string.color_purple));
        LibraryColorObject deepPurple = new LibraryColorObject(R.drawable.deep_purple, getString(R.string.color_deep_purple));
        LibraryColorObject indigo = new LibraryColorObject(R.drawable.indigo, getString(R.string.color_indigo));
        LibraryColorObject blue = new LibraryColorObject(R.drawable.blue, getString(R.string.color_blue));
        LibraryColorObject cyan = new LibraryColorObject(R.drawable.cyan, getString(R.string.color_cyan));
        LibraryColorObject teal = new LibraryColorObject(R.drawable.teal, getString(R.string.color_teal));
        LibraryColorObject green = new LibraryColorObject(R.drawable.green, getString(R.string.color_green));
        LibraryColorObject lightGreen = new LibraryColorObject(R.drawable.light_green, getString(R.string.color_light_green));
        LibraryColorObject lime = new LibraryColorObject(R.drawable.lime, getString(R.string.color_lime));
        LibraryColorObject yellow = new LibraryColorObject(R.drawable.yellow, getString(R.string.color_yellow));
        LibraryColorObject amber = new LibraryColorObject(R.drawable.amber, getString(R.string.color_amber));
        LibraryColorObject orange = new LibraryColorObject(R.drawable.orange, getString(R.string.color_orange));
        LibraryColorObject deepOrange = new LibraryColorObject(R.drawable.deep_orange, getString(R.string.color_deep_orange));
        LibraryColorObject brown = new LibraryColorObject(R.drawable.brown, getString(R.string.color_brown));
        LibraryColorObject grey = new LibraryColorObject(R.drawable.grey, getString(R.string.color_grey));

        colorsList.add(red);
        colorsList.add(pink);
        colorsList.add(purple);
        colorsList.add(deepPurple);
        colorsList.add(indigo);
        colorsList.add(blue);
        colorsList.add(cyan);
        colorsList.add(teal);
        colorsList.add(green);
        colorsList.add(lightGreen);
        colorsList.add(lime);
        colorsList.add(yellow);
        colorsList.add(amber);
        colorsList.add(orange);
        colorsList.add(deepOrange);
        colorsList.add(brown);
        colorsList.add(grey);

        GridView gridView = (GridView)findViewById(R.id.gridview_color_library);
        gridView.setOnItemClickListener(null);
        GridViewAdapter adapter = new GridViewAdapter(this, colorsList);
        gridView.setAdapter(adapter);

    }

    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
}
