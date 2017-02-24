package com.jc.parallax.version2.adapter;

import java.util.ArrayList;

import com.jc.parallax.version2.LoginAnimTextBaseFragment;
import com.jc.parallax.version2.bean.TextBean;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;


/**
 * 下层文字动画有4页
 */
public class TextFragmentStatePagerAdapter extends FragmentStatePagerAdapter {

    ArrayList<TextBean> mTextBeans;

    public TextFragmentStatePagerAdapter(FragmentManager fm, ArrayList<TextBean> tbs) {
        super(fm);
        if(tbs != null){
            mTextBeans = tbs;
        }
        else{
            mTextBeans = new ArrayList<TextBean>();
        }
    }

    @Override
    public Fragment getItem(int position) {
        return new LoginAnimTextBaseFragment(mTextBeans.get(position));
    }

    @Override
    public int getCount() {
        return mTextBeans.size();
    }
}
