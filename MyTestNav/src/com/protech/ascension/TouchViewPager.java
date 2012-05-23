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
    private CaptionTextFragment captionTextFragment;

    private int[] list = new int[] {
            R.array.ch1_pg1_caption_list,
            R.array.ch1_pg2_caption_list
    };

    private TouchViewPager(Context context) {
        super(context);
        setListener();
    }

    private TouchViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        setListener();
    }

    private void setListener() {
        setOnPageChangeListener(new OnPageChangeListener() {
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //To change body of implemented methods use File | Settings | File Templates.
            }

            public void onPageSelected(int position) {
                captionTextFragment.setNewCaptionList(
                        list[position]
                );
            }

            public void onPageScrollStateChanged(int state) {
                //To change body of implemented methods use File | Settings | File Templates.
            }
        });
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

    public void setCaptionTextFragment(CaptionTextFragment captionTextFragment) {
        this.captionTextFragment = captionTextFragment;
    }
}
