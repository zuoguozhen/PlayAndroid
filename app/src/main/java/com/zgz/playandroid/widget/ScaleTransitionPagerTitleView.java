package com.zgz.playandroid.widget;

import android.content.Context;

import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView;

/**
 * @author ex-zuoguozhen001
 * @date 2019/2/19
 * @description
 */
public class ScaleTransitionPagerTitleView extends ColorTransitionPagerTitleView {

    private float minScale = 0.83f;

    public ScaleTransitionPagerTitleView(Context context) {
        super(context);
    }

    @Override
    public void onEnter(int index, int totalCount, float enterPercent, boolean leftToRight) {
        super.onEnter(index, totalCount, enterPercent, leftToRight);
        setScaleX(minScale + (1.0f - minScale) * enterPercent);
        setScaleY(minScale + (1.0f - minScale) * enterPercent);
    }

    @Override
    public void onLeave(int index, int totalCount, float leavePercent, boolean leftToRight) {
        super.onLeave(index, totalCount, leavePercent, leftToRight);
        setScaleX(1.0f + (minScale - 1.0f) * leavePercent);
        setScaleY(1.0f + (minScale - 1.0f) * leavePercent);
    }
}
