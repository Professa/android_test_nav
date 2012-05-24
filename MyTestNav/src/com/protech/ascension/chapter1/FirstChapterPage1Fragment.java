package com.protech.ascension.chapter1;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.protech.ascension.PageFragment;
import com.protech.ascension.R;
import com.protech.ascension.TouchImageView2;

/**
 * Created with IntelliJ IDEA.
 * User: BNengel
 * Date: 5/10/12
 * Time: 5:18 PM
 * To change this template use File | Settings | File Templates.
 */
public class FirstChapterPage1Fragment extends PageFragment {
    TouchImageView2 view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.ch1_page1, container, false);
//        ((TouchImageView)  rootView.findViewById(R.id.touch_view)).setupImage(R.drawable.cupcake);

        Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.image8);
//        ((TouchImageView2) rootView.findViewById(R.id.touch_view)).setImageBitmap(bm);
        view = ((TouchImageView2) rootView.findViewById(R.id.touch_view));
        view.setImageBitmap(bm);

        return rootView;
    }

    @Override
    protected int getTouchMode() {
        return view.getMode();
    }

    @Override
    protected int getCaptionListId() {
        return R.array.ch1_pg1_caption_list;
    }
}