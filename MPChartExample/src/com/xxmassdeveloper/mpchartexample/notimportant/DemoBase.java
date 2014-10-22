
package com.xxmassdeveloper.mpchartexample.notimportant;

import android.support.v4.app.FragmentActivity;
import android.view.MenuItem;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarLineChartBase;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.filter.Approximator;
import com.github.mikephil.charting.utils.XLabels;
import com.github.mikephil.charting.view.ChartView;
import com.xxmassdeveloper.mpchartexample.R;

import java.util.ArrayList;

/**
 * Baseclass of all Activities of the Demo Application.
 *
 * @author Philipp Jahoda
 */
public abstract class DemoBase<C extends Chart<?>> extends FragmentActivity {
    protected ChartView mChartView;
    protected C mChart;

    protected String[] mMonths = new String[]{
            "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Okt", "Nov", "Dec"
    };

    protected String[] mParties = new String[]{
            "Party A", "Party B", "Party C", "Party D", "Party E", "Party F", "Party G", "Party H",
            "Party I", "Party J", "Party K", "Party L", "Party M", "Party N", "Party O", "Party P",
            "Party Q", "Party R", "Party S", "Party T", "Party U", "Party V", "Party W", "Party X",
            "Party Y", "Party Z"
    };

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.move_left_in_activity, R.anim.move_right_out_activity);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.actionToggleValues: {
                if (mChart.isDrawYValuesEnabled()) {
                    mChart.setDrawYValues(false);
                } else {
                    mChart.setDrawYValues(true);
                }

                mChartView.invalidate();
                break;
            }

            case R.id.actionToggle3D: {
                if (mChart.is3DEnabled()) {
                    mChart.set3DEnabled(false);
                } else {
                    mChart.set3DEnabled(true);
                }

                mChartView.invalidate();
                break;
            }

            case R.id.actionToggleHighlight: {
                if (mChart.isHighlightEnabled()) {
                    mChart.setHighlightEnabled(false);
                } else {
                    mChart.setHighlightEnabled(true);
                }

                mChartView.invalidate();
                break;
            }

            case R.id.actionTogglePinch: {
                if (mChart.isPinchZoomEnabled()) {
                    mChart.setPinchZoom(false);
                } else {
                    mChart.setPinchZoom(true);
                }

                mChartView.invalidate();
                break;
            }

            case R.id.actionToggleHighlightArrow: {
                if (mChart.isDrawHighlightArrowEnabled()) {
                    mChart.setDrawHighlightArrow(false);
                } else {
                    mChart.setDrawHighlightArrow(true);
                }

                mChartView.invalidate();
                break;
            }

            case R.id.actionToggleStartzero: {
                if (mChart.isStartAtZeroEnabled()) {
                    mChart.setStartAtZero(false);
                } else {
                    mChart.setStartAtZero(true);
                }

                mChartView.invalidate();
                break;
            }

            case R.id.actionToggleAdjustXLegend: {
                if (mChart instanceof BarLineChartBase) {
                    XLabels xLabels = ((BarLineChartBase) mChart).getXLabels();

                    if (xLabels.isAdjustXLabelsEnabled()) {
                        xLabels.setAdjustXLabels(false);
                    } else {
                        xLabels.setAdjustXLabels(true);
                    }
                }

                mChartView.invalidate();
                break;
            }

            case R.id.animateX: {
                mChart.animateX(3000);
                break;
            }

            case R.id.animateY: {
                mChart.animateY(3000);
                break;
            }

            case R.id.animateXY: {
                mChart.animateXY(3000, 3000);
                break;
            }

            case R.id.actionToggleFilter: {
                Approximator a = new Approximator(Approximator.ApproximatorType.DOUGLAS_PEUCKER, 25);

                if (!mChart.isFilteringEnabled()) {
                    mChart.enableFiltering(a);
                } else {
                    mChart.disableFiltering();
                }

                mChartView.invalidate();
                break;
            }

            case R.id.actionSave: {
                if (mChart.saveToGallery("title" + System.currentTimeMillis(), 50)) {
                    Toast.makeText(getApplicationContext(), "Saving SUCCESSFUL!",
                            Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(getApplicationContext(), "Saving FAILED!", Toast.LENGTH_SHORT)
                            .show();
                break;
            }

            case R.id.actionToggleFilled: {
                ArrayList<LineDataSet> sets = (ArrayList<LineDataSet>) mChart.getDataCurrent().getDataSets();

                for (LineDataSet set : sets) {
                    if (set.isDrawFilledEnabled())
                        set.setDrawFilled(false);
                    else
                        set.setDrawFilled(true);
                }

                mChartView.invalidate();
                break;
            }
            case R.id.actionToggleCircles: {
                ArrayList<LineDataSet> sets = (ArrayList) mChart.getDataCurrent().getDataSets();

                for (LineDataSet set : sets) {
                    if (set.isDrawCirclesEnabled())
                        set.setDrawCircles(false);
                    else
                        set.setDrawCircles(true);
                }
                mChartView.invalidate();
                break;
            }
            case R.id.actionToggleCubic: {
                ArrayList<LineDataSet> sets = (ArrayList) mChart.getDataCurrent().getDataSets();

                for (LineDataSet set : sets) {
                    if (set.isDrawCubicEnabled())
                        set.setDrawCubic(false);
                    else
                        set.setDrawCubic(true);
                }
                mChartView.invalidate();
                break;
            }
        }

        return true;
    }
}
