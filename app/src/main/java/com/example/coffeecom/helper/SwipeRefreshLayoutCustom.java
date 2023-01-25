package com.example.coffeecom.helper;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

public class SwipeRefreshLayoutCustom extends SwipeRefreshLayout {
    public SwipeRefreshLayoutCustom(Context context, AttributeSet attributes) {
        super(context, attributes);
    }

//    @Override
    public boolean canChildScrollUp(ScrollView scrollView) {
//        return super.canChildScrollUp();
        return scrollView.getScrollY() != 0;
    }
}
