package com.jc.parallax.version1;

import android.content.Context;
import android.view.LayoutInflater;

/**
 * Created by asus on 2017/2/22.
 */

public class ParallaxLayoutInflater extends LayoutInflater {
    protected ParallaxLayoutInflater(LayoutInflater original, Context newContext) {
        super(original, newContext);
        if (!(getFactory() instanceof ParallaxFactory)) {
            setFactory(new ParallaxFactory(this, getFactory()));
        }
    }

    @Override
    public LayoutInflater cloneInContext(Context newContext) {
        return new ParallaxLayoutInflater(this, newContext);
    }
}
