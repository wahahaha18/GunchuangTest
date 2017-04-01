package com.example.horizontalscrollviewtest;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.HorizontalScrollView;

/**
 * Created by yq895943339 on 2017/4/1.
 */

public class HorizontalSChanged extends HorizontalScrollView {
    public static final String TAG = HorizontalSChanged.class.getName();
    public HorizontalSChanged(Context p_context, AttributeSet p_attrs)
    {
        super(p_context, p_attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        getParent().requestDisallowInterceptTouchEvent(true);
        int action = ev.getAction();

        switch (action)
        {
            case MotionEvent.ACTION_DOWN:
                Log.e(TAG, "dispatchTouchEvent ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e(TAG, "dispatchTouchEvent ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.e(TAG, "dispatchTouchEvent ACTION_UP");
                break;

            default:
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent p_event)
    {
        return true;
    }

//    @Override
//    public boolean onTouchEvent(MotionEvent p_event)
//    {
//        if (p_event.getAction() == MotionEvent.ACTION_MOVE && getParent() != null)
//        {
//            Log.d("DEBUG", "intercept move event");
//            getParent().requestDisallowInterceptTouchEvent(true);
//        }
//
//        return super.onTouchEvent(p_event);
//    }
}
