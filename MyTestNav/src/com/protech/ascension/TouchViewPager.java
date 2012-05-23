package com.protech.ascension;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created with IntelliJ IDEA.
 * User: BNengel
 * Date: 5/23/12
 * Time: 1:30 PM
 * To change this template use File | Settings | File Templates.
 */
public class TouchViewPager extends ViewPager {
    private MyPageStateAdapter myPageStateAdapter;

    private TouchViewPager(Context context) {
        super(context);
    }

    private TouchViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (TouchImageView2.NONE !=  myPageStateAdapter.getTouchMode()) {
            return super.onTouchEvent(ev);
        }
        return false;
    }

    public void setAdapter(MyPageStateAdapter adapter) {
        myPageStateAdapter = adapter;
        super.setAdapter(adapter);
    }
}
