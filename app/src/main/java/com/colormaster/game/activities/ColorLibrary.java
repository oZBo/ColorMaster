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

        GridView gridView = (GridView)findViewById(R.id.gridview_color_library);
        gridView.setOnItemClickListener(null); //remove itemClickEvent
        GridViewAdapter adapter = new GridViewAdapter(this, colorsList);
        gridView.setAdapter(adapter);

    }

    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
}
