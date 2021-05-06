package pe.com.sedapal.ofivirtual.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;

import androidx.appcompat.widget.Toolbar;

import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pe.com.sedapal.ofivirtual.R;
import pe.com.sedapal.ofivirtual.di.HasComponent;
import pe.com.sedapal.ofivirtual.di.components.DaggerUpdateDataUserComponent;
import pe.com.sedapal.ofivirtual.di.components.UpdateDataUserComponent;
import pe.com.sedapal.ofivirtual.model.ApplicantTypeModel;
import pe.com.sedapal.ofivirtual.model.DistrictModel;
import pe.com.sedapal.ofivirtual.model.DocumentTypeModel;
import pe.com.sedapal.ofivirtual.model.GenderPersonModel;
import pe.com.sedapal.ofivirtual.model.LoginNewUserModel;
import pe.com.sedapal.ofivirtual.model.ObtainDataUserLoginModel;
import pe.com.sedapal.ofivirtual.model.ValidDocumentTypeModel;
import pe.com.sedapal.ofivirtual.presenter.sedapal.ApplicantTypePresenter;
import pe.com.sedapal.ofivirtual.presenter.sedapal.DistrictPresenter;
import pe.com.sedapal.ofivirtual.presenter.sedapal.DocumentTypePresenter;
import pe.com.sedapal.ofivirtual.presenter.sedapal.UpdateDataUserPresenter;
import pe.com.sedapal.ofivirtual.presenter.user.ObtainDataUserLoginPresenter;
import pe.com.sedapal.ofivirtual.presenter.view.ApplicantTypeView;
import pe.com.sedapal.ofivirtual.presenter.view.DistrictView;
import pe.com.sedapal.ofivirtual.presenter.view.DocumentTypeView;
import pe.com.sedapal.ofivirtual.presenter.view.ObtainDataUserLoginView;
import pe.com.sedapal.ofivirtual.presenter.view.UpdateDataUserView;
import pe.com.sedapal.ofivirtual.ui.widget.MaterialSpinner;
import pe.com.sedapal.ofivirtual.util.CommonUtil;
import pe.com.sedapal.ofivirtual.util.ValidationUtil;

public class UpdateDataUserActivity extends BaseActivity implements HasComponent<UpdateDataUserComponent>, UpdateDataUserView, ApplicantTypeView, DocumentTypeView, DistrictView, ObtainDataUserLoginView {

    @BindView(R.id.toolbar_logo)
    Toolbar toolbar_logo;


    @BindView(R.id.tilNumSuminsitroActDatos)
    TextInputLayout tilNumSuminsitro;
    @BindView(R.id.spnTipoDocumentoActDatos)
    MaterialSpinner spnTipoDocumento;
    @BindView(R.id.tilNumDocumentoActDatos)
    TextInputLayout tilNumDocumento;
    @BindView(R.id.edtxtNumDocumentoActDatos)
    EditText edtxtNumDocumento;
    @BindView(R.id.tilNombresActDatos)
    TextInputLayout tilNombres;
    @BindView(R.id.edtxtNombresActDatos)
    EditText edtxtNombres;
    @BindView(R.id.tilApellido_1ActDatos)
    TextInputLayout tilApellido_1;
    @BindView(R.id.edtxtApellido_1ActDatos)
    EditText edtxtApellido_1;
    @BindView(R.id.tilApellido_2ActDatos)
    TextInputLayout tilApellido_2;
    @BindView(R.id.edtxtApellido_2ActDatos)
    EditText edtxtApellido_2;
    @BindView(R.id.tilNombreEmpresaActDatos)
    TextInputLayout tilNombreEmpresa;
    @BindView(R.id.edtxtNombreEmpresaActDatos)
    EditText edtxtNombreEmpresa;
    @BindView(R.id.tilTelefono_1ActDatos)
    TextInputLayout tilTelefono1;
    @BindView(R.id.tilTelefono_2ActDatos)
    TextInputLayout tilTelefono2;
    @BindView(R.id.edtxtTelefono_1ActDatos)
    EditText edtxtTelefono1;
    @BindView(R.id.edtxtTelefono_2ActDatos)
    EditText edtxtTelefono2;
    @BindView(R.id.chkRecNotificaActDatos)
    CheckBox chkRecNotifica;

    @BindView(R.id.spnSexoActDatos)
    MaterialSpinner spnSexo;
    @BindView(R.id.spnDistritoActDatos)
    MaterialSpinner spnDistrito;
    @BindView(R.id.tilFechaNacimientoActDatos)
    TextInputLayout tilFechaNacimiento;
    @BindView(R.id.edtxtFechaNacimientoActDatos)
    EditText edtxtFechaNacimiento;
    @BindView(R.id.spnTipoSolicitanteActDatos)
    MaterialSpinner spnTipoSolicitante;
    @BindView(R.id.edtxtDireccion)
    EditText edtxtDireccion;

    @BindView(R.id.scrollScreenContaint)
    ScrollView scrollScreenContaint;
    @BindView(R.id.llCircleProgressBar)
    LinearLayout llCircleProgressBar;

    @BindView(R.id.btnActualizarDatos)
    Button btnActualizarDatos;

    @BindView(R.id.progressLoadDocumentTypeActDatos)
    ProgressBar progressLoadDocumentType;

    private static final String BUNDLE_USER_LOGIN = "USER_LOGIN";
    private UpdateDataUserComponent goUpdateDataUserComponent;
    String TAG = UpdateDataUserActivity.class.getName();
    LoginNewUserModel goUsuarioLogueado;

    @Inject
    DocumentTypePresenter goDocumentTypePresenter;

    @Inject
    ApplicantTypePresenter goApplicantTypePresenter;

    @Inject
    DistrictPresenter goDistrictPresenter;

    @Inject
    ObtainDataUserLoginPresenter goObtainDataUserLoginPresenter;

    @Inject
    UpdateDataUserPresenter goUpdateDataUserPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_datos);
        ButterKnife.bind(this);
        initializeInjector();
        setUpView();
    }

    public static Intent getCallingIntent(Context poContext, LoginNewUserModel poUserModel) {
        Bundle loBundle = new Bundle();
        loBundle.putSerializable(BUNDLE_USER_LOGIN, poUserModel);
        Intent loIntent = new Intent(poContext, UpdateDataUserActivity.class);
        loIntent.putExtras(loBundle);
        return loIntent;
    }

    private void configToolbar(){
        setSupportActionBar(toolbar_logo);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar_logo.setNavigationIcon(R.drawable.ic_back_toolbar);
    }

    @Override
    public UpdateDataUserComponent getComponent() {
        return this.goUpdateDataUserComponent;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initializeInjector() {
        this.goUpdateDataUserComponent = DaggerUpdateDataUserComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build();
        this.goUpdateDataUserComponent.inject(UpdateDataUserActivity.this);
    }

    private void setUpView() {
        goUsuarioLogueado = (LoginNewUserModel) getIntent().getSerializableExtra(BUNDLE_USER_LOGIN);

        this.goDocumentTypePresenter.setView(this);
        this.goApplicantTypePresenter.setView(this);
        this.goDistrictPresenter.setView(this);
        this.goObtainDataUserLoginPresenter.setView(this);
        this.goUpdateDataUserPresenter.setView(this);

        configToolbar();
        loadControls();
        configControls();
        callObtainDataUserLogin();
    }

    /**
     * Carga inicial del aplicativo
     */

    public void callObtainDataUserLogin(){
        goObtainDataUserLoginPresenter.initialize(goUsuarioLogueado.getIdCliente());
    }

    //region Configuracion de controles
    /**
     * Configuracion de controles
     */
    public void loadControls() {
        goDocumentTypePresenter.obtainListDocumentType();
        goApplicantTypePresenter.initialize();
        goDistrictPresenter.initialize();

        showSpnListSexo();
    }

    public void showSpnListSexo(){
        ArrayAdapter<GenderPersonModel> arrAdapterSexo = new ArrayAdapter<GenderPersonModel>(this, android.R.layout.simple_spinner_item, ValidationUtil.lstValSpnGenero());
        arrAdapterSexo.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        spnSexo.setAdapter(arrAdapterSexo);
        spnSexo.setPaddingSafe(0, 0, 0, 0);
    }

    @Override
    public void showSucessListDocumentType(List<DocumentTypeModel> poListDocumentType) {
        ArrayAdapter<DocumentTypeModel> adapterTipoDocumento = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, poListDocumentType);
        adapterTipoDocumento.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        spnTipoDocumento.setAdapter(adapterTipoDocumento);
        spnTipoDocumento.setPaddingSafe(0, 0, 0, 0);
    }


    @Override
    public void showSucessApplicantType(List<ApplicantTypeModel> poListApplicantTypeModel) {
        ArrayAdapter<ApplicantTypeModel> adapter = new ArrayAdapter<ApplicantTypeModel>(this, android.R.layout.simple_spinner_item, poListApplicantTypeModel);
        adapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        spnTipoSolicitante.setAdapter(adapter);
        spnTipoSolicitante.setPaddingSafe(0, 0, 0, 0);
    }

    @Override
    public void showSucessListDictrict(List<DistrictModel> poListDistrictModel) {
        ArrayAdapter<DistrictModel> adapterDistrito = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, poListDistrictModel);
        adapterDistrito.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        spnDistrito.setAdapter(adapterDistrito);
        spnDistrito.setPaddingSafe(0, 0, 0, 0);
    }

    private void configControls(){
        edtxtTelefono1.setFilters(new InputFilter[]{new InputFilter.LengthFilter(ValidationUtil.CANTIDAD_CARACTERES_TELEFONO)});
        edtxtTelefono2.setFilters(new InputFilter[]{new InputFilter.LengthFilter(ValidationUtil.CANTIDAD_CARACTERES_TELEFONO)});

        //Ocultar campos de la informacion del usuario cuando se ingresa a la pantalla por primera vez
        tilNombres.setVisibility(View.GONE);
        tilApellido_1.setVisibility(View.GONE);
        tilApellido_2.setVisibility(View.GONE);
        tilNombreEmpresa.setVisibility(View.GONE);
        btnActualizarDatos.setEnabled(true);

        personalizateUpdateData();

        //disableControls(false);
    }

    public void personalizateUpdateData(){
        //Personalizar labels datos obligatorios
        spnTipoDocumento.setFloatingLabelText(getResources().getString(R.string.lbl_tipo_documento) + " " + getResources().getString(R.string.lbl_obligatorio_ind));
        spnTipoDocumento.setHint(getResources().getString(R.string.lbl_tipo_documento) + " " + getResources().getString(R.string.lbl_obligatorio_ind));

        tilNumDocumento.setHint(getResources().getString(R.string.lbl_num_documento) + " " + getResources().getString(R.string.lbl_obligatorio_ind));
        tilApellido_1.setHint(getResources().getString(R.string.lbl_apellidos_paterno) + " " + getResources().getString(R.string.lbl_obligatorio_ind));
        tilApellido_2.setHint(getResources().getString(R.string.lbl_apellidos_materno) + " " + getResources().getString(R.string.lbl_obligatorio_ind));
        tilNombres.setHint(getResources().getString(R.string.lbl_nombres) + " " + getResources().getString(R.string.lbl_obligatorio_ind));
        tilNombreEmpresa.setHint(getResources().getString(R.string.lbl_nombre_empresa) + " " + getResources().getString(R.string.lbl_obligatorio_ind));
        tilTelefono1.setHint(getResources().getString(R.string.lbl_telefono_1) + " " + getResources().getString(R.string.lbl_obligatorio_ind));

        //Desabilitando campos que no permitan editarse en la actualizacion
        tilNumSuminsitro.setEnabled(false);
    }
    //endregion

    //region VALIDATE CONTROLS AFTER EVENT
    /**
     * Validar campos al ingresar password
     */
    boolean isValidDocumentType = false;
    boolean isValidNumDoc = false;
    boolean isReciveNotifi = false;
    boolean isValidNames = false;
    boolean isValidLastName_1 = false;
    boolean isValidLastName_2 = false;
    boolean isValidEnterpriseName = false;
    boolean isValidPhone = false;

    /**
     * Validando campos al seleccionar el tipo de documento, mostrando la informacion
     * del tipo de documento
     */
    public void desaTipoDocumento() {
        edtxtNombreEmpresa.getText().clear();
        edtxtNombres.getText().clear();
        edtxtApellido_1.getText().clear();
        edtxtApellido_2.getText().clear();
        edtxtTelefono1.getText().clear();

        tilNombreEmpresa.setError(null);
        tilNombreEmpresa.setErrorEnabled(false);
        tilNombres.setError(null);
        tilNombres.setErrorEnabled(false);
        tilApellido_1.setError(null);
        tilApellido_1.setErrorEnabled(false);
        tilApellido_2.setError(null);
        tilApellido_2.setErrorEnabled(false);

        tilNombreEmpresa.setVisibility(View.GONE);
        tilNombres.setVisibility(View.GONE);
        tilApellido_1.setVisibility(View.GONE);
        tilApellido_2.setVisibility(View.GONE);
        tilTelefono1.setVisibility(View.GONE);
    }

    private void isCorrectNumDocAlert(boolean response) {
        if (response) {
            tilNumDocumento.setErrorEnabled(false);
            tilNumDocumento.setError(null);

        } else {
            tilNumDocumento.setErrorEnabled(true);
            tilNumDocumento.setError(getResources().getString(R.string.lbl_error_numero_documento));
            isValidNumDoc = false;

        }
    }

    public boolean isFirstLoad = true;
    private void eventControls() {

        spnTipoSolicitante.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                isPendingValueInformation();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spnTipoDocumento.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //desaTipoDocumento();
                //desaTipoDocumentoSelectCombobox();
//
                DocumentTypeModel tipoDocumentoSelecc = (DocumentTypeModel) parent.getSelectedItem();
                if (tipoDocumentoSelecc != null) {
                    configurarCantidadDigitosNumDocumento(tipoDocumentoSelecc.getIdTipoDocumento());
                    isValidDocumentType = true;
                    edtxtNumDocumento.setEnabled(true);
                } else {
                    isValidDocumentType = false;
                    edtxtNumDocumento.setEnabled(false);
                }

                if(isFirstLoad) {
                    isFirstLoad = false;
                    //validateDocumentAfterLoadInformationUser(edtxtNumDocumento.getText().toString());
                    configurarMostrarControlesTipoDocumento(tipoDocumentoSelecc.getIdTipoDocumento());
                }else {
                    edtxtNumDocumento.getText().clear();
                }

                isPendingValueInformation();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        edtxtNumDocumento.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                desaTipoDocumento();

                if (s.length() == cantidadCaracteresNumDoc && s.length() >0) {
                    hideKeyboard();

                    isCorrectNumDocAlert(true);
                    DocumentTypeModel spnDocumentTypeModel = spnTipoDocumento.getSelectedItem() == null ? new DocumentTypeModel() : (DocumentTypeModel) spnTipoDocumento.getSelectedItem();

                  //clb
                   // goDocumentTypePresenter.validateTypeDocument(spnDocumentTypeModel.getIdTipoDocumento(), edtxtNumDocumento.getText().toString());
                } else {
                    isCorrectNumDocAlert(false);
                }

                isPendingValueInformation();
            }
        });

        edtxtNombres.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (edtxtNombres.getText().toString().length() >0) {
                    tilNombres.setErrorEnabled(false);
                    tilNombres.setError(null);

                    isValidNames = true;
                } else {
                    tilNombres.setErrorEnabled(true);
                    tilNombres.setError(getResources().getString(R.string.lbl_error_til_nombres));

                    isValidNames = false;
                }

                isPendingValueInformation();
            }
        });

        edtxtApellido_1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (edtxtApellido_1.getText().toString().length() >0) {
                    tilApellido_1.setErrorEnabled(false);
                    tilApellido_1.setError(null);

                    isValidLastName_1 = true;
                } else {
                    tilApellido_1.setErrorEnabled(true);
                    tilApellido_1.setError(getResources().getString(R.string.lbl_error_til_apellido_1));

                    isValidLastName_1 = false;
                }

                isPendingValueInformation();
            }
        });

        edtxtApellido_2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (edtxtApellido_2.getText().toString().length() >0) {
                    tilApellido_2.setErrorEnabled(false);
                    tilApellido_2.setError(null);

                    isValidLastName_2 = true;
                } else {
                    tilApellido_2.setErrorEnabled(true);
                    tilApellido_2.setError(getResources().getString(R.string.lbl_error_til_apellido_2));

                    isValidLastName_2 = false;
                }

                isPendingValueInformation();
            }
        });


        edtxtNombreEmpresa.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (edtxtNombreEmpresa.getText().toString().length() >0) {
                    tilNombreEmpresa.setErrorEnabled(false);
                    tilNombreEmpresa.setError(null);

                    isValidEnterpriseName = true;
                } else {
                    tilNombreEmpresa.setErrorEnabled(true);
                    tilNombreEmpresa.setError(getResources().getString(R.string.lbl_error_til_empresa));

                    isValidEnterpriseName = false;
                }

                isPendingValueInformation();
            }
        });

        edtxtTelefono1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (ValidationUtil.esValidoNumeroTelefono(edtxtTelefono1.getText().toString())) {
                    tilTelefono1.setErrorEnabled(false);
                    tilTelefono1.setError(null);

                    isValidPhone = true;
                } else {
                    tilTelefono1.setErrorEnabled(true);
                    tilTelefono1.setError(getResources().getString(R.string.lbl_error_til_telefono));

                    isValidPhone = false;
                }

                isPendingValueInformation();
            }
        });

        edtxtTelefono2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (ValidationUtil.esValidoNumeroTelefono(edtxtTelefono2.getText().toString())) {
                    tilTelefono2.setErrorEnabled(false);
                    tilTelefono2.setError(null);
                } else {
                    tilTelefono2.setErrorEnabled(true);
                    tilTelefono2.setError(getResources().getString(R.string.lbl_error_til_telefono));
                }

                isPendingValueInformation();
            }
        });

        chkRecNotifica.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    isReciveNotifi = true;
                } else {
                    isReciveNotifi = false;
                }

                isPendingValueInformation();
            }
        });

        /**
         * Hide keyboard if is enable after touch spinners
         */

        spnTipoDocumento.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                hideKeyboard();
                return false;
            }
        });

        spnSexo.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                hideKeyboard();
                return false;
            }
        });

        spnDistrito.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                hideKeyboard();
                return false;
            }
        });

        tilFechaNacimiento.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                hideKeyboard();
                return false;
            }
        });
    }

    @OnClick({R.id.tilFechaNacimientoActDatos, R.id.edtxtFechaNacimientoActDatos})
    public void seleccionarFechaNacimiento() {
        showDatePicker(new CallbackDialogDate() {
            @Override
            public void fecha(String fecha) {
                edtxtFechaNacimiento.setText(fecha);
            }
        });
    }

    public void configurarMostrarControlesTipoDocumento(int idTipoDocumento) {
        switch (idTipoDocumento) {
            case ValidationUtil.ID_CARNET_EXTRNJERIA:
                enableVisibilityUserInformation();
                break;
            case ValidationUtil.ID_DNI:
                enableVisibilityUserInformation();
                break;
            case ValidationUtil.ID_PARTIDA_NACIMIENTO:
                enableVisibilityUserInformation();
                break;
            case ValidationUtil.ID_PASAPORTE:
                enableVisibilityUserInformation();
                break;
            case ValidationUtil.ID_RUC:
                enableVisibilityUserInformationEnterprise();
                break;
            default:

                break;
        }

    }

    public void enableVisibilityUserInformation() {
        tilNombreEmpresa.setVisibility(View.GONE);
        tilNombres.setVisibility(View.VISIBLE);
        tilApellido_1.setVisibility(View.VISIBLE);
        tilApellido_2.setVisibility(View.VISIBLE);
        tilTelefono1.setVisibility(View.VISIBLE);
        tilTelefono2.setVisibility(View.VISIBLE);
    }

    public void enableVisibilityUserInformationEnterprise() {
        tilNombreEmpresa.setVisibility(View.VISIBLE);
        tilNombres.setVisibility(View.GONE);
        tilApellido_1.setVisibility(View.GONE);
        tilApellido_2.setVisibility(View.GONE);
        tilTelefono1.setVisibility(View.VISIBLE);
        tilTelefono2.setVisibility(View.VISIBLE);
    }

    //endregion

    //region VALUE IF CONTROLS ALL COMPLETE
    public void isPendingValueInformation() {
        if (isValidInformation()) {
            btnActualizarDatos.setEnabled(true);
        } else btnActualizarDatos.setEnabled(false);

    }

    public boolean isValidInformation() {

        if (!isValidDocumentType) {
            return false;
        }

        if (!isValidNumDoc) {
            return false;
        }

        if (!isValidPhone) {
            return false;
        }

        DocumentTypeModel tipoDocumentoSelecc =  (DocumentTypeModel)spnTipoDocumento.getSelectedItem();
        if(tipoDocumentoSelecc != null) {
            if (tipoDocumentoSelecc.getIdTipoDocumento() == ValidationUtil.ID_RUC) {
                if (!isValidEnterpriseName) {
                    return false;
                }
            }else {
                if (!isValidNames) {
                    return false;
                }

                if (!isValidLastName_1) {
                    return false;
                }

                if (!isValidLastName_2) {
                    return false;
                }
            }
        }

        return true;
    }
    //endregion

    //region SET VALUES IN CONTROLS

    /**
     * Permite configurar los controles segun los valores seleccionados
     *
     * @param idTipoDocumento
     */

    int cantidadCaracteresNumDoc;

    public void configurarCantidadDigitosNumDocumento(int idTipoDocumento) {
        cantidadCaracteresNumDoc = ValidationUtil.configurarCantidadDigitosNumDocumento(idTipoDocumento);
        edtxtNumDocumento.setEnabled(true);
        edtxtNumDocumento.setFilters(new InputFilter[]{new InputFilter.LengthFilter(cantidadCaracteresNumDoc)});

        if (ValidationUtil.tieneNumeros(idTipoDocumento)) {
            edtxtNumDocumento.setInputType(InputType.TYPE_CLASS_NUMBER);
        } else {
            edtxtNumDocumento.setInputType(InputType.TYPE_CLASS_TEXT);
        }
    }

    public void cargarDatosObtenidosNumDocumento(ValidDocumentTypeModel poValidDocumentTypeModel, int idTipoDocumento) {

        /**
         * Cargando datos obtenidos
         */

        if (!poValidDocumentTypeModel.getTelefono_1().equalsIgnoreCase("")) {
            edtxtTelefono1.setText(poValidDocumentTypeModel.getTelefono_1());
            //edtxtTelefono2.setText(poValidDocumentTypeModel.getTelefono_2());
        }

        if (idTipoDocumento == ValidationUtil.ID_RUC) {
            if (!poValidDocumentTypeModel.getNombres().equalsIgnoreCase("")) {
                edtxtNombreEmpresa.setText(poValidDocumentTypeModel.getApellido_1() + " " +poValidDocumentTypeModel.getApellido_2() + " " + poValidDocumentTypeModel.getNombres());
            }
        } else {
            if (!poValidDocumentTypeModel.getNombres().equalsIgnoreCase("")) {
                edtxtNombres.setText(poValidDocumentTypeModel.getNombres());
            }

            if (!poValidDocumentTypeModel.getApellido_1().equalsIgnoreCase("")) {
                edtxtApellido_1.setText(poValidDocumentTypeModel.getApellido_1());
            }

            if (!poValidDocumentTypeModel.getApellido_2().equalsIgnoreCase("")) {
                edtxtApellido_2.setText(poValidDocumentTypeModel.getApellido_2());
            }
        }
    }
    //endregion

    @OnClick(R.id.btnActualizarDatos)
    public void updateUserInformation() {
        DocumentTypeModel spnDocumentTypeModel = spnTipoDocumento.getSelectedItem() == null ? new DocumentTypeModel() : (DocumentTypeModel) spnTipoDocumento.getSelectedItem();
        ApplicantTypeModel spnApplicantTypeModel = spnTipoSolicitante.getSelectedItem() == null ? new ApplicantTypeModel() : (ApplicantTypeModel) spnTipoSolicitante.getSelectedItem();
        String strApellido1 = spnDocumentTypeModel.getIdTipoDocumento() == ValidationUtil.ID_RUC ? edtxtNombreEmpresa.getText().toString() : edtxtApellido_1.getText().toString();
        GenderPersonModel spnGenderPersonModel = spnSexo.getSelectedItem() == null ? new GenderPersonModel() : (GenderPersonModel) spnSexo.getSelectedItem();
        DistrictModel spnDistrictModel = spnDistrito.getSelectedItem() == null ? new DistrictModel() : (DistrictModel) spnDistrito.getSelectedItem();
        String strAceptNotifi = chkRecNotifica.isChecked() ? "1" : "0";
        String fechaNacimiento = edtxtFechaNacimiento.getText().toString() == null || edtxtFechaNacimiento.getText().toString().equalsIgnoreCase("") ? "" : CommonUtil.getFormatDateValueUpdateUser_yyymmddd(edtxtFechaNacimiento.getText().toString());
        long telefono1 = edtxtTelefono1.getText().toString() == null || edtxtTelefono1.getText().toString().equalsIgnoreCase("") ? 0 : Long.parseLong(edtxtTelefono1.getText().toString());
        long telefono2 = edtxtTelefono2.getText().toString() == null || edtxtTelefono2.getText().toString().equalsIgnoreCase("") ? 0 : Long.parseLong(edtxtTelefono2.getText().toString());

        this.goUpdateDataUserPresenter.initialize(
                goUsuarioLogueado.getIdCliente(),
                spnApplicantTypeModel.getIdTipoSolicitante(),
                spnDocumentTypeModel.getIdTipoDocumento(),
                Long.parseLong(edtxtNumDocumento.getText().toString()),
                strApellido1,
                edtxtApellido_2.getText().toString(),
                edtxtNombres.getText().toString(),
                spnGenderPersonModel.getIdGender(),
                String.valueOf(spnDistrictModel.getCodDist()),
                edtxtDireccion.getText().toString(),
                fechaNacimiento,
                telefono1,
                telefono2,
                strAceptNotifi);
    }

    @Override
    public void showSucessUpdateDataUser(String poMessage) {
        //StringBuilder strbMensaje = new StringBuilder();
//
        //strbMensaje.append(getResources().getString(R.string.lbl_hemos__enviado_correo))
        //        .append(" ")
        //        .append("<b>")
        //        .append(edtxtCorreoActDatos.getText().toString())
        //        .append("</b>")
        //        .append(" ")
        //        .append(getResources().getString(R.string.lbl_con_su_usuario));
        poMessage = poMessage.replace("|"," ");
        showDialogCorrectoNoCloseDialog("",poMessage, getResources().getString(R.string.lbl_volver_pantalla_principal) , R.drawable.ic_correct_dialog, new CallbackDialogCorrecto() {
            @Override
            public void onClickBtnOption() {
                onBackPressed();
            }
        });
    }

    /**
     * VALIDAR TIPO DOCUMENTO
     *
     * @param poValidDocumentTypeModel
     */

    @Override
    public void showSucessValidateDocumentType(ValidDocumentTypeModel poValidDocumentTypeModel) {
        isValidNumDoc = true;
        DocumentTypeModel spnDocumentTypeModel = spnTipoDocumento.getSelectedItem() == null ? new DocumentTypeModel() : (DocumentTypeModel) spnTipoDocumento.getSelectedItem();
        configurarMostrarControlesTipoDocumento(spnDocumentTypeModel.getIdTipoDocumento());
        cargarDatosObtenidosNumDocumento(poValidDocumentTypeModel, spnDocumentTypeModel.getIdTipoDocumento());
    }

    @Override
    public void showErrorMessageDocumentType(String message) {
        showDialogError(message);
        isCorrectNumDocAlert(false);
    }

    /**
     * Obteniendo informacion del usuario
     */

    @Override
    public void showSucessObtainDatauser(ObtainDataUserLoginModel poObtainDataUserLoginModel) {

        if(poObtainDataUserLoginModel.getTipoSoli() != 0){
            spnTipoSolicitante.setSelection(getIndexSpinnerApplicantType(poObtainDataUserLoginModel.getTipoSoli()));
        }

        if(poObtainDataUserLoginModel.getTipoDocu() != 0){
            spnTipoDocumento.setSelection(getIndexSpinnerDocumentType(poObtainDataUserLoginModel.getTipoDocu()));
            isValidDocumentType = true;
        }

        if(poObtainDataUserLoginModel.getSexo() != null)
        if(!poObtainDataUserLoginModel.getSexo().equalsIgnoreCase("")){
            spnSexo.setSelection(getIndexSpinnerGender(poObtainDataUserLoginModel.getSexo()));
        }

        if(poObtainDataUserLoginModel.getDistrito() != null)
        if(!poObtainDataUserLoginModel.getDistrito().equalsIgnoreCase("")){
            spnDistrito.setSelection(getIndexSpinnerDistrict(Integer.parseInt(poObtainDataUserLoginModel.getDistrito())));
        }

        if(!String.valueOf(poObtainDataUserLoginModel.getNroDoc()).equalsIgnoreCase("")) {
            edtxtNumDocumento.setText(String.valueOf(poObtainDataUserLoginModel.getNroDoc()));
            isValidNumDoc = true;
        }

        if(poObtainDataUserLoginModel.getNombres() != null)
        if(!poObtainDataUserLoginModel.getNombres().equalsIgnoreCase("")) {
            edtxtNombres.setText(poObtainDataUserLoginModel.getNombres());
            isValidNames = true;
        }

        if(poObtainDataUserLoginModel.getApellido1() != null)
        if(!poObtainDataUserLoginModel.getApellido1().equalsIgnoreCase("")) {
            edtxtApellido_1.setText(poObtainDataUserLoginModel.getApellido1());
            isValidLastName_1 = true;
        }

        if(poObtainDataUserLoginModel.getApellido2() != null)
        if(!poObtainDataUserLoginModel.getApellido2().equalsIgnoreCase("")) {
            edtxtApellido_2.setText(poObtainDataUserLoginModel.getApellido2());
            isValidLastName_2 = true;
        }

        if(poObtainDataUserLoginModel.getApellido1() != null)
        if(!poObtainDataUserLoginModel.getApellido1().equalsIgnoreCase("")) {
            edtxtNombreEmpresa.setText(poObtainDataUserLoginModel.getApellido1());
            isValidEnterpriseName = true;
        }

        if(poObtainDataUserLoginModel.getDireccion() != null)
        if(!poObtainDataUserLoginModel.getDireccion().equalsIgnoreCase("")) {
            edtxtDireccion.setText(poObtainDataUserLoginModel.getDireccion());
        }

        if(poObtainDataUserLoginModel.getTelefono1() != null)
        if(!poObtainDataUserLoginModel.getTelefono1().equalsIgnoreCase("")) {
            edtxtTelefono1.setText(poObtainDataUserLoginModel.getTelefono1());
            isValidPhone = true;
        }

        if(poObtainDataUserLoginModel.getTelefono2() != null)
        if(!poObtainDataUserLoginModel.getTelefono2().equalsIgnoreCase("")) {
            edtxtTelefono2.setText(poObtainDataUserLoginModel.getTelefono2());
        }

        if(poObtainDataUserLoginModel.getFecha_nac() != null)
        if(!poObtainDataUserLoginModel.getFecha_nac().equalsIgnoreCase("")) {
            edtxtFechaNacimiento.setText(CommonUtil.getFormatDateValue(poObtainDataUserLoginModel.getFecha_nac()));
        }

        if(poObtainDataUserLoginModel.getAceptaNoti() == 1) {
            chkRecNotifica.setChecked(true);
        }else chkRecNotifica.setChecked(false);

        eventControls();

        isPendingValueInformation();

    }


    private int getIndexSpinnerApplicantType(int idItem){
        int position = 0;
        for (int i=0;i<spnTipoSolicitante.getCount()-1;i++){
            ApplicantTypeModel itemPosition = (ApplicantTypeModel)spnTipoSolicitante.getItemAtPosition(i);
            if (itemPosition.getIdTipoSolicitante() == idItem){
                position = i+1;
            }
        }
        return position;
    }

    private int getIndexSpinnerDocumentType(int idItem){
        int position = 0;
        for (int i=0;i<spnTipoDocumento.getCount()-1;i++){
            DocumentTypeModel itemPosition = (DocumentTypeModel)spnTipoDocumento.getItemAtPosition(i);
            if (itemPosition.getIdTipoDocumento() == idItem){
                position = i+1;
            }
        }
        return position;
    }

    private int getIndexSpinnerGender(String idItem){
        int position = 0;
        for (int i=0;i<spnSexo.getCount()-1;i++){
            GenderPersonModel itemPosition = (GenderPersonModel)spnSexo.getItemAtPosition(i);
            if (itemPosition.getIdGender().equalsIgnoreCase(idItem)){
                position = i+1;
            }
        }
        return position;
    }

    private int getIndexSpinnerDistrict(int idItem){
        int position = 0;
        for (int i=0;i<spnDistrito.getCount()-1;i++){
            DistrictModel itemPosition = (DistrictModel)spnDistrito.getItemAtPosition(i);
            if (itemPosition.getCodDist() == idItem){
                position = i+1;
            }
        }
        return position;
    }

    //region SHOW - HIDE PROGRESS DIALOG

    @Override
    public void showLoadingDocumentType() {
        progressLoadDocumentType.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoadingDocumentType() {
        progressLoadDocumentType.setVisibility(View.GONE);
    }

    @Override
    public void showProgessDialogObtainDataUser() {
        scrollScreenContaint.setVisibility(View.INVISIBLE);
        llCircleProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgessDialogObtainDataUser() {
        scrollScreenContaint.setVisibility(View.VISIBLE);
        llCircleProgressBar.setVisibility(View.GONE);
    }

    //endregion

    @Override
    public void showRetry() {

    }

    @Override
    public void hideRetry() {

    }

    @Override
    public void showError(String psMessage) {
        showDialogError(psMessage);
        btnActualizarDatos.setEnabled(false);
    }

    @Override
    public Context context() {
        return this;
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.goUpdateDataUserPresenter.resume();
        this.goApplicantTypePresenter.resume();
        this.goDocumentTypePresenter.resume();
        this.goDistrictPresenter.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        this.goUpdateDataUserPresenter.pause();
        this.goApplicantTypePresenter.pause();
        this.goDocumentTypePresenter.pause();
        this.goDistrictPresenter.pause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.goUpdateDataUserPresenter.destroy();
        this.goApplicantTypePresenter.destroy();
        this.goDocumentTypePresenter.destroy();
        this.goDistrictPresenter.destroy();
    }
}