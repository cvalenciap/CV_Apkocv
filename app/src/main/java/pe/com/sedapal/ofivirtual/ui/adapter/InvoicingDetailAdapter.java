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
import pe.com.sedapal.ofivirtual.model.DetailInvoicesModel;

/**
 * Created by jsaenz on 13,diciembre,2018
 */
public class InvoicingDetailAdapter extends RecyclerView.Adapter<InvoicingDetailAdapter.DetailInvoiceViewHolder> {
    private final LayoutInflater goLayoutInflater;
    private List<DetailInvoicesModel> goDetailInvoicesModel;
    private final Context goContext;

    public InvoicingDetailAdapter(Context poContext) {
        this.goContext = poContext;
        this.goLayoutInflater =
                (LayoutInflater) poContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.goDetailInvoicesModel = Collections.emptyList();
    }

    @Override
    public int getItemCount() {
        return (this.goDetailInvoicesModel != null) ? this.goDetailInvoicesModel.size() : 0;
    }

    @Override
    public DetailInvoiceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = this.goLayoutInflater.inflate(R.layout.row_detail_invoices, parent, false);
        return new DetailInvoiceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DetailInvoiceViewHolder holder, int position) {
        final DetailInvoicesModel loDetailInvoiceViewHolder = this.goDetailInvoicesModel.get(position);

        holder.txtDescripcionConcepto.setText(loDetailInvoiceViewHolder.getDesConcepto());
        holder.txtMontoConcepto.setText(goContext.getResources().getString(R.string.lbl_money_soles) + " " + String.valueOf(loDetailInvoiceViewHolder.getMontoConcepto()));
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void setDetailInvoicesList(List<DetailInvoicesModel> poDetailInvoicesModel){
        this.validateDetailInvoiceList(poDetailInvoicesModel);
        this.goDetailInvoicesModel = (List<DetailInvoicesModel>) poDetailInvoicesModel;
        this.notifyDataSetChanged();
    }


    private void validateDetailInvoiceList(List<DetailInvoicesModel> poDetailInvoicesModel) {
        if (poDetailInvoicesModel == null) {
            throw new IllegalArgumentException("The list cannot be null");
        }
    }

    public class DetailInvoiceViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txtDescripcionConcepto)
        TextView txtDescripcionConcepto;
        @BindView(R.id.txtMontoConcepto)
        TextView txtMontoConcepto;

        public DetailInvoiceViewHolder(View poItemView) {
            super(poItemView);
            ButterKnife.bind(this, poItemView);
        }
    }
}
