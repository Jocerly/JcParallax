package com.jc.parallax.version1;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import com.jc.parallax.R;

/**
 * Created by asus on 2017/2/22.
 */

public class ParallaxFactory implements LayoutInflater.Factory {
    private final LayoutInflater.Factory factory;
    private ParallaxLayoutInflater mInflater;
    private static final String[] sClassPrefixList = {
            "android.widget.",
            "android.webkit.",
            "android.view."
    };

    public ParallaxFactory(ParallaxLayoutInflater parallaxLayoutInflater, LayoutInflater.Factory factory) {
        mInflater = parallaxLayoutInflater;
        this.factory = factory;
    }

    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        View view = null;
        if (context instanceof LayoutInflater.Factory) {
            view = ((LayoutInflater.Factory)context).onCreateView(name, context, attrs);
        }

        if (factory != null && view == null) {
            view = factory.onCreateView(name, context, attrs);
        }

        if (view == null) {
            view = onCreateViewOrFailQuietly(name, context, attrs);
        }

        if (view != null) {
            onViewCreate(view, context, attrs);
        }

        return view;
    }

    private View onCreateViewOrFailQuietly(String name, Context context, AttributeSet attrs) {
        if (name.contains(".")) {
            return createViewOrFailQuietly(name, null, context, attrs);
        }
        for (String prefix : sClassPrefixList) {
            View view = createViewOrFailQuietly(name, prefix, context, attrs);
            if (view != null) {
                return view;
            }
        }
        return null;
    }

    private View createViewOrFailQuietly(String name, String prefix, Context context, AttributeSet attrs) {
        try {
            return mInflater.createView(name, prefix, attrs);
        } catch (ClassNotFoundException e) {
            return null;
        }
    }

    private void onViewCreate(View view, Context context, AttributeSet attrs) {
        int[] attrIds = {R.attr.a_in, R.attr.a_out, R.attr.x_in, R.attr.x_out, R.attr.y_in, R.attr.y_out};
        TypedArray typedArray = context.obtainStyledAttributes(attrs, attrIds);

        if (typedArray != null) {
            if (typedArray.length() > 0) {
                ParallaxViewTag tag = new ParallaxViewTag();
                tag.alphaIn = typedArray.getFloat(0, 0f);
                tag.alphaOut = typedArray.getFloat(1, 0f);
                tag.xIn = typedArray.getFloat(2, 0f);
                tag.xOut = typedArray.getFloat(3, 0f);
                tag.yIn = typedArray.getFloat(4, 0f);
                tag.yOut = typedArray.getFloat(5, 0f);
                view.setTag(R.id.parallax_view_tag, tag);
            }
        }
        typedArray.recycle();
    }
}
