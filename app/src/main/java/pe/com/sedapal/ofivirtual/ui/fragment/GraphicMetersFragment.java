package pe.com.sedapal.ofivirtual.ui.fragment;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.RectF;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.MPPointF;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import pe.com.sedapal.ofivirtual.R;
import pe.com.sedapal.ofivirtual.model.HistoricConsumModel;
import pe.com.sedapal.ofivirtual.model.ListHistoricConsumModel;
import pe.com.sedapal.ofivirtual.model.PayInvoicesModel;
import pe.com.sedapal.ofivirtual.presenter.view.PayInvoicesView;
import pe.com.sedapal.ofivirtual.ui.component.graphics.MyMarkerView;
import pe.com.sedapal.ofivirtual.util.CommonUtil;

public class GraphicMetersFragment extends BaseFragment implements PayInvoicesView, OnChartValueSelectedListener {
    @BindView(R.id.chart1)
    BarChart chart;

    private static final String TAG = GraphicMetersFragment.class.getSimpleName();

    private static final String BUNDLE_DATA_LIST_HISTORIC_CONSUM = "BUNDLE_DATA_LIST_HISTORIC_CONSUM";
    List<HistoricConsumModel> listHistoricConsumModelMeters;

    public GraphicMetersFragment() {
        // Required empty public constructor
    }

    public static GraphicMetersFragment newInstance(ListHistoricConsumModel listHistoricConsum) {
        GraphicMetersFragment fragment = new GraphicMetersFragment();
        Bundle args = new Bundle();
        args.putSerializable(BUNDLE_DATA_LIST_HISTORIC_CONSUM, listHistoricConsum);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View loView = inflater.inflate(R.layout.fragment_graphic_meters_invoices, container, false);
        ButterKnife.bind(this, loView);
        return loView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle poSavedInstanceState) {
        super.onViewCreated(view, poSavedInstanceState);
        //this.opPayInvoicesPresenter.setView(this);
        getBundles();
    }

    public void getBundles(){
        ListHistoricConsumModel listHistoricConsumModelObtainMeters = (ListHistoricConsumModel) getArguments().getSerializable(BUNDLE_DATA_LIST_HISTORIC_CONSUM);
        listHistoricConsumModelMeters = listHistoricConsumModelObtainMeters.getListHistoricConsumModel();
        eventControls();
    }

    public void eventControls() {
        chart.getDescription().setEnabled(false);
        chart.setMaxVisibleValueCount(60);
        chart.setPinchZoom(false);

        chart.setOnChartValueSelectedListener(this);
        chart.setDrawBarShadow(false);
        chart.setDrawValueAboveBar(true);
        chart.setDrawGridBackground(false);

        chart.getAxisLeft().setDrawGridLines(true);
        chart.setScaleEnabled(false);
        chart.animateY(1500);

        chart.getLegend().setEnabled(false);

        chart.getAxisRight().setEnabled(false);

        //chart.getXAxis().setDrawAxisLine(false);
        chart.getAxisLeft().setDrawAxisLine(false);


        ArrayList<BarEntry> values = getValuesGraphicMeters();
        ArrayList<String> xLabel = getLabelsXAxisGraphicMeters();

        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return xLabel.get((int)value);
            }
        });


        BarDataSet set1;

        if (chart.getData() != null &&
                chart.getData().getDataSetCount() > 0) {
            set1 = (BarDataSet) chart.getData().getDataSetByIndex(0);
            set1.setValues(values);
            chart.getData().notifyDataChanged();
            chart.notifyDataSetChanged();
        } else {
            set1 = new BarDataSet(values, "Data Set");
            set1.setColors(getResources().getColor(R.color.color_bars_unselected));
            set1.setHighLightColor(getResources().getColor(R.color.color_bar_selected));
            set1.setHighLightAlpha(255);
            set1.setDrawValues(false);

            //Seleccionando un valor por defecto
            Highlight h = new Highlight(values.size()-1, 0,0); // dataset index for piechart is always 0
            chart.highlightValues(new Highlight[] { h });
            chart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
                @Override
                public void onValueSelected(Entry e, Highlight h) {

                }

                @Override
                public void onNothingSelected() {

                }
            });
            //end

            ArrayList<IBarDataSet> dataSets = new ArrayList<>();
            dataSets.add(set1);

            BarData data = new BarData(dataSets);
            chart.setData(data);
            chart.setFitBars(true);
        }

        MyMarkerView mv = new MyMarkerView(getContext(), R.layout.custom_marker_view, false);
        mv.setChartView(chart); // For bounds control
        chart.setMarker(mv); // Set the marker to the chart
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            chart.setVisibleXRangeMaximum(8); // allow 20 values to be displayed at once on the x-axis, not more
        } else chart.setVisibleXRangeMaximum(6);
        chart.moveViewToX(10);

        chart.invalidate();
    }


    public ArrayList<BarEntry> getValuesGraphicMeters(){
        ArrayList<BarEntry> values = new ArrayList<>();
        int i = 0;
        for(HistoricConsumModel goHistoricConsumModel:listHistoricConsumModelMeters ) {
            values.add(new BarEntry(i, (float) goHistoricConsumModel.getVolumen()));
            i++;
        }
        return values;
    }

    public ArrayList<String> getLabelsXAxisGraphicMeters(){
        ArrayList<String> xLabel = new ArrayList<>();

        for(HistoricConsumModel goHistoricConsumModel:listHistoricConsumModelMeters) {
            xLabel.add(CommonUtil.getMonthAndYearValueGraphics(getContext(),goHistoricConsumModel.getMesFact()));
        }

        return xLabel;
    }



    private final RectF onValueSelectedRectF = new RectF();

    @Override
    public void onValueSelected(Entry e, Highlight h) {
        if (e == null)
            return;

        RectF bounds = onValueSelectedRectF;
        chart.getBarBounds((BarEntry) e, bounds);
        MPPointF position = chart.getPosition(e, YAxis.AxisDependency.LEFT);

        MPPointF.recycleInstance(position);
    }

    @Override
    public void onNothingSelected() {

    }



    @Override
    public void showError(String psMessage) {

    }

    @Override
    public void showRetry() {

    }

    @Override
    public void hideRetry() {

    }

    @Override
    public Context context() {
        return this.getActivity();
    }

    @Override
    public void onResume() {
        super.onResume();
        //this.opPayInvoicesPresenter.resume();
    }

    @Override
    public void onPause() {
        super.onPause();
        //this.opPayInvoicesPresenter.pause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //this.opPayInvoicesPresenter.destroy();
    }

    @Override
    public void showSucessListPayInvoices(List<PayInvoicesModel> poPayInvoicesModel) {

    }

    @Override
    public void showErrorMessagePay(String poErrorMessage) {

    }
}