package com.protech.ascension.chapter1;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.protech.ascension.PageFragment;
import com.protech.ascension.R;

/**
 * Created with IntelliJ IDEA.
 * User: BNengel
 * Date: 5/10/12
 * Time: 5:18 PM
 * To change this template use File | Settings | File Templates.
 */
public class FirstChapterPage2Fragment extends PageFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.ch1_page2, container, false);
        return rootView;
    }

    @Override
    protected int getTouchMode() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    protected int getCaptionListId() {
        return R.array.ch1_pg2_caption_list;
    }
}