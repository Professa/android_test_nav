package com.protech.ascension;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.protech.ascension.chapter1.Chapter1FragmentActivity;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: BNengel
 * Date: 5/10/12
 * Time: 5:18 PM
 * To change this template use File | Settings | File Templates.
 */
public class CaptionTextFragment extends ListFragment {
    private View rootView;
//    private ViewPager mViewPager;
    private TouchViewPager mViewPager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(android.R.layout.list_content, container, false);
        setNewCaptionList(R.array.ch1_pg1_caption_list);

        return rootView;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        setRowColors(l, v, position);

        mViewPager = (TouchViewPager) getActivity().findViewById(R.id.pager);
        if (mViewPager != null) {
            if ("Next Page".equals(l.getItemAtPosition(position))) {
                mViewPager.setCurrentItem(
                        mViewPager.getCurrentItem() + 1
                );
            }
        }
    }

    private void setRowColors(ListView l, View v, int position) {
        // Set up the colors for all the Items in this list view.
        for (int i = 0; i < l.getChildCount(); i++) {
            if (i != position) {
                View view = l.getChildAt(i);
                if (view instanceof TextView) {
                    view.setBackgroundResource(android.R.color.white);
                }
            }
        }
        v.setBackgroundResource(R.color.pressed);
    }

    public void setNewCaptionList(int listId) {
        setListAdapter(new ArrayAdapter<String>(
                getActivity(),
                R.layout.caption_text_layout,
                getResources().getStringArray(listId))
        );
    }
}