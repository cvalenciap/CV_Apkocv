package pe.com.sedapal.ofivirtual.ui.adapter;

import android.content.Context;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import pe.com.sedapal.ofivirtual.R;
import pe.com.sedapal.ofivirtual.model.PendingInvoicesModel;
import pe.com.sedapal.ofivirtual.util.CommonUtil;

/**
 * Created by jsaenz on 10/03/2019
 */

public class PendingInvoicesAdapter extends BaseAdapterScrollLoad<PendingInvoicesModel> {

    public interface OnItemClickListener {
        /**
         * On Item Clicked in list to {@link PendingInvoicesModel}.
         *
         * @author jsaenz
         * @since 10/03/2019
         */

        void onPendingInvoiceItemClicked(View view, PendingInvoicesModel obj, int pos);

        void onPendingInvoiceItemLongClick(View view, PendingInvoicesModel obj, int pos);

        void onPayPendingInvoice(PendingInvoicesModel obj, int pos);
    }

    // region Member Variables
    private Context context;
    private FooterViewHolder footerViewHolder;
    private OnItemClickListener onItemClickListener;
    /**
     * Multiple selection variables
     */
    private SparseBooleanArray selectedMultipleItemPendingInvoice;
    private int currentMultiselectedidx = -1;
    // endregion

    // region Constructors
    public PendingInvoicesAdapter(Context goContext) {
        super();
        context = goContext;
        selectedMultipleItemPendingInvoice = new SparseBooleanArray();
    }
    // endregion

    @Override
    public int getItemViewType(int position) {
        return (isLastPosition(position) && isFooterAdded) ? FOOTER : ITEM;
    }

    @Override
    protected RecyclerView.ViewHolder createHeaderViewHolder(ViewGroup parent) {
        return null;
    }

    @Override
    protected RecyclerView.ViewHolder createItemViewHolder(ViewGroup parent) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_pending_invoices, parent, false);

        final PendingInvoicesViewHolder holder = new PendingInvoicesViewHolder(v);
        return holder;
    }

    @Override
    protected RecyclerView.ViewHolder createFooterViewHolder(ViewGroup parent) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.progress_load_resource_recyclerview, parent, false);

        final FooterViewHolder holder = new FooterViewHolder(v);
        holder.reloadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onReloadClickListener != null){
                    onReloadClickListener.onReloadClick();
                }
            }
        });

        return holder;
    }

    @Override
    protected void bindHeaderViewHolder(RecyclerView.ViewHolder viewHolder) {

    }
    boolean isMultiSelect = false;

    @Override
    protected void bindItemViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        final PendingInvoicesViewHolder holder = (PendingInvoicesViewHolder) viewHolder;

        final PendingInvoicesModel loPendingInvoiceViewHolder = getItem(position);
        if (loPendingInvoiceViewHolder != null) {
            holder.txtMesRecibo.setText(CommonUtil.getDateValueInvoice(context,loPendingInvoiceViewHolder.getMes()));
            holder.txtMontoTotal.setText(context.getResources().getString(R.string.lbl_money_soles) + " "+ CommonUtil.valueDoubleFormat(loPendingInvoiceViewHolder.getDeuda()));
            holder.txtTipoConsumo.setText(loPendingInvoiceViewHolder.getTipoRecibo());
            holder.txtFechaVencimiento.setText(CommonUtil.getFormatDateValue(loPendingInvoiceViewHolder.getVencimiento()));

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //if(!isMultiSelect) {
                    //    onItemClickListener.onPendingInvoiceItemClicked(items.get(position));
                    //} else {
                        onItemClickListener.onPendingInvoiceItemClicked(v, loPendingInvoiceViewHolder, position);
                    //}
                }
            });

            /**
             * Eventos para seleccionar las filas
             */

            holder.itemView.setActivated(selectedMultipleItemPendingInvoice.get(position, false));

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    isMultiSelect = true;
                    if (onItemClickListener == null) return false;
                    onItemClickListener.onPendingInvoiceItemLongClick(v, loPendingInvoiceViewHolder, position);
                    return true;
                }
            });

            toggleCheckedIcon(holder, position);
        }
    }

    @Override
    protected void bindFooterViewHolder(RecyclerView.ViewHolder viewHolder) {
        FooterViewHolder holder = (FooterViewHolder) viewHolder;
        footerViewHolder = holder;

        holder.loadingImageView.setIndeterminate(true);
    }

    @Override
    protected void displayLoadMoreFooter() {
        if(footerViewHolder!= null){
            footerViewHolder.errorRelativeLayout.setVisibility(View.GONE);
            footerViewHolder.loadingFrameLayout.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void displayErrorFooter() {
        if(footerViewHolder!= null){
            footerViewHolder.loadingFrameLayout.setVisibility(View.GONE);
            footerViewHolder.errorRelativeLayout.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void addFooter() {
        isFooterAdded = true;
        add(new PendingInvoicesModel());
    }

    // region Inner Classes

    public static class PendingInvoicesViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imgIconoEstado)
        ImageView imgIconoEstado;
        @BindView(R.id.txtMesRecibo)
        TextView txtMesRecibo;
        @BindView(R.id.txtMontoTotal)
        TextView txtMontoTotal;
        @BindView(R.id.txtTipoConsumo)
        TextView txtTipoConsumo;
        @BindView(R.id.txtFechaVencimiento)
        TextView txtFechaVencimiento;
        @BindView(R.id.imgIconoCheck)
        ImageView imgIconoCheck;

        public PendingInvoicesViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public static class FooterViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.loadmore_progress)
        ProgressBar loadingImageView;
        @BindView(R.id.loading_fl)
        FrameLayout loadingFrameLayout;
        @BindView(R.id.error_rl)
        RelativeLayout errorRelativeLayout;
        @BindView(R.id.reload_btn)
        Button reloadButton;

        public FooterViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    /**
     * Set on item click listener of {@link OnItemClickListener}.
     *
     * @param poOnItemClickListener List of {@link OnItemClickListener}.
     * @author jsaenz
     * @version 1.0
     * @since 12/01/2017
     */
    public void setOnItemClickListener(OnItemClickListener poOnItemClickListener) {
        this.onItemClickListener = poOnItemClickListener;
    }

    /**
     * Multiple selection
     */

    private void toggleCheckedIcon(PendingInvoicesViewHolder holder, int position) {
        if (selectedMultipleItemPendingInvoice.get(position, false)) {
            holder.imgIconoEstado.setVisibility(View.GONE);
            holder.imgIconoCheck.setVisibility(View.VISIBLE);
            if (currentMultiselectedidx == position) resetCurrentIndex();
        } else {
            holder.imgIconoCheck.setVisibility(View.GONE);
            holder.imgIconoEstado.setVisibility(View.VISIBLE);
            if (currentMultiselectedidx == position) resetCurrentIndex();
        }
    }

    public void toggleSelection(int pos) {
        currentMultiselectedidx = pos;
        //Verificando si se tiene agregado el item
        if (selectedMultipleItemPendingInvoice.get(pos, false)) {
            selectedMultipleItemPendingInvoice.delete(pos);

            //Agregado para limpiar los items posteriores al seleccionado
            PendingPayAddItemsValidate(pos);
            //end

            notifyItemChanged(pos);
        } else {
            //si no se tiene agregado comprueba si se selecciono el item anterior
            if (PendingPayValidateAddItem(pos)) {
                selectedMultipleItemPendingInvoice.put(pos, true);
                notifyItemChanged(pos);
            }
        }
    }

    //public void toggleSelection(int pos) {
    //    currentMultiselectedidx = pos;
    //    if (selectedMultipleItemPendingInvoice.get(pos, false)) {
    //        selectedMultipleItemPendingInvoice.delete(pos);
    //        notifyItemChanged(pos);
    //    } else {
    //        selectedMultipleItemPendingInvoice.put(pos, true);
    //    }
//
    //    notifyItemChanged(pos);
    //}

    /**
     * Opciones agregadas para validar si se tiene recibos pendientes por pagar
     * @param pos
     */
    public void PendingPayAddItemsValidate(int pos){
        for(int i = pos+1; i < items.size();i++){
            selectedMultipleItemPendingInvoice.delete(i);
            notifyItemChanged(i);
        }
    }

    public boolean PendingPayValidateAddItem(int pos){
        if(pos == 0){
            selectedMultipleItemPendingInvoice.put(pos, true);
            return true;
        }else{
            int position = pos -1;
            if(selectedMultipleItemPendingInvoice.get(position,false)){
                return true;
            }else {
                onItemClickListener.onPayPendingInvoice(items.get(position),position);
                return false;
            }
        }
    }

    public void deleteAllSelectedItems(){
        for(int i = 0; i < items.size();i++){
            selectedMultipleItemPendingInvoice.delete(i);
            notifyItemChanged(i);
        }
    }

    /**
     * end
     */

    private void resetCurrentIndex() {
        currentMultiselectedidx = -1;
    }

    public PendingInvoicesModel getItem(int position) {
        return items.get(position);
    }

    public List<PendingInvoicesModel> getAllListLoadPendingInvoicesModel(){
        return items;
    }

    public int getSelectedItemCount() {
        return selectedMultipleItemPendingInvoice.size();
    }

    public List<Integer> getSelectedItems() {
        List<Integer> items = new ArrayList<>(selectedMultipleItemPendingInvoice.size());
        for (int i = 0; i < selectedMultipleItemPendingInvoice.size(); i++) {
            items.add(selectedMultipleItemPendingInvoice.keyAt(i));
        }
        return items;
    }


    public PendingInvoicesModel getCheckSelectedItem(int position) {
        return items.get(position);
    }

}