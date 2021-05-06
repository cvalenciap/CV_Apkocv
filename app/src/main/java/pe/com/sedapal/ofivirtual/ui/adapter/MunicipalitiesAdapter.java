package pe.com.sedapal.ofivirtual.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import pe.com.sedapal.ofivirtual.R;
import pe.com.sedapal.ofivirtual.model.MunicipalitiesModel;

/**
 * Created by jsaenz on 20/03/2019
 */
public class MunicipalitiesAdapter extends RecyclerView.Adapter<MunicipalitiesAdapter.MunicipalitiesViewHolder> {
    private final LayoutInflater goLayoutInflater;
    private List<MunicipalitiesModel> goMunicipalitiesModel;
    private OnItemClickListener onItemClickListener;
    private final Context goContext;


    public interface OnItemClickListener {
        /**
         * On Item Clicked in list places
         *
         * @param psMunicipalitiesModel place id
         * @author jsaenz
         * @version 1.0
         * @since 12/01/2017
         */
        void onItemClick(MunicipalitiesModel psMunicipalitiesModel);
    }

    public MunicipalitiesAdapter(Context poContext) {
        this.goContext = poContext;
        this.goLayoutInflater =
                (LayoutInflater) poContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.goMunicipalitiesModel = Collections.emptyList();
    }

    @Override
    public int getItemCount() {
        return (this.goMunicipalitiesModel != null) ? this.goMunicipalitiesModel.size() : 0;
    }

    @Override
    public MunicipalitiesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = this.goLayoutInflater.inflate(R.layout.row_municipalities, parent, false);
        return new MunicipalitiesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MunicipalitiesViewHolder holder, int position) {
        final MunicipalitiesModel loMunicipalitiesViewHolder = this.goMunicipalitiesModel.get(position);

        holder.txtDistrito.setText(loMunicipalitiesViewHolder.getNomMunicipio());
        holder.txtNumIncidencias.setText(String.valueOf(loMunicipalitiesViewHolder.getNroIncidencias()));
        holder.crdMunicipality.setOnClickListener(v -> {
            MunicipalitiesAdapter.this.onItemClickListener.onItemClick(loMunicipalitiesViewHolder);
        });


    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void setMunicipalitiesList(List<MunicipalitiesModel> poMunicipalitiesModel){
        this.validateMunicipalitiesList(poMunicipalitiesModel);
        this.goMunicipalitiesModel = (List<MunicipalitiesModel>) poMunicipalitiesModel;
        this.notifyDataSetChanged();
    }

    public void clearAdapter(){
        goMunicipalitiesModel.clear();
        notifyDataSetChanged();
    }

    private void validateMunicipalitiesList(List<MunicipalitiesModel> poMunicipalitiesModel) {
        if (poMunicipalitiesModel == null) {
            throw new IllegalArgumentException("The list cannot be null");
        }
    }

    public class MunicipalitiesViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txtDistrito)
        TextView txtDistrito;
        @BindView(R.id.txtNumIncidencias)
        TextView txtNumIncidencias;
        @BindView(R.id.crdMunicipality)
        CardView crdMunicipality;

        public MunicipalitiesViewHolder(View poItemView) {
            super(poItemView);
            ButterKnife.bind(this, poItemView);
        }
    }


    public void setOnItemClickListener(OnItemClickListener poOnItemClickListener) {
        this.onItemClickListener = poOnItemClickListener;
    }
}
