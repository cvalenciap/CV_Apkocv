package pe.com.sedapal.ofivirtual.ui.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pe.com.sedapal.ofivirtual.R;
import pe.com.sedapal.ofivirtual.di.components.CommercialOfficeComponent;
import pe.com.sedapal.ofivirtual.model.ListPendingInvoicesModel;
import pe.com.sedapal.ofivirtual.model.PayInvoicesModel;
import pe.com.sedapal.ofivirtual.model.PendingInvoicesModel;
import pe.com.sedapal.ofivirtual.navigation.Navigator;
import pe.com.sedapal.ofivirtual.presenter.user.PendingInvoicesPresenter;
import pe.com.sedapal.ofivirtual.presenter.view.PendingInvoicesView;
import pe.com.sedapal.ofivirtual.ui.adapter.PendingInvoicesAdapter;

public class PendingInvoicesFragment extends BaseFragment implements PendingInvoicesView, PendingInvoicesAdapter.OnReloadClickListener {
    @BindView(R.id.rcvRecibosPendientes)
    RecyclerView rcvRecibosPendientes;
    @BindView(R.id.llFelicNoRecibosPendientes)
    LinearLayout llFelicNoRecibosPendientes;
    @BindView(R.id.btnPaySelected)
    Button btnPaySelected;
    @BindView(R.id.llCircleProgressBar)
    LinearLayout llCircleProgressBar;
    @BindView(R.id.swrlPendingInvoice)
    SwipeRefreshLayout swrlPendingInvoice;
    //error
    @BindView(R.id.llError)
    LinearLayout llError;
    @BindView(R.id.txtErrorDetail)
    TextView txtErrorDetail;

    private LinearLayoutManager layoutManager;
    PendingInvoicesAdapter goPendingInvoicesAdapter;

    //region CONSTANTS AND VARIABLES
    //INDICA EL NUMERO DE NIS RAD OBTENIDO
    long GET_BUNDLE_NIS_RAD = 0;
    //INDICA LA CANTIDAD DE ELEMENTOS A TRAER EN CADA LISTA
    public static final int PAGE_SIZE = 10;
    //INDICA LA PAGINA QUE SE REQUERIRA EN LA LISTA
    private int CURRENT_PAGE = 1;
    //INDICA SI SE INGRESA Y SE LLAMA A LA LISTA POR PRIMERA VEZ O SI SE LLAMARA A LAS SIGUIENTES
    //PAGINAS DE LA LISTA
    private boolean findPendingInvoicesFirstFetchCallback = true;
    private boolean isLastPage = false;
    private boolean isLoading = false;
    public boolean isMultiselect = false;
    //endregion

    @Inject
    public Navigator navigator;

    @Inject
    PendingInvoicesPresenter opPendingInvoicesPresenter;


    private static final String TAG = PendingInvoicesFragment.class.getSimpleName();

    private static final String BUNDLE_DATA_NIS_RAD = "BUNDLE_DATA_NIS_RAD";

    public PendingInvoicesFragment() {
        // Required empty public constructor
    }

    public static PendingInvoicesFragment newInstance(long nisRad) {
        PendingInvoicesFragment fragment = new PendingInvoicesFragment();
        Bundle args = new Bundle();
        args.putLong(BUNDLE_DATA_NIS_RAD, nisRad);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getComponent(CommercialOfficeComponent.class).inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View loView = inflater.inflate(R.layout.fragment_pending_invoices, container, false);
        ButterKnife.bind(this, loView);

        eventControls();
        setUpRecyclerViewPendingInvoices();
        return loView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle poSavedInstanceState) {
        super.onViewCreated(view, poSavedInstanceState);
        this.opPendingInvoicesPresenter.setView(this);
        getBundles();
        if (poSavedInstanceState == null) {
            this.loadPendingInvoices();
        }
    }

    public void getBundles(){
        GET_BUNDLE_NIS_RAD = getArguments().getLong(BUNDLE_DATA_NIS_RAD);
    }

    public void eventControls() {
        swrlPendingInvoice.setColorSchemeColors(getResources().getColor(R.color.colorPrimarySwipe), getResources().getColor(R.color.colorSecondarySwipe));
        swrlPendingInvoice.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                llError.setVisibility(View.GONE);
                onRefreshSwipe();
            }
        });

        /**
         * Hide layouts from response
         */
        llCircleProgressBar.setVisibility(View.VISIBLE);
        llFelicNoRecibosPendientes.setVisibility(View.GONE);
        btnPaySelected.setVisibility(View.GONE);
        llError.setVisibility(View.GONE);
        txtErrorDetail.setVisibility(View.GONE);
    }

    public void setUpRecyclerViewPendingInvoices() {
        layoutManager = new LinearLayoutManager(getActivity());
        rcvRecibosPendientes.setLayoutManager(layoutManager);

        goPendingInvoicesAdapter = new PendingInvoicesAdapter(getContext());
        rcvRecibosPendientes.setAdapter(goPendingInvoicesAdapter);

        // Pagination
        rcvRecibosPendientes.addOnScrollListener(recyclerViewOnScrollListener);
        goPendingInvoicesAdapter.setOnReloadClickListener(this);
        goPendingInvoicesAdapter.setOnItemClickListener(onPendingInvoiceItemClickListener);

        //rcvRecibosPendientes.setItemAnimator(new DefaultItemAnimator());
        //runAnimationRecyclerView(rcvRecibosPendientes);
    }

    //region EVENTS RECYCLERVIEW
    private RecyclerView.OnScrollListener recyclerViewOnScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            int visibleItemCount = layoutManager.getChildCount();
            int totalItemCount = layoutManager.getItemCount();
            int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();

            if (!isLoading && !isLastPage) {
                if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                        && firstVisibleItemPosition >= 0
                        && totalItemCount >= PAGE_SIZE) {
                    loadMoreItems();
                }
            }

        }
    };

    public void enableButtonSelectEventList(){
        /**
         * Verificara si se tiene una multiseleccion para mostrar el boton cuando
         * es solo un elemento en la lista
         */
        if (isMultiselect) {
            if (goPendingInvoicesAdapter.getSelectedItemCount() - 1 < 0) {
                isMultiselect = false;
                btnPaySelected.setVisibility(View.GONE);
            }
        } else {
            btnPaySelected.setVisibility(View.GONE);
        }

        btnPaySelected.setText(getResources().getString(R.string.lbl_pagar_recibo)
                + " (" +
                String.valueOf(goPendingInvoicesAdapter.getSelectedItemCount())
                + (goPendingInvoicesAdapter.getSelectedItemCount() == 1 ? " Seleccionado)" : " Seleccionados)"));
    }

    private PendingInvoicesAdapter.OnItemClickListener onPendingInvoiceItemClickListener =
            new PendingInvoicesAdapter.OnItemClickListener() {
                @Override
                public void onPendingInvoiceItemClicked(View view, PendingInvoicesModel obj, int pos) {
                    if (goPendingInvoicesAdapter.getSelectedItemCount() > 0) {
                        enableActionModeMultiSelect(pos);
                        enableButtonSelectEventList();

                    } else {
                        PendingInvoicesModel getPendingInvoice = goPendingInvoicesAdapter.getItem(pos);
                        goToDetailInvoiceActivity(getPendingInvoice);
                    }
                }

                @Override
                public void onPendingInvoiceItemLongClick(View view, PendingInvoicesModel obj, int pos) {
                    isMultiselect = true;
                    btnPaySelected.setVisibility(View.VISIBLE);
                    enableActionModeMultiSelect(pos);
                    enableButtonSelectEventList();
                }

                @Override
                public void onPayPendingInvoice(PendingInvoicesModel obj, int pos) {
                    showToastError(getResources().getString(R.string.lbl_recibos_pendientes_pagar));
                }
            };
    //endregion

    public void onRefreshSwipe() {
        //CLEAR AND INITIALIZE CONSTANTS AND VARIABLES
        goPendingInvoicesAdapter.deleteAllSelectedItems();
        btnPaySelected.setVisibility(View.GONE);
        goPendingInvoicesAdapter.clear();
        CURRENT_PAGE = 1;
        findPendingInvoicesFirstFetchCallback = true;
        isLastPage = false;
        isLoading = false;
        isMultiselect = false;
        goPendingInvoicesAdapter.updateFooter(PendingInvoicesAdapter.FooterType.LOAD_MORE);
        initializeGetValues(CURRENT_PAGE);
    }

    public void verifyIsRefreshingSwipe() {
        if (swrlPendingInvoice.isRefreshing()) {
            swrlPendingInvoice.setRefreshing(false);
        }
    }

    public void loadMoreItems() {
        findPendingInvoicesFirstFetchCallback = false;
        isLoading = true;
        CURRENT_PAGE += 1;

        loadPendingInvoices();
    }

    private void enableActionModeMultiSelect(int position) {
        goPendingInvoicesAdapter.toggleSelection(position);
    }

    //region CALL WEBSERVICES

    public void loadPendingInvoices() {
        //CAPTURANDO Y LLAMANDO AL WEBSERVICES CON EL NUMERO DE PAGINA SOLICITADA
        this.initializeGetValues(CURRENT_PAGE);
    }

    public void initializeGetValues(int currentPage) {
        Log.i("PAGINA", String.valueOf(currentPage));
        Log.i("GET_BUNDLE_NIS_RAD", String.valueOf(GET_BUNDLE_NIS_RAD));

        this.opPendingInvoicesPresenter.initialize(GET_BUNDLE_NIS_RAD, currentPage, PAGE_SIZE);
    }

    //endregion

    //region RESPONSE WEBSERVICES

    @Override
    public void showSucessListPendingInvoices(List<PendingInvoicesModel> poPendingInvoicesModel) {
        llCircleProgressBar.setVisibility(View.GONE);
        verifyIsRefreshingSwipe();

        if (poPendingInvoicesModel.isEmpty()) {
            llFelicNoRecibosPendientes.setVisibility(View.VISIBLE);
        } else {
            if (findPendingInvoicesFirstFetchCallback) {
                isLoading = false;
                if (poPendingInvoicesModel.size() > 0)
                    goPendingInvoicesAdapter.addAll(poPendingInvoicesModel);

                if (poPendingInvoicesModel.size() >= PAGE_SIZE) {
                    goPendingInvoicesAdapter.addFooter();
                } else {
                    isLastPage = true;
                }
            } else {
                goPendingInvoicesAdapter.removeFooter();
                isLoading = false;

                if (poPendingInvoicesModel.size() > 0)
                    goPendingInvoicesAdapter.addAll(poPendingInvoicesModel);

                if (poPendingInvoicesModel.size() >= PAGE_SIZE) {
                    goPendingInvoicesAdapter.addFooter();
                } else {
                    isLastPage = true;
                }
            }
        }
    }

    @Override
    public void showErrorMessagePending(String poErrorMessage) {
        verifyIsRefreshingSwipe();
        if (findPendingInvoicesFirstFetchCallback) {
            llCircleProgressBar.setVisibility(View.GONE);
            llError.setVisibility(View.VISIBLE);
            txtErrorDetail.setVisibility(View.VISIBLE);
            txtErrorDetail.setText(poErrorMessage);
            llFelicNoRecibosPendientes.setVisibility(View.GONE);
        } else {
            goPendingInvoicesAdapter.updateFooter(PendingInvoicesAdapter.FooterType.ERROR);
        }
    }

    //endregion

    @OnClick(R.id.btnPaySelected)
    public void getListPendingInvoicesSelected() {
        List<PendingInvoicesModel> pendingInvoicesListSelected = new ArrayList<>();
        List<Integer> selectedItemPositions = goPendingInvoicesAdapter.getSelectedItems();

        for (int i = 0; i < selectedItemPositions.size(); i++) {
            pendingInvoicesListSelected.add(goPendingInvoicesAdapter.getCheckSelectedItem(selectedItemPositions.get(i)));
        }

        goPendingInvoicesAdapter.notifyDataSetChanged();

        goToPaySummaryActivity(pendingInvoicesListSelected);
    }

    @Override
    public void showError(String psMessage) {
        isLoading = false;
    }

    @Override
    public void onReloadClick() {
        goPendingInvoicesAdapter.updateFooter(PendingInvoicesAdapter.FooterType.LOAD_MORE);
        loadPendingInvoices();
    }

    public void goToDetailInvoiceActivity(PendingInvoicesModel getPendingInvoice){
        getPendingInvoice.setNisRad(GET_BUNDLE_NIS_RAD);
        PayInvoicesModel goPayInvoicesModel = new PayInvoicesModel();
        navigator.navigateToDetailInvoiceActivity((Activity) getContext(),getPendingInvoice,goPayInvoicesModel);
    }

    public void goToPaySummaryActivity(List<PendingInvoicesModel> pendingInvoicesListSelected){
        ListPendingInvoicesModel goLiListPendingInvoicesModel = new ListPendingInvoicesModel();
        goLiListPendingInvoicesModel.setListPendingInvoicesModel(pendingInvoicesListSelected);

        navigator.navigateToPaySummaryActivity((Activity)getContext(),goLiListPendingInvoicesModel,GET_BUNDLE_NIS_RAD);
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
        this.opPendingInvoicesPresenter.resume();
    }

    @Override
    public void onPause() {
        super.onPause();
        this.opPendingInvoicesPresenter.pause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.opPendingInvoicesPresenter.destroy();
    }
}