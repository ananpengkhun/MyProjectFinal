package com.example.ananpengkhun.myprojectfinal.customview;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by ananpengkhun on 12/19/16.
 */

public class NonSwipeViewPager extends ViewPager {

    private boolean isSwipeable;

    public NonSwipeViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        isSwipeable = false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return isSwipeable ? super.onTouchEvent(ev) : false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return isSwipeable ? super.onInterceptTouchEvent(ev) : false;
    }
}
