package pe.com.sedapal.ofivirtual.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import pe.com.sedapal.ofivirtual.R;
import pe.com.sedapal.ofivirtual.model.NisModel;


public class SpinnerNisAdapter extends ArrayAdapter<NisModel> {
    private ArrayList<Boolean> mChecked;
    private List<NisModel> mValues;
    private Context mContext;

    public SpinnerNisAdapter(Context context, int resourceId, List<NisModel> values) {
        super(context, resourceId, values);
        mValues = values;
        mContext = context;
        mChecked = new ArrayList<Boolean>();
        for (int i = 0; i < mValues.size(); i++) {
            mChecked.add(false);
        }
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        View row = View.inflate(mContext, R.layout.select_spinner_nis, null);
        TextView label = (TextView) row.findViewById(R.id.textoOption);
        label.setText(String.valueOf(mValues.get(position).getNisRad()));
        return row;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row;
        if(mValues.size() == 1){
            row = View.inflate(mContext, R.layout.simple_spinner_nis_one_elem, null);
        }else {
            row = View.inflate(mContext, R.layout.simple_spinner_nis, null);
        }

        TextView label = (TextView) row.findViewById(R.id.tv_spinnervalue);
        label.setText(String.valueOf(mValues.get(position).getNisRad()));
        for (int i = 0; i < mChecked.size(); i++) {
            mChecked.set(i, (i == position));
        }
        return row;
    }
}