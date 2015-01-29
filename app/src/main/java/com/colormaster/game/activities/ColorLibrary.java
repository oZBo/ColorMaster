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

        ArrayList<LibraryColorObject> colorsList = new ArrayList<>();
        LibraryColorObject color1 = new LibraryColorObject(this.getResources().getColor(R.color.red), "Red");
        LibraryColorObject color2 = new LibraryColorObject(this.getResources().getColor(R.color.orange), "Orange");
        LibraryColorObject color3 = new LibraryColorObject(this.getResources().getColor(R.color.yellow), "Yellow");
        LibraryColorObject color4 = new LibraryColorObject(this.getResources().getColor(R.color.green), "Green");
        LibraryColorObject color5 = new LibraryColorObject(this.getResources().getColor(R.color.blue), "Blue");
        LibraryColorObject color6 = new LibraryColorObject(this.getResources().getColor(R.color.indigo), "Indigo");
        LibraryColorObject color7 = new LibraryColorObject(this.getResources().getColor(R.color.violet), "Violet");
        colorsList.add(color1);
        colorsList.add(color2);
        colorsList.add(color3);
        colorsList.add(color4);
        colorsList.add(color5);
        colorsList.add(color6);
        colorsList.add(color7);

        GridView gridView = (GridView)findViewById(R.id.gridview_color_library);
        GridViewAdapter adapter = new GridViewAdapter(this, colorsList);
        gridView.setAdapter(adapter);

    }
}
