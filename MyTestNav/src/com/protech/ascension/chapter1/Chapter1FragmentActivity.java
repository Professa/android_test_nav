package com.protech.ascension.chapter1;

import android.animation.*;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.*;
import com.protech.ascension.CaptionTextFragment;
import com.protech.ascension.MyPageStateAdapter;
import com.protech.ascension.R;
import com.protech.ascension.TouchImageView2;

/**
 * Created with IntelliJ IDEA.
 * User: BNengel
 * Date: 5/10/12
 * Time: 10:56 AM
 * To change this template use File | Settings | File Templates.
 */
public class Chapter1FragmentActivity extends FragmentActivity {
    private Animator mCurrentTextAnimator;
    private MyPageStateAdapter myPageStateAdapter;

    private ViewPager mViewPager;
//    private TouchViewPager mViewPager;

    private String[] mToggleText = {"Show Text", "Hide Text"};
    private boolean mTextAreaHidden = false;
    private CaptionTextFragment captionTextFragment;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            mTextAreaHidden = savedInstanceState.getBoolean("textAreaHidden");
        }
        setContentView(R.layout.chapter_1_layout);

        captionTextFragment = (CaptionTextFragment) getSupportFragmentManager().
                        findFragmentById(R.id.caption_text_frag);

        // Create an adapter that when requested, will return a fragment representing an object in
        // the collection.
        //
        // ViewPager and its adapters use support library fragments, so we must use
        // getSupportFragmentManager.
        String[] pageList = getResources().getStringArray(R.array.ch1_page_list);
        myPageStateAdapter = new MyPageStateAdapter(getSupportFragmentManager(), 1,  pageList.length);

        // Set up action bar.
//        final ActionBar actionBar = getActionBar();

        // Specify that the Home button should show an "Up" caret, indicating that touching the
        // button will take the user one step up in the application's hierarchy.
//        actionBar.setDisplayHomeAsUpEnabled(true);

        // Set up the ViewPager, attaching the adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(myPageStateAdapter);

        if (mTextAreaHidden) {
//            getSupportFragmentManager().beginTransaction().hide(
//                    getSupportFragmentManager().findFragmentById(R.id.caption_text_frag));
        } else {
//            captionTextFragment = new CaptionTextFragment();
//
//            getSupportFragmentManager().beginTransaction().add(
//                    android.R.id.content, captionTextFragment
//            ).commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.menu_toggleTextCloud).setTitle(mToggleText[mTextAreaHidden ? 0 : 1]);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_toggleTextCloud:
                toggleVisibleTextArea();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void toggleVisibleTextArea() {
        // Use these for custom animations.
        final android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
        final Fragment f = fm.findFragmentById(R.id.caption_text_frag);
//        final Fragment f = captionTextFragment;
        final View textAreaView = f.getView();

        // Determine if we're in portrait, and whether we're showing or hiding the titles
        // with this toggle.
        final boolean isPortrait = getResources().getConfiguration().orientation ==
                Configuration.ORIENTATION_PORTRAIT;

        final boolean shouldShow = f.isHidden() || mCurrentTextAnimator != null;

        // Cancel the current titles animation if there is one.
        if (mCurrentTextAnimator != null)
            mCurrentTextAnimator.cancel();

        // Begin setting up the object animator. We'll animate the bottom or right edge of the
        // titles view, as well as its alpha for a fade effect.
        // todo - Original Approach
        ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(
                textAreaView,
                PropertyValuesHolder.ofFloat("alpha", shouldShow ? 1 : 0)
        );
        if (shouldShow) {
            fm.beginTransaction().show(f).commit();
            objectAnimator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animator) {
                    mCurrentTextAnimator = null;
                    mTextAreaHidden = false;
                    invalidateOptionsMenu();
                }
            });

        } else {
            objectAnimator.addListener(new AnimatorListenerAdapter() {
                boolean canceled;

                @Override
                public void onAnimationCancel(Animator animation) {
                    canceled = true;
                    super.onAnimationCancel(animation);
                }

                @Override
                public void onAnimationEnd(Animator animator) {
                    if (canceled)
                        return;
                    mCurrentTextAnimator = null;
                    fm.beginTransaction().hide(f).commit();
                    mTextAreaHidden = true;
                    invalidateOptionsMenu();
                }
            });
        }

        // Start the animation.
        objectAnimator.start();
        mCurrentTextAnimator = objectAnimator;

        // Manually trigger onNewIntent to check for ACTION_DIALOG.
        onNewIntent(getIntent());
    }
}