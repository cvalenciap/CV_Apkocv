package pe.com.sedapal.ofivirtual.ui.fragment;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.InputFilter;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.gson.Gson;

import java.lang.reflect.Field;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pe.com.sedapal.ofivirtual.R;
import pe.com.sedapal.ofivirtual.data.entity.dto.SessionUserDto;
import pe.com.sedapal.ofivirtual.di.components.CommercialOfficeComponent;
import pe.com.sedapal.ofivirtual.model.LoginNewUserModel;
import pe.com.sedapal.ofivirtual.model.NisDetailModel;
import pe.com.sedapal.ofivirtual.model.NisModel;
import pe.com.sedapal.ofivirtual.navigation.Navigator;
import pe.com.sedapal.ofivirtual.presenter.sedapal.ValidSupplyPresenter;
import pe.com.sedapal.ofivirtual.presenter.user.DetailNisPresenter;
import pe.com.sedapal.ofivirtual.presenter.user.ListNisPresenter;
import pe.com.sedapal.ofivirtual.presenter.view.DetailNisView;
import pe.com.sedapal.ofivirtual.presenter.view.HomeFragmentView;
import pe.com.sedapal.ofivirtual.presenter.view.ListNisView;
import pe.com.sedapal.ofivirtual.presenter.view.ValidSupplyView;
import pe.com.sedapal.ofivirtual.ui.adapter.SpinnerNisAdapter;
import pe.com.sedapal.ofivirtual.util.ValidationUtil;

import static pe.com.sedapal.ofivirtual.data.repository.datasource.LocalUserSPDataStore.KEY_USER_SESSION_SP;

public class HomeFragment extends BaseFragment implements HomeFragmentView, ListNisView, DetailNisView, ValidSupplyView {

    @BindView(R.id.rbtnRecibosPendientes)
    RadioButton rbtnRecibosPendientes;
    @BindView(R.id.rbtnRecibosPagados)
    RadioButton rbtnRecibosPagados;
    @BindView(R.id.llRespuestaEncontroResultados)
    LinearLayout llRespuestaEncontroResultados;
    @BindView(R.id.llRespuestaNoResultados)
    LinearLayout llRespuestaNoResultados;
    @BindView(R.id.txtNoResultados)
    TextView txtNoResultados;
    @BindView(R.id.spnListNis)
    Spinner spnListNis;

    /**
     * Informacion del suministro en la parte superior
     */
    @BindView(R.id.txtEstadoSuministro)
    TextView txtEstadoSuministro;
    @BindView(R.id.txtDeudaTotal)
    TextView txtDeudaTotal;
    @BindView(R.id.txtDireccion)
    TextView txtDireccion;
    @BindView(R.id.llVerGraficosConsumo)
    LinearLayout llGraficosConsumo;
    @BindView(R.id.txtNombreTitular)
    TextView txtNombreTitular;

    /**
     * Contenido total de la pantalla suministros
     */
    @BindView(R.id.llContenidoSuministro)
    LinearLayout llContenidoSuministro;

    @BindView(R.id.llProfileAdmin)
    RelativeLayout llProfileAdmin;

    /**
     * CONTROLS VIEW LOAD RESOURCES
     */
    @BindView(R.id.rlProgressLoadScreeen)
    RelativeLayout rlProgressLoadScreeen;
    @BindView(R.id.pbProgressBarLoadScreen)
    ProgressBar pbProgressBarLoadScreen;
    @BindView(R.id.llError)
    LinearLayout llErrorScreen;
    @BindView(R.id.txtErrorDetail)
    TextView txtErrorDetail;
    @BindView(R.id.swpRlError)
    SwipeRefreshLayout swpRlError;
    @BindView(R.id.flSupplyPendingInvoices)
    FrameLayout flSupplyPendingInvoices;
    @BindView(R.id.flSupplyPayInvoices)
    FrameLayout flSupplyPayInvoices;

    @Inject
    ListNisPresenter opListNisPresenter;
    @Inject
    DetailNisPresenter opDetailNisPresenter;
    @Inject
    ValidSupplyPresenter goValidSupplyPresenter;

    @Inject
    public Navigator navigator;

    /**
     * El flag "isFirstLoad" permite no iniciar nuevas instancias de los fragments ubicados en
     * la parte inferior de la pantalla (Recibos pendientes, pagados)
     */
    public boolean isFirstLoad = true;

    /**
     * ALMACENA EL VALOR DEL NUMEOR DE SUMINISTRO QUE INGRESE UN USUARIO CON EL
     * PERFIL DE ADMINISTRADOR
     */
    String NUMERO_SUMINISTRO_INGRESADO = "0";


    private static final String TAG = HomeFragment.class.getSimpleName();
    private static final String BUNDLE_DATA_USER_LOGIN = "BUNDLE_DATA_USER_LOGIN";
    LoginNewUserModel goUsuarioLogueado;

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance(LoginNewUserModel poLoginNewUserModel) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        if(poLoginNewUserModel == null){
            poLoginNewUserModel = new LoginNewUserModel();
        }
        args.putSerializable(BUNDLE_DATA_USER_LOGIN, poLoginNewUserModel);
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
        final View loView = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, loView);
        setHasOptionsMenu(true);
        return loView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle poSavedInstanceState) {
        super.onViewCreated(view, poSavedInstanceState);
        this.opListNisPresenter.setView(this);
        this.opDetailNisPresenter.setView(this);
        this.goValidSupplyPresenter.setView(this);

        setUpView();
        setSearchtollbar();
    }

    private void setUpView() {
        getBundlesBeginOpFrequent();
        eventControls();
    }

    private void getBundlesBeginOpFrequent() {
        goUsuarioLogueado = (LoginNewUserModel) getArguments().getSerializable(BUNDLE_DATA_USER_LOGIN);
    }

    public void eventControls(){
        rbtnRecibosPendientes.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    loadPendingFragment();
                }
            }
        });

        rbtnRecibosPagados.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    loadPayFragment();
                }
            }
        });

        spnListNis.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(!isFirstLoad) {
                    isFirstLoad = true;
                    loadDetailNis("1");

                    //Se muestra un modal de carga ya que no es posible
                    //controlar esto desde la misma vista
                    showLoading(getResources().getString(R.string.lbl_consultando));

                }

                isFirstLoad = false;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        swpRlError.setColorSchemeColors(getResources().getColor(R.color.colorPrimarySwipe), getResources().getColor(R.color.colorSecondarySwipe));
        swpRlError.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
               loadControlsLayoutsFromResponse();
            }
        });

        /**
         * Hide layouts from response
         */
        loadControlsLayoutsFromResponse();
    }

    public void loadControlsLayoutsFromResponse(){

        Log.d("PRUEBA",String.valueOf(goUsuarioLogueado.getAdminEtic()));





        if(goUsuarioLogueado.getAdmin() == 1){
            llContenidoSuministro.setVisibility(View.INVISIBLE);//SE OCULTA TEMPORALMENTE EL CONTENIDO DEL SUMINISTRO
            rlProgressLoadScreeen.setVisibility(View.GONE);//SE MUESTRA EL FRAGMENT DE PROGRESS DIALOG
            llErrorScreen.setVisibility(View.GONE);//SE OCULTA EL FRAGMENT DE ERROR
            llProfileAdmin.setVisibility(View.VISIBLE);//SE MUESTRA LOS CONTROLES DE USUARIO ADMINISTRADOR
            llRespuestaNoResultados.setVisibility(View.GONE);//OCULTANDO LA RESPUESTA DE  NO RESULTADOS DEL ADMIN
        }else {
            this.loadListNisUser();
            llContenidoSuministro.setVisibility(View.INVISIBLE);//SE OCULTA TEMPORALMENTE EL CONTENIDO DEL SUMINISTRO
            rlProgressLoadScreeen.setVisibility(View.VISIBLE);//SE MUESTRA EL FRAGMENT DE PROGRESS DIALOG
            llErrorScreen.setVisibility(View.GONE);//SE OCULTA EL FRAGMENT DE ERROR
            llProfileAdmin.setVisibility(View.GONE);//SE OCULTA LOS CONTROLES DE USUARIO ADMINISTRADOR
            //llSearchFragment.setVisibility(View.GONE);//OCULTANDO EL BUSCADOR DE LA PARTE SUPERIOR
        }
    }

    @OnClick(R.id.llVerGraficosConsumo)
    public void goToWatchGraphics(){
        NisModel spnNisModel = spnListNis.getSelectedItem() == null ? new NisModel() : (NisModel)spnListNis.getSelectedItem();
        long nisRadSelected = spnNisModel.getNisRad();

        navigator.navigateToGraphicsActivity((Activity) getContext(),nisRadSelected);

    }

    public void searchContent(String strSupplySearch){
        //Buscar segun NIS ingresada
        hideKeyboard();
        isFirstLoad = true;

        if (strSupplySearch.length() == ValidationUtil.CANTIDAD_CARACTERES_SUMINSITRO) {
            //VERIFICANDO SU EL SUMINISTRO INGRESADO ES VALIDO



            NUMERO_SUMINISTRO_INGRESADO = strSupplySearch;
            goValidSupplyPresenter.initialize(NUMERO_SUMINISTRO_INGRESADO);
        }else {
            showDialogError(getResources().getString(R.string.lbl_error_til_number_supply));
        }
    }

    @Override
    public void showRetry() {

    }

    @Override
    public void hideRetry() {

    }

    @Override
    public void showError(String psMessage) {

    }

    /**
     * Permite obtener el listado de las NIS asociadas a un usuario
     */
    private void loadListNisUser(){
        /**
         *
         */
        if(goUsuarioLogueado!=null) {

            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
            SessionUserDto loResult =  new Gson().fromJson(preferences.getString(KEY_USER_SESSION_SP,""),SessionUserDto.class);

            this.opListNisPresenter.initialize(goUsuarioLogueado.getNisRad(),loResult.getEmail(), "2" );
        }
    }

    /**
     *
     * permite obtener el listado de las NIS a traves de un usuario administrador
     */

    private void loadListNisUserAdmin(String strSuministro){
        /**
         *
         */
        if(strSuministro!=null) {
            if(!strSuministro.equalsIgnoreCase("")) {
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
                SessionUserDto loResult =  new Gson().fromJson(preferences.getString(KEY_USER_SESSION_SP,""),SessionUserDto.class);

                this.opListNisPresenter.initialize(Long.parseLong(strSuministro), loResult.getEmail(),"2");
            }
        }
    }


    /**
     * Permite obtener el detalle de las nis
     */

    public void loadDetailNis(String flag ){
        /**
         * Se obtiene del combobox cargado, la nis por default, con esta se llamara a su detalle
         */
        NisModel spnNisModel = spnListNis.getSelectedItem() == null ? new NisModel() : (NisModel)spnListNis.getSelectedItem();
        long nisRadSelected = spnNisModel.getNisRad();
        Log.v("NIS RAD SELECTED",String.valueOf(nisRadSelected));

        /**
         * Se llama al webservices el cual obtendra el detalle de la nis seleccionada
         */
        if(nisRadSelected != 0) {

            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
            SessionUserDto loResult =  new Gson().fromJson(preferences.getString(KEY_USER_SESSION_SP,""),SessionUserDto.class);

            opDetailNisPresenter.initialize(nisRadSelected, loResult.getEmail(),"2",flag);
        }
    }







    //public void loadDetailNisProfileAdmin(){
    //    /**
    //     * Se obtiene del editext del buscador, la nis por default, con esta se llamara a su detalle
    //     */
    //    long nisRadSelected = Long.parseLong(NUMERO_SUMINISTRO_INGRESADO);
    //    Log.v("NIS RAD SELECTED",String.valueOf(nisRadSelected));
//
    //    /**
    //     * Se llama al webservices el cual obtendra el detalle de la nis seleccionada
    //     */
    //    if(nisRadSelected != 0) {
    //        opDetailNisPresenter.initialize(nisRadSelected);
    //    }
    //}

    /**
     * Events after response - webservices
     * @param nisRad
     */

    /**
     * Initialize value for default
     */
    public void loadFragmentsInvoices(long nisRad){

        rbtnRecibosPendientes.setChecked(true);
        showFragmentPendingInvoices(nisRad);
        showFragmentPayInvoices(nisRad);
    }

    /**
     * Show fragment with contains pending invoices
     * @param
     */

    public void loadPendingFragment(){
        flSupplyPendingInvoices.setVisibility(View.VISIBLE);
        flSupplyPayInvoices.setVisibility(View.GONE);
    }

    public void loadPayFragment(){
        flSupplyPendingInvoices.setVisibility(View.GONE);
        flSupplyPayInvoices.setVisibility(View.VISIBLE);
    }

    public void showFragmentPendingInvoices(long nisRad){
        Fragment pendingInvoicesFragment = PendingInvoicesFragment.newInstance(nisRad);
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.flSupplyPendingInvoices, pendingInvoicesFragment).commitAllowingStateLoss();
    }

    public void showFragmentPayInvoices(long nisRad){
        Fragment payInvoicesFragment = PayInvoicesFragment.newInstance(nisRad);
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.flSupplyPayInvoices, payInvoicesFragment).commitAllowingStateLoss();
    }

    //region RESPONSE WEBSERVICES
    /**
     * Response webservices
     * @return
     */

    @Override
    public void showSucessListNis(List<NisModel> poNisModel) {
        /**
         * Luego de haber obtenido la lista desde el webservices, se llena el adaptador
         */
        spnListNis.setAdapter(new SpinnerNisAdapter(getContext(), R.layout.simple_spinner_nis,poNisModel));

        if(poNisModel.size() == 1){
            spnListNis.setEnabled(false);
        }else {
            spnListNis.setEnabled(true);
        }


        /**
         * Se agrega una validacion adicional en caso el usuario tenga el perfil de administrador
         * ya que se debera ubicar y consultar por el numero de suministro ingresado, ya que al tener
         * un listado con suministros carga el combobox y debera ubicarse el elemento ingresado en el
         * searchview
         */
        if(goUsuarioLogueado.getAdmin()==1){
            spnListNis.setSelection(getIndexSpinnerListNis(Integer.parseInt(NUMERO_SUMINISTRO_INGRESADO)));
           loadDetailNis("0");
        }

        /**
         * Se obtiene del combobox cargado, la nis por default, con esta se llamara a su detalle
         */
        loadDetailNis("0");

    }

    private int getIndexSpinnerListNis(int idItem){
        int position = 0;
        for (int i=0;i<spnListNis.getCount();i++){
            NisModel itemPosition = (NisModel)spnListNis.getItemAtPosition(i);
            if (itemPosition.getNisRad() == idItem){
                position = i;
            }
        }
        return position;
    }

    @Override
    public void showErrorMessageListNis(String poErrorMessage) {
        showErrorItemsLoad(poErrorMessage);
    }

    @Override
    public void showSucessDetailNis(NisDetailModel poNisDetailModel) {
        //En caso se tenga una consulta previa
        //oculta el modal de carga
        hideLoading();

        hideItemsLoadSucess();
        showItemsLoadSucess();

        txtEstadoSuministro.setText(poNisDetailModel.getEstsum());
        txtDireccion.setText(poNisDetailModel.getDireccion());
        txtDeudaTotal.setText(getResources().getString(R.string.lbl_money_soles) + " "+poNisDetailModel.getTotalDeuda());
        txtNombreTitular.setText(poNisDetailModel.getNomCliente());

        loadFragmentsInvoices(poNisDetailModel.getNisRad());
    }

    @Override
    public void showErrorMessageDetailNis(String poErrorMessage) {
        showErrorItemsLoad(poErrorMessage);
    }

    //Verificando si existe el suministro
    @Override
    public void showSucessValidSupply(String message) {
        //SI EL SUMINISTRO ES VALIDO CARGA LOS CONTROLES DEL DETALLE DE NIS
        //this.loadDetailNisProfileAdmin();
        this.loadListNisUserAdmin(NUMERO_SUMINISTRO_INGRESADO);
    }

    @Override
    public void showErrorMessageSupply(String poErrorMessage) {
        showErrorValidSupplyLoadAdmin(poErrorMessage);
    }

    //endregion

    /**
     * Mostrar u ocultar los controles
     * @return
     */

    public void hideItemsLoadSucess(){
        rlProgressLoadScreeen.setVisibility(View.GONE);
        llContenidoSuministro.setVisibility(View.VISIBLE);
    }

    public void showItemsLoadSucess(){

        rlProgressLoadScreeen.setVisibility(View.GONE);
        llContenidoSuministro.setVisibility(View.VISIBLE);

        //if(goUsuarioLogueado.getAdmin()==1){
        //    showItemsLoadSucessAdmin();
        //}
    }

    public void showErrorItemsLoad(String poTxtError){
        //En caso se tenga una consulta previa
        //oculta el modal de carga
        hideLoading();


        isFirstLoad = true;
        llContenidoSuministro.setVisibility(View.INVISIBLE);
        if(swpRlError.isRefreshing()){
            swpRlError.setRefreshing(false);
        }

        pbProgressBarLoadScreen.setVisibility(View.GONE);
        rlProgressLoadScreeen.setVisibility(View.VISIBLE);
        llErrorScreen.setVisibility(View.VISIBLE);
        txtErrorDetail.setText(poTxtError);
    }

    /**
     * Perfil de administrador
     */

    //public void showItemsLoadSucessAdmin(){
    //    //Controles del perfil administador
    //    llProfileAdmin.setVisibility(View.GONE);
    //    //enableSeachBar();
//
    //    /**
    //     * Si el nis ingresado existe se obtiene desde el detalle obtenido y se carga por default
    //     * dentro del componente de nis, se realiza la carga al adaptador luego de realizada la obtencion
    //     * de la NIS correcta
    //     */
    //    List<NisModel> lstNisObtenido = new ArrayList<>();
    //    NisModel nisModel = new NisModel();
    //    nisModel.setNisRad(Long.parseLong(NUMERO_SUMINISTRO_INGRESADO));
    //    lstNisObtenido.add(nisModel);
    //    spnListNis.setAdapter(new SpinnerNisAdapter(getContext(), R.layout.simple_spinner_nis,lstNisObtenido));
    //    spnListNis.setEnabled(false);
    //}

    public void showErrorValidSupplyLoadAdmin(String poErrorMessage){
        isFirstLoad = true;

        //Layout load and error
        pbProgressBarLoadScreen.setVisibility(View.GONE);
        rlProgressLoadScreeen.setVisibility(View.GONE);//SE OCULTA EL FRAGMENT DE PROGRESS DIALOG
        llRespuestaNoResultados.setVisibility(View.VISIBLE);
        //txtNoResultados.setText("No se encontraron resultados para " + edtxtTextSearch.getText().toString());
        txtNoResultados.setText(poErrorMessage);

        //layout profile admin
        llProfileAdmin.setVisibility(View.VISIBLE);
        //enableSeachBar();

        //layout profile user common
        llContenidoSuministro.setVisibility(View.GONE);
    }

    //METODOS DEL VALIDADOR DE SUMINISTRO

    @Override
    public void showLoadingSupply() {
        isFirstLoad = true;

        //Layout load and error
        pbProgressBarLoadScreen.setVisibility(View.VISIBLE);
        rlProgressLoadScreeen.setVisibility(View.VISIBLE);//SE MUESTRA EL FRAGMENT DE PROGRESS DIALOG
        llRespuestaNoResultados.setVisibility(View.GONE);

        //hide layout from perfil profile admin
        llProfileAdmin.setVisibility(View.GONE);
        //disableSearchBar();

        //hide layout content supply
        llContenidoSuministro.setVisibility(View.GONE);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_home,menu);
        if(goUsuarioLogueado.getAdmin() != 1){
            MenuItem item = menu.findItem(R.id.action_search);
            item.setVisible(false);
        }

        super.onCreateOptionsMenu(menu, inflater);
    }

    /**
     *
     *
     * @param poListDocumentType
     */


    Toolbar searchtollbar;
    Menu search_menu;
    MenuItem item_search;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_search:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                    circleReveal(R.id.searchtoolbar,1,true,true);
                else
                    searchtollbar.setVisibility(View.VISIBLE);

                item_search.expandActionView();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void setSearchtollbar()
    {
        searchtollbar = (Toolbar) getActivity().findViewById(R.id.searchtoolbar);
        if (searchtollbar != null) {
            searchtollbar.inflateMenu(R.menu.menu_search);
            search_menu=searchtollbar.getMenu();

            searchtollbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                        circleReveal(R.id.searchtoolbar,1,true,false);
                    else
                        searchtollbar.setVisibility(View.GONE);
                }
            });

            item_search = search_menu.findItem(R.id.action_filter_search);

            MenuItemCompat.setOnActionExpandListener(item_search, new MenuItemCompat.OnActionExpandListener() {
                @Override
                public boolean onMenuItemActionCollapse(MenuItem item) {
                    // Do something when collapsed
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        circleReveal(R.id.searchtoolbar,1,true,false);
                    }
                    else
                        searchtollbar.setVisibility(View.GONE);
                    return true;
                }

                @Override
                public boolean onMenuItemActionExpand(MenuItem item) {
                    // Do something when expanded
                    return true;
                }
            });

            initSearchView();


        } else
            Log.d("toolbar", "setSearchtollbar: NULL");
    }

    public void initSearchView() {
        final SearchView searchView =
                (SearchView) search_menu.findItem(R.id.action_filter_search).getActionView();

        // Enable/Disable Submit button in the keyboard
        searchView.setSubmitButtonEnabled(false);
        searchView.setInputType(InputType.TYPE_CLASS_NUMBER);
        searchView.setMaxWidth(Integer.MAX_VALUE);

        // Change search close button image
        ImageView closeButton = (ImageView) searchView.findViewById(R.id.search_close_btn);
        closeButton.setImageResource(R.drawable.ic_close_toolbar_blue);


        // set hint and the text colors
        EditText txtSearch = ((EditText) searchView.findViewById(R.id.search_src_text));
        txtSearch.setHint(getResources().getString(R.string.lbl_hint_search));
        txtSearch.setHintTextColor(Color.DKGRAY);
        txtSearch.setTextColor(getResources().getColor(R.color.colorPrimary));
        txtSearch.setFilters(new InputFilter[]{new InputFilter.LengthFilter(7)});


        // set the cursor
        AutoCompleteTextView searchTextView = (AutoCompleteTextView) searchView.findViewById(R.id.search_src_text);
        try {
            Field mCursorDrawableRes = TextView.class.getDeclaredField("mCursorDrawableRes");
            mCursorDrawableRes.setAccessible(true);
            mCursorDrawableRes.set(searchTextView, R.drawable.search_cursor); //This sets the cursor resource ID to 0 or @null which will make it visible on white background
        } catch (Exception e) {
            e.printStackTrace();
        }

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchContent(query);
                searchView.clearFocus();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return true;
            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void circleReveal(int viewID, int posFromRight, boolean containsOverflow, final boolean isShow)
    {
        final View myView = getActivity().findViewById(viewID);

        int width=myView.getWidth();

        if(posFromRight>0)
            width-=(posFromRight*getResources().getDimensionPixelSize(R.dimen.abc_action_button_min_width_material))-(getResources().getDimensionPixelSize(R.dimen.abc_action_button_min_width_material)/ 2);
        if(containsOverflow)
            width-=getResources().getDimensionPixelSize(R.dimen.abc_action_button_min_width_overflow_material);

        int cx=width;
        int cy=myView.getHeight()/2;

        Animator anim;
        if(isShow)
            anim = ViewAnimationUtils.createCircularReveal(myView, cx, cy, 0,(float)width);
        else
            anim = ViewAnimationUtils.createCircularReveal(myView, cx, cy, (float)width, 0);

        anim.setDuration((long)220);

        // make the view invisible when the animation is done
        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                if(!isShow)
                {
                    super.onAnimationEnd(animation);
                    myView.setVisibility(View.INVISIBLE);
                }
            }
        });

        // make the view visible and start the animation
        if(isShow)
            myView.setVisibility(View.VISIBLE);

        // start the animation
        anim.start();
    }

    /**
     *
     * @param
     */

    @Override
    public void hideLoadingSupply() {

    }

    @Override
    public Context context() {
        return this.getActivity();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


}