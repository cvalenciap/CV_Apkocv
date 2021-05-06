package pe.com.sedapal.ofivirtual.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import pe.com.sedapal.ofivirtual.R;
import pe.com.sedapal.ofivirtual.model.AffectAreaModel;

/**
 * Created by jsaenz on 20/03/2019
 */
public class AreaAffectedAdapter extends RecyclerView.Adapter<AreaAffectedAdapter.AffectedAreaViewHolder> {
    private final LayoutInflater goLayoutInflater;
    private List<AffectAreaModel> goAffectAreaModel;
    private final Context goContext;

    public AreaAffectedAdapter(Context poContext) {
        this.goContext = poContext;
        this.goLayoutInflater =
                (LayoutInflater) poContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.goAffectAreaModel = Collections.emptyList();
    }

    @Override
    public int getItemCount() {
        return (this.goAffectAreaModel != null) ? this.goAffectAreaModel.size() : 0;
    }

    @Override
    public AffectedAreaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = this.goLayoutInflater.inflate(R.layout.row_affected_area, parent, false);
        return new AffectedAreaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AffectedAreaViewHolder holder, int position) {
        final AffectAreaModel loAffectedAreaViewHolder = this.goAffectAreaModel.get(position);

        holder.txtDescArea.setText(loAffectedAreaViewHolder.getDescArea());
        holder.txtTipoArea.setText(String.valueOf(loAffectedAreaViewHolder.getTipArea()));

        if(position == goAffectAreaModel.size()-1){
            holder.viewSeparetor.setVisibility(View.GONE);
        }

    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void setAffectedAreaList(List<AffectAreaModel> poAffectAreaModel){
        this.validateAffectedAreaList(poAffectAreaModel);
        this.goAffectAreaModel = (List<AffectAreaModel>) poAffectAreaModel;
        this.notifyDataSetChanged();
    }

    private void validateAffectedAreaList(List<AffectAreaModel> poAffectAreaModel) {
        if (poAffectAreaModel == null) {
            throw new IllegalArgumentException("The list cannot be null");
        }
    }

    public class AffectedAreaViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txtDescArea)
        TextView txtDescArea;
        @BindView(R.id.txtTipoArea)
        TextView txtTipoArea;
        @BindView(R.id.viewSeparetor)
        View viewSeparetor;

        public AffectedAreaViewHolder(View poItemView) {
            super(poItemView);
            ButterKnife.bind(this, poItemView);
        }
    }
}
