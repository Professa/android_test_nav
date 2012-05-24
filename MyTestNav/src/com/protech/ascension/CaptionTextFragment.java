package com.protech.ascension;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.protech.ascension.chapter1.Chapter1FragmentActivity;

/**
 * Created with IntelliJ IDEA.
 * User: BNengel
 * Date: 5/10/12
 * Time: 5:18 PM
 * To change this template use File | Settings | File Templates.
 */
public class CaptionTextFragment extends ListFragment {
    private Chapter1FragmentActivity mainFragAct;
    private View rootView;
//    private ViewPager mViewPager;
    private TouchViewPager mViewPager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mainFragAct = (Chapter1FragmentActivity )inflater.getContext();

        rootView = inflater.inflate(android.R.layout.list_content, container, false);
        setNewCaptionList(R.array.ch1_pg1_caption_list);

        return rootView;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        mViewPager = (TouchViewPager) mainFragAct.findViewById(R.id.pager);
        if (mViewPager != null) {
            if ("Next Page".equals(l.getItemAtPosition(position))) {
                mViewPager.setCurrentItem(
                        mViewPager.getCurrentItem() + 1
                );
            }
        }
    }

    public void setNewCaptionList(int listId) {
        setListAdapter(new ArrayAdapter<String>(
                rootView.getContext(),
                R.layout.caption_text_layout,
                getResources().getStringArray(listId))
        );
    }
}