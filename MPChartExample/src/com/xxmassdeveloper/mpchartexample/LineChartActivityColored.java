
package com.xxmassdeveloper.mpchartexample;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.WindowManager;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.Legend;
import com.github.mikephil.charting.utils.Legend.LegendForm;
import com.github.mikephil.charting.utils.XLabels;
import com.github.mikephil.charting.utils.YLabels;
import com.github.mikephil.charting.view.ChartView;
import com.xxmassdeveloper.mpchartexample.notimportant.DemoBase;

import java.util.ArrayList;

public class LineChartActivityColored extends DemoBase {

    private ChartView[] mChartViews = new ChartView[4];
    private LineChart[] mCharts = new LineChart[4];
    private Typeface mTf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_colored_lines);

        mChartViews[0] = (ChartView) findViewById(R.id.chart1);
        mChartViews[1] = (ChartView) findViewById(R.id.chart2);
        mChartViews[2] = (ChartView) findViewById(R.id.chart3);
        mChartViews[3] = (ChartView) findViewById(R.id.chart4);
        mCharts[0] = (LineChart) mChartViews[0].getChart();
        mCharts[1] = (LineChart) mChartViews[1].getChart();
        mCharts[2] = (LineChart) mChartViews[2].getChart();
        mCharts[3] = (LineChart) mChartViews[3].getChart();

        mTf = Typeface.createFromAsset(getAssets(), "OpenSans-Bold.ttf");

        LineData data = getData(36, 100);

        for (int i = 0; i < mCharts.length; i++)
            // add some transparency to the color with "& 0x90FFFFFF"
            setupChart(mChartViews[i], mCharts[i], data, mColors[i % mColors.length]);
    }

    private int[] mColors = new int[] {
            Color.rgb(137, 230, 81), 
            Color.rgb(240, 240, 30), 
            Color.rgb(89, 199, 250),
            Color.rgb(250, 104, 104)
    };

    private void setupChart(ChartView chartView, LineChart chart, LineData data, int color) {

        // if enabled, the chart will always start at zero on the y-axis
        chart.setStartAtZero(true);

        // disable the drawing of values into the chart
        chart.setDrawYValues(false);

        chart.setDrawBorder(false);

        // no description text
        chart.setDescription("");
        chart.setNoDataTextDescription("You need to provide data for the chart.");

        // enable / disable grid lines
        chart.setDrawVerticalGrid(false);
        // mChart.setDrawHorizontalGrid(false);
        //
        // enable / disable grid background
        chart.setDrawGridBackground(false);
        chart.setGridColor(Color.WHITE & 0x70FFFFFF);
        chart.setGridWidth(1.25f);

        // enable touch gestures
        chart.setTouchEnabled(true);

        // enable scaling and dragging
        chart.setDragEnabled(true);
        chart.setScaleEnabled(true);

        // if disabled, scaling can be done on x- and y-axis separately
        chart.setPinchZoom(false);

        chartView.setBackgroundColor(color);

        chart.setValueTypeface(mTf);

        // add data
        chart.setData(data);

        // get the legend (only possible after setting data)
        Legend l = chart.getLegend();

        // modify the legend ...
        // l.setPosition(LegendPosition.LEFT_OF_CHART);
        l.setForm(LegendForm.CIRCLE);
        l.setFormSize(6f);
        l.setTextColor(Color.WHITE);
        l.setTypeface(mTf);

        YLabels y = chart.getYLabels();
        y.setTextColor(Color.WHITE);
        y.setTypeface(mTf);
        y.setLabelCount(4);

        XLabels x = chart.getXLabels();
        x.setTextColor(Color.WHITE);
        x.setTypeface(mTf);

        // animate calls invalidate()...
        chart.animateX(2500);
    }
    
    private LineData getData(int count, float range) {

        ArrayList<String> xVals = new ArrayList<String>();
        for (int i = 0; i < count; i++) {
            xVals.add(mMonths[i % 12]);
        }

        ArrayList<Entry> yVals = new ArrayList<Entry>();

        for (int i = 0; i < count; i++) {
            float val = (float) (Math.random() * range) + 3;
            yVals.add(new Entry(val, i));
        }

        // create a dataset and give it a type
        LineDataSet set1 = new LineDataSet(yVals, "DataSet 1");
        // set1.setFillAlpha(110);
        // set1.setFillColor(Color.RED);

        set1.setLineWidth(1.75f);
        set1.setCircleSize(3f);
        set1.setColor(Color.WHITE);
        set1.setCircleColor(Color.WHITE);
        set1.setHighLightColor(Color.WHITE);

        ArrayList<LineDataSet> dataSets = new ArrayList<LineDataSet>();
        dataSets.add(set1); // add the datasets

        // create a data object with the datasets
        LineData data = new LineData(xVals, dataSets);

        return data;
    }
}
