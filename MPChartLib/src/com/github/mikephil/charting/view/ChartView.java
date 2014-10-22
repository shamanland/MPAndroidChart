package com.github.mikephil.charting.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewParent;

import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.utils.Utils;

public class ChartView extends View {
    private Chart<?> mChart;

    public Chart<?> getChart() {
        return mChart;
    }

    public void setChart(Chart<?> chart) {
        mChart = chart;
    }

    public ChartView(Context context) {
        super(context);
        init();
    }

    public ChartView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ChartView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    protected void init() {
        Utils.init(getContext());
    }

    /**
     * disables intercept touchevents
     */
    public void disableScroll() {
        ViewParent parent = getParent();
        parent.requestDisallowInterceptTouchEvent(true);
    }

    /**
     * enables intercept touchevents
     */
    public void enableScroll() {
        ViewParent parent = getParent();
        parent.requestDisallowInterceptTouchEvent(false);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        if (mChart != null) {
            mChart.onSizeChanged(w, h, oldw, oldh);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (mChart != null) {
            mChart.onDraw(canvas);
        }
    }
}
