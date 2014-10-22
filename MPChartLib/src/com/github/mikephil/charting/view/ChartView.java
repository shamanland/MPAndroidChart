package com.github.mikephil.charting.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewParent;

import com.github.mikephil.charting.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.CandleStickChart;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.charts.ScatterChart;
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
        init(context, null, 0);
    }

    public ChartView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public ChartView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    protected void init(Context context, AttributeSet attrs, int defStyleAttr) {
        // FIXME
        Utils.init(context);

        if (attrs != null) {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ChartView);
            if (a != null) {
                try {
                    Chart chart = null;

                    switch (a.getInteger(R.styleable.ChartView_chartType, -1)) {
                        case 0:
                            chart = new LineChart();
                            break;
                        case 1:
                            chart = new BarChart();
                            break;
                        case 2:
                            chart = new ScatterChart();
                            break;
                        case 3:
                            chart = new CandleStickChart();
                            break;
                        case 4:
                            chart = new PieChart();
                            break;
                        case 5:
                            chart = new RadarChart();
                            break;
                    }

                    setChart(chart);
                } finally {
                    a.recycle();
                }
            }
        }
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
