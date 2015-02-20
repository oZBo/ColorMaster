package com.colormaster.game.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

import com.colormaster.game.R;
import com.colormaster.game.TutorialPagerAdapter;
import com.viewpagerindicator.CirclePageIndicator;

public class ColorLibrary extends FragmentActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);

//        //TODO fixxed colorLibrary
//        ArrayList<LibraryColorObject> colorsList = new ArrayList<>();
//        LibraryColorObject red = new LibraryColorObject(R.drawable.red, getString(R.string.color_red));
//        LibraryColorObject pink = new LibraryColorObject(R.drawable.pink, getString(R.string.color_pink));
//        LibraryColorObject purple = new LibraryColorObject(R.drawable.purple, getString(R.string.color_purple));
//        LibraryColorObject indigo = new LibraryColorObject(R.drawable.indigo, getString(R.string.color_indigo));
//        LibraryColorObject blue = new LibraryColorObject(R.drawable.blue, getString(R.string.color_blue));
//        LibraryColorObject teal = new LibraryColorObject(R.drawable.teal, getString(R.string.color_teal));
//        LibraryColorObject green = new LibraryColorObject(R.drawable.green, getString(R.string.color_green));
//        LibraryColorObject yellow = new LibraryColorObject(R.drawable.yellow, getString(R.string.color_yellow));
//        LibraryColorObject orange = new LibraryColorObject(R.drawable.orange, getString(R.string.color_orange));
//        LibraryColorObject brown = new LibraryColorObject(R.drawable.brown, getString(R.string.color_brown));
//
//        colorsList.add(red);
//        colorsList.add(pink);
//        colorsList.add(purple);
//        colorsList.add(indigo);
//        colorsList.add(blue);
//        colorsList.add(teal);
//        colorsList.add(green);
//        colorsList.add(yellow);
//        colorsList.add(orange);
//        colorsList.add(brown);
//
//        GridView gridView = (GridView)findViewById(R.id.gridview_color_library);
//        gridView.setOnItemClickListener(null); //remove itemClickEvent
//        GridViewAdapter adapter = new GridViewAdapter(this, colorsList);
//        gridView.setAdapter(adapter);

//        TitlePageIndicator titleIndicator = (TitlePageIndicator)findViewById(R.id.titles);
//        titleIndicator.setViewPager(pager);

        ViewPager pager = (ViewPager) findViewById(R.id.viewPager);
        pager.setAdapter(new TutorialPagerAdapter(getSupportFragmentManager()));

        CirclePageIndicator indicator = (CirclePageIndicator)findViewById(R.id.indicator);
        final float density = getResources().getDisplayMetrics().density;
        indicator.setRadius(10 * density);
        indicator.setPageColor(this.getResources().getColor(R.color.blue_200));
        indicator.setFillColor(0x880000FF);
        indicator.setStrokeColor(0xFF000000);
        indicator.setStrokeWidth(1 * density);
        indicator.setViewPager(pager);



    }

    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
}
