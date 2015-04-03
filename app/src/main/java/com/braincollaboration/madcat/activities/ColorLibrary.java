package com.braincollaboration.madcat.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

import com.braincollaboration.madcat.R;
import com.braincollaboration.madcat.TutorialPagerAdapter;
import com.viewpagerindicator.CirclePageIndicator;

public class ColorLibrary extends FragmentActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);

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
