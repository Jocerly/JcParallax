package com.jc.parallax.version1;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.LinkedList;

import static android.view.ViewGroup.LayoutParams;
import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

/**
 * Created by asus on 2017/2/22.
 */

public class ParallaxPagerAdapter extends PagerAdapter {
    private LinkedList<View> linkedList = new LinkedList<>();
    private Context context;
    private int count;

    public ParallaxPagerAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = null;
        if (!linkedList.isEmpty()) {
            view = linkedList.pop();
        } else {
            view = new View(context);
            view.setLayoutParams(new LayoutParams(MATCH_PARENT, MATCH_PARENT));
        }
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View view = (View) object;
        container.removeView(view);
        linkedList.push(view);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    public void setCount(int i) {
        count = i;
    }
}
