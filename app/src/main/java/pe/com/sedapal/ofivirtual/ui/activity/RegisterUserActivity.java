package pe.com.sedapal.ofivirtual.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pe.com.sedapal.ofivirtual.R;
import pe.com.sedapal.ofivirtual.di.HasComponent;
import pe.com.sedapal.ofivirtual.di.components.DaggerRegisterUserComponent;
import pe.com.sedapal.ofivirtual.di.components.RegisterUserComponent;
import pe.com.sedapal.ofivirtual.model.DocumentTypeModel;
import pe.com.sedapal.ofivirtual.model.ValidDocumentTypeModel;
import pe.com.sedapal.ofivirtual.navigation.Navigator;
import pe.com.sedapal.ofivirtual.presenter.sedapal.DocumentTypePresenter;
import pe.com.sedapal.ofivirtual.presenter.sedapal.PaymentReferencePresenter;
import pe.com.sedapal.ofivirtual.presenter.sedapal.ValidSupplyPresenter;
import pe.com.sedapal.ofivirtual.presenter.user.RegisterUserPresenter;
import pe.com.sedapal.ofivirtual.presenter.view.DocumentTypeView;
import pe.com.sedapal.ofivirtual.presenter.view.PaymentReferenceView;
import pe.com.sedapal.ofivirtual.presenter.view.RegisterUserView;
import pe.com.sedapal.ofivirtual.presenter.view.ValidSupplyView;
import pe.com.sedapal.ofivirtual.ui.widget.MaterialSpinner;
import pe.com.sedapal.ofivirtual.util.ValidationUtil;

import static pe.com.sedapal.ofivirtual.util.ValidationUtil.esValidoPassword;
import static pe.com.sedapal.ofivirtual.util.ValidationUtil.isValidLong;

public class RegisterUserActivity extends BaseActivity implements HasComponent<RegisterUserComponent>, ValidSupplyView, PaymentReferenceView, RegisterUserView, DocumentTypeView {

    @BindView(R.id.toolbar_logo)
    Toolbar toolbar_logo;
    @BindView(R.id.tilNumSuminsitro)
    TextInputLayout tilNumSuminsitro;
    @BindView(R.id.edtxtNumSuministro)
    EditText edtxtNumSuministro;
    @BindView(R.id.txtDondeEncuentroSuminsitro)
    TextView txtDondeEncuentroSuminsitro;
    @BindView(R.id.tilReferenciaCobro)
    TextInputLayout tilReferenciaCobro;
    @BindView(R.id.edtxtReferenciaCobro)
    EditText edtxtReferenciaCobro;
    @BindView(R.id.txtDondeEncuentroRefCobr)
    TextView txtDondeEncuentroRefCobr;
    @BindView(R.id.spnTipoDocumento)
    MaterialSpinner spnTipoDocumento;
    @BindView(R.id.tilNumDocumento)
    TextInputLayout tilNumDocumento;
    @BindView(R.id.edtxtNumDocumento)
    EditText edtxtNumDocumento;
    @BindView(R.id.tilNombres)
    TextInputLayout tilNombres;
    @BindView(R.id.edtxtNombres)
    EditText edtxtNombres;
    @BindView(R.id.tilApellido_1)
    TextInputLayout tilApellido_1;
    @BindView(R.id.edtxtApellido_1)
    EditText edtxtApellido_1;
    @BindView(R.id.tilApellido_2)
    TextInputLayout tilApellido_2;
    @BindView(R.id.edtxtApellido_2)
    EditText edtxtApellido_2;
    @BindView(R.id.tilNombreEmpresa)
    TextInputLayout tilNombreEmpresa;
    @BindView(R.id.edtxtNombreEmpresa)
    EditText edtxtNombreEmpresa;
    @BindView(R.id.tilCorreo)
    TextInputLayout tilCorreo;
    @BindView(R.id.edtxtCorreo)
    EditText edtxtCorreo;
    @BindView(R.id.tilTelefono)
    TextInputLayout tilTelefono;
    @BindView(R.id.edtxtTelefono)
    EditText edtxtTelefono;
    @BindView(R.id.tilContrasenia)
    TextInputLayout tilContrasenia;
    @BindView(R.id.edtxtContrasenia)
    EditText edtxtContrasenia;
    @BindView(R.id.txtValidadorContrasenia)
    TextView txtValidadorContrasenia;
    @BindView(R.id.tilRepetirContrasenia)
    TextInputLayout tilRepetirContrasenia;
    @BindView(R.id.edtxtRepetirContrasenia)
    EditText edtxtRepetirContrasenia;
    @BindView(R.id.txtValidadorRepetirContrasenia)
    TextView txtValidadorRepetirContrasenia;
    @BindView(R.id.chkTerminosCondiciones)
    CheckBox chkTerminosCondiciones;
    @BindView(R.id.txtTerminosCondiciones)
    TextView txtTerminosCondiciones;
    @BindView(R.id.chkRecNotifica)
    CheckBox chkRecNotifica;
    @BindView(R.id.btnRegistrarme)
    Button btnRegistrarme;

    @BindView(R.id.progressLoadSupply)
    ProgressBar progressLoadSupply;
    @BindView(R.id.progressLoadPayReference)
    ProgressBar progressLoadPayReference;
    @BindView(R.id.progressLoadDocumentType)
    ProgressBar progressLoadDocumentType;

    public static Intent getCallingIntent(Context poContext) {
        Intent loIntent = new Intent(poContext, RegisterUserActivity.class);
        return loIntent;
    }

    private RegisterUserComponent goRegisterUserComponent;

    @Inject
    RegisterUserPresenter goRegisterUserPresenter;

    @Inject
    ValidSupplyPresenter goValidSupplyPresenter;

    @Inject
    PaymentReferencePresenter goPaymentReferencePresenter;

    @Inject
    DocumentTypePresenter goDocumentTypePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
        ButterKnife.bind(this);
        initializeInjector();
        setUpView();
        //initiliaze();
    }

    private void configToolbar() {
        setSupportActionBar(toolbar_logo);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar_logo.setNavigationIcon(R.drawable.ic_back_toolbar);
    }

    @Override
    public RegisterUserComponent getComponent() {
        return this.goRegisterUserComponent;
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
        this.goRegisterUserComponent = DaggerRegisterUserComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build();
        this.goRegisterUserComponent.inject(RegisterUserActivity.this);
    }

    private void setUpView() {
        this.goDocumentTypePresenter.setView(this);
        this.goRegisterUserPresenter.setView(this);
        this.goValidSupplyPresenter.setView(this);
        this.goPaymentReferencePresenter.setView(this);

        configToolbar();
        loadControls();
        configControls();
        eventControls();
    }

    //region Configuracion de controles

    /**
     * Configuracion de controles
     */
    public void loadControls() {
        goDocumentTypePresenter.obtainListDocumentType();
    }

    private void configControls() {
        edtxtNumSuministro.setFilters(new InputFilter[]{new InputFilter.LengthFilter(ValidationUtil.CANTIDAD_CARACTERES_SUMINSITRO)});
        edtxtReferenciaCobro.setFilters(new InputFilter[]{new InputFilter.LengthFilter(ValidationUtil.CANTIDAD_CARACTERES_REFERENCIA_COBRO)});
        edtxtContrasenia.setFilters(new InputFilter[]{new InputFilter.LengthFilter(ValidationUtil.CANTIDAD_CARACTERES_MAX_PASSWORD)});
        edtxtRepetirContrasenia.setFilters(new InputFilter[]{new InputFilter.LengthFilter(ValidationUtil.CANTIDAD_CARACTERES_MAX_PASSWORD)});
        edtxtTelefono.setFilters(new InputFilter[]{new InputFilter.LengthFilter(ValidationUtil.CANTIDAD_CARACTERES_TELEFONO)});

        //Ocultar campos de la informacion del usuario cuando se ingresa a la pantalla por primera vez
        tilNombres.setVisibility(View.GONE);
        tilApellido_1.setVisibility(View.GONE);
        tilApellido_2.setVisibility(View.GONE);
        tilTelefono.setVisibility(View.GONE);

        btnRegistrarme.setEnabled(false);

        disableControls(false);
    }
    //endregion

    //region VALIDATE CONTROLS AFTER EVENT
    /**
     * Validar campos al ingresar password
     */
    boolean isValidNumberSupply = false;
    boolean isValidPayReference = false;
    boolean isValidPassword = false;
    boolean isValidDocumentType = false;
    boolean isValidNumDoc = false;
    boolean isValidMail = false;
    boolean isTermsCondi = false;
    boolean isReciveNotifi = false;

    boolean isValidNames = false;
    boolean isValidLastName_1 = false;
    boolean isValidLastName_2 = false;
    boolean isValidEnterpriseName = false;
    boolean isValidPhone = false;


    public void disableControls(boolean isEnabled){
        edtxtReferenciaCobro.setEnabled(isEnabled);
        txtDondeEncuentroRefCobr.setEnabled(isEnabled);
        spnTipoDocumento.setEnabled(isEnabled);
        edtxtNumDocumento.setEnabled(isEnabled);
        edtxtCorreo.setEnabled(isEnabled);
        edtxtContrasenia.setEnabled(isEnabled);
        edtxtRepetirContrasenia.setEnabled(isEnabled);
        txtTerminosCondiciones.setEnabled(isEnabled);
        chkTerminosCondiciones.setEnabled(isEnabled);
        chkRecNotifica.setEnabled(isEnabled);

        /**
         * Al enviarle false limpia los controles y desactivara las alertas de error
         */
        if(!isEnabled){
            edtxtReferenciaCobro.getText().clear();
            spnTipoDocumento.setSelection(0);
            edtxtNumDocumento.getText().clear();
            edtxtNumDocumento.setEnabled(false);
            edtxtCorreo.getText().clear();
            edtxtContrasenia.getText().clear();
            edtxtRepetirContrasenia.getText().clear();
            chkTerminosCondiciones.setChecked(false);
            chkRecNotifica.setChecked(false);

            tilReferenciaCobro.setError(null);
            tilNumDocumento.setError(null);
            tilCorreo.setError(null);
            tilReferenciaCobro.setErrorEnabled(false);
            tilNumDocumento.setErrorEnabled(false);
            tilCorreo.setErrorEnabled(false);

            txtValidadorContrasenia.setTextColor(getResources().getColor(R.color.text_color_secondary));
            txtValidadorRepetirContrasenia.setTextColor(getResources().getColor(R.color.text_color_secondary));
        }
    }

    public void enableDisableControlsAfterValidatePaymentRef(boolean isEnabled){
        spnTipoDocumento.setEnabled(isEnabled);
        edtxtTelefono.setEnabled(isEnabled);
        edtxtCorreo.setEnabled(isEnabled);
        edtxtContrasenia.setEnabled(isEnabled);
        edtxtRepetirContrasenia.setEnabled(isEnabled);
        txtTerminosCondiciones.setEnabled(isEnabled);
        chkTerminosCondiciones.setEnabled(isEnabled);
        chkRecNotifica.setEnabled(isEnabled);

        if(!isEnabled){
            spnTipoDocumento.setSelection(0);
            edtxtNumDocumento.getText().clear();
            edtxtNumDocumento.setEnabled(false);
            edtxtCorreo.getText().clear();
            edtxtContrasenia.getText().clear();
            edtxtRepetirContrasenia.getText().clear();
            chkTerminosCondiciones.setChecked(false);
            chkRecNotifica.setChecked(false);

            tilNumDocumento.setError(null);
            tilNumSuminsitro.setError(null);
            tilCorreo.setError(null);
            tilNumDocumento.setErrorEnabled(false);
            tilNumSuminsitro.setErrorEnabled(false);
            tilCorreo.setErrorEnabled(false);

            txtValidadorContrasenia.setTextColor(getResources().getColor(R.color.text_color_secondary));
            txtValidadorRepetirContrasenia.setTextColor(getResources().getColor(R.color.text_color_secondary));
        }
    }

    public void habPasswordCorrecto() {
        txtValidadorContrasenia.setTextColor(getResources().getColor(R.color.text_color_secondary));
        edtxtRepetirContrasenia.setEnabled(true);
    }

    public void desaPasswordError() {
        txtValidadorContrasenia.setTextColor(getResources().getColor(R.color.error_color));
        edtxtRepetirContrasenia.setEnabled(false);
        edtxtRepetirContrasenia.getText().clear();
    }

    public void habRepetirPasswordCorrecto() {
        isValidPassword = true;
        txtValidadorRepetirContrasenia.setTextColor(getResources().getColor(R.color.text_color_secondary));
    }

    public void desaRepetirPasswordError() {
        isValidPassword = false;
        txtValidadorRepetirContrasenia.setTextColor(getResources().getColor(R.color.error_color));
    }

    /**
     * Validando campos al seleccionar el tipo de documento, mostrando la informacion
     * del tipo de documento
     */
    public void desaTipoDocumento() {
        edtxtNombreEmpresa.getText().clear();
        edtxtNombres.getText().clear();
        edtxtApellido_1.getText().clear();
        edtxtApellido_2.getText().clear();
        edtxtTelefono.getText().clear();

        tilNombreEmpresa.setError(null);
        tilNombreEmpresa.setErrorEnabled(false);
        tilNombres.setError(null);
        tilNombres.setErrorEnabled(false);
        tilApellido_1.setError(null);
        tilApellido_1.setErrorEnabled(false);
        tilApellido_2.setError(null);
        tilApellido_2.setErrorEnabled(false);
        tilTelefono.setError(null);
        tilTelefono.setErrorEnabled(false);

        tilNombreEmpresa.setVisibility(View.GONE);
        tilNombres.setVisibility(View.GONE);
        tilApellido_1.setVisibility(View.GONE);
        tilApellido_2.setVisibility(View.GONE);
        tilTelefono.setVisibility(View.GONE);
    }

    public void desaTipoDocumentoSelectCombobox(){
        if(getCurrentFocus() != null) {
            getCurrentFocus().clearFocus();
        }
        edtxtNumDocumento.getText().clear();

        tilNumDocumento.setErrorEnabled(false);
        tilNumDocumento.setError(null);

        edtxtNumDocumento.setEnabled(false);
    }

    //endregion

    //region EVENT CONTROLS

    private void isCorrectSupplyAlert(boolean response) {
        if (response) {
            edtxtReferenciaCobro.setEnabled(true);
            txtDondeEncuentroRefCobr.setEnabled(true);

            tilNumSuminsitro.setErrorEnabled(false);
            tilNumSuminsitro.setError(null);
        } else {
            edtxtReferenciaCobro.setEnabled(false);
            txtDondeEncuentroRefCobr.setEnabled(false);

            tilNumSuminsitro.setErrorEnabled(true);
            tilNumSuminsitro.setError(getResources().getString(R.string.lbl_error_til_number_supply));
            isValidNumberSupply = false;

            disableControls(false);
        }
    }

    private void isCorrectPaymentReferenceAlert(boolean response) {
        if (response) {
            tilReferenciaCobro.setErrorEnabled(false);
            tilReferenciaCobro.setError(null);

            enableDisableControlsAfterValidatePaymentRef(true);
        } else {
            tilReferenciaCobro.setErrorEnabled(true);
            tilReferenciaCobro.setError(getResources().getString(R.string.lbl_error_til_pay_reference));
            isValidPayReference = false;

            enableDisableControlsAfterValidatePaymentRef(false);
        }
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


    private void eventControls() {

        edtxtNumSuministro.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == ValidationUtil.CANTIDAD_CARACTERES_SUMINSITRO) {
                    hideKeyboard();
                    goValidSupplyPresenter.initialize(edtxtNumSuministro.getText().toString());

                    isCorrectSupplyAlert(true);
                } else {
                    isCorrectSupplyAlert(false);
                }

                isPendingValueInformation();
            }
        });

        edtxtReferenciaCobro.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == ValidationUtil.CANTIDAD_CARACTERES_REFERENCIA_COBRO) {
                    hideKeyboard();

                    goPaymentReferencePresenter.initialize(
                            isValidLong(edtxtNumSuministro.getText().toString()),
                            isValidLong(edtxtReferenciaCobro.getText().toString()));
                    isCorrectPaymentReferenceAlert(true);

                } else {
                    isCorrectPaymentReferenceAlert(false);
                }

                isPendingValueInformation();
            }
        });

        spnTipoDocumento.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                desaTipoDocumento();
                desaTipoDocumentoSelectCombobox();

                DocumentTypeModel tipoDocumentoSelecc = (DocumentTypeModel) parent.getSelectedItem();
                if (tipoDocumentoSelecc != null) {
                    configurarCantidadDigitosNumDocumento(tipoDocumentoSelecc.getIdTipoDocumento());

                    isValidDocumentType = true;
                    edtxtNumDocumento.setEnabled(true);
                } else {

                    isValidDocumentType = false;
                    edtxtNumDocumento.setEnabled(false);
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
                    goDocumentTypePresenter.validateTypeDocument(edtxtNumSuministro.getText().toString().trim().equals("")? 0: Integer.parseInt(edtxtNumSuministro.getText().toString()),spnDocumentTypeModel.getIdTipoDocumento(), edtxtNumDocumento.getText().toString());
                } else {
                    isCorrectNumDocAlert(false);
                }

                isPendingValueInformation();
            }
        });

        edtxtCorreo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (ValidationUtil.esValidoCorreo(edtxtCorreo.getText().toString())) {
                    tilCorreo.setErrorEnabled(false);
                    tilCorreo.setError(null);

                    isValidMail = true;
                } else {
                    tilCorreo.setErrorEnabled(true);
                    tilCorreo.setError(getResources().getString(R.string.lbl_error_correo));

                    isValidMail = false;
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

        edtxtTelefono.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (ValidationUtil.esValidoNumeroTelefono(edtxtTelefono.getText().toString())) {
                    tilTelefono.setErrorEnabled(false);
                    tilTelefono.setError(null);

                    isValidPhone = true;
                } else {
                    tilTelefono.setErrorEnabled(true);
                    tilTelefono.setError(getResources().getString(R.string.lbl_error_til_telefono));

                    isValidPhone = false;
                }

                isPendingValueInformation();
            }
        });

        edtxtContrasenia.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!edtxtContrasenia.getText().toString().equalsIgnoreCase("")) {
                    if (s.length() > 8 && esValidoPassword(edtxtContrasenia.getText().toString())) {
                        habPasswordCorrecto();
                    } else {
                        desaPasswordError();
                    }
                }

                isPendingValueInformation();
            }
        });

        edtxtRepetirContrasenia.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!edtxtContrasenia.getText().toString().equalsIgnoreCase("")) {
                    if (edtxtContrasenia.getText().toString().equalsIgnoreCase(edtxtRepetirContrasenia.getText().toString())) {
                        habRepetirPasswordCorrecto();
                    } else {
                        desaRepetirPasswordError();
                    }
                }

                isPendingValueInformation();
            }
        });

        chkTerminosCondiciones.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    isTermsCondi = true;
                } else {
                    isTermsCondi = false;
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

                //isPendingValueInformation();
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
    }

    public void imeOptionsKeyboard(){
        edtxtReferenciaCobro.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_NEXT) {
                    goValidSupplyPresenter.initialize(edtxtNumSuministro.getText().toString());
                }
                return false;
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
        tilTelefono.setVisibility(View.VISIBLE);
    }

    public void enableVisibilityUserInformationEnterprise() {
        tilNombreEmpresa.setVisibility(View.VISIBLE);
        tilNombres.setVisibility(View.GONE);
        tilApellido_1.setVisibility(View.GONE);
        tilApellido_2.setVisibility(View.GONE);
        tilTelefono.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.txtDondeEncuentroSuminsitro)
    public void infoDondeEncuentroSuministro() {
        showDialogEventImage(R.mipmap.img_donde_enc_suministro);
    }

    @OnClick(R.id.txtDondeEncuentroRefCobr)
    public void infoDondeEncuentroReferencia() {
        showDialogEventImage(R.mipmap.img_donde_enc_ref_cobro);
    }
    //endregion

    //region VALUE IF CONTROLS ALL COMPLETE
    public void isPendingValueInformation() {
        if (isValidInformation()) {
            btnRegistrarme.setEnabled(true);
        } else btnRegistrarme.setEnabled(false);

    }

    public boolean isValidInformation() {

        if (!isValidNumberSupply) {
            return false;
        }

        if (!isValidPayReference) {
            return false;
        }

        if (!isValidDocumentType) {
            return false;
        }

        if (!isValidNumDoc) {
            return false;
        }


        if (!isValidMail) {
            return false;
        }

        if (!isValidPhone) {
            return false;
        }

        if (!isValidPassword) {
            return false;
        }

        if (!isTermsCondi) {
            return false;
        }

        //if (!isReciveNotifi) {
        //    return false;
        //}

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
        edtxtNumDocumento.getText().clear();
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
            edtxtTelefono.setText(poValidDocumentTypeModel.getTelefono_1());
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

    @OnClick(R.id.btnRegistrarme)
    public void registerUserInformation() {
        DocumentTypeModel spnDocumentTypeModel = spnTipoDocumento.getSelectedItem() == null ? new DocumentTypeModel() : (DocumentTypeModel) spnTipoDocumento.getSelectedItem();
        String strApellido1 = spnDocumentTypeModel.getIdTipoDocumento() == ValidationUtil.ID_RUC ? edtxtNombreEmpresa.getText().toString() : edtxtApellido_1.getText().toString();

        goRegisterUserPresenter.initialize(
                isValidLong(edtxtNumSuministro.getText().toString()),
                isValidLong(edtxtReferenciaCobro.getText().toString()),
                spnDocumentTypeModel.getIdTipoDocumento(),
                edtxtNumDocumento.getText().toString(),
                strApellido1,
                edtxtApellido_2.getText().toString(),
                edtxtNombres.getText().toString(),
                edtxtCorreo.getText().toString(),
                isValidLong(edtxtTelefono.getText().toString()),
                1,
                isReciveNotifi ? 1 : 0,
                edtxtContrasenia.getText().toString()
        );
    }

    @OnClick(R.id.txtTerminosCondiciones)
    public void mostrarTerminosCondiciones() {
        navigator.navigateToTerminosCondiciones(this, Navigator.REQUEST_TERM_CONDIC);
    }

    //region WEBSERVICES RESPONSE
    @Override
    public void showSucessRegisterUser(String poMessage) {
        navigator.navigateToRegisterUserSuccessActivity(this, poMessage, edtxtCorreo.getText().toString());
    }

    @Override
    public void showSucessListDocumentType(List<DocumentTypeModel> poListDocumentType) {
        ArrayAdapter<DocumentTypeModel> adapterTipoDocumento = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, poListDocumentType);
        adapterTipoDocumento.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        spnTipoDocumento.setAdapter(adapterTipoDocumento);
        spnTipoDocumento.setPaddingSafe(0, 0, 0, 0);
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

    /**
     * VALIDAR SUMINISTRO
     *
     * @param message
     */
    @Override
    public void showSucessValidSupply(String message) {
        isValidNumberSupply = true;
        isCorrectSupplyAlert(true);
        showToastCorrect(message);
    }

    /**
     * VALIDAR REFERENCIA DE COBRO
     *
     * @param poMessage
     */
    @Override
    public void showSucessValidPaymentReference(String poMessage) {
        isValidPayReference = true;
        showToastCorrect(poMessage);

        edtxtNumSuministro.setEnabled(true);
    }

    @Override
    public void showErrorMessageSupply(String message) {
        showDialogError(message);
        isCorrectSupplyAlert(false);
    }

    @Override
    public void showErrorMessagePayReference(String message) {
        showDialogError(message);
        isCorrectPaymentReferenceAlert(false);
    }

    @Override
    public void showErrorMessageDocumentType(String message) {
        showDialogError(message);
        isCorrectNumDocAlert(false);
    }
    //endregion

    //region SHOW - HIDE PROGRESS DIALOG

    @Override
    public void showLoadingSupply() {
        progressLoadSupply.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoadingSupply() {
        progressLoadSupply.setVisibility(View.GONE);
    }

    @Override
    public void showLoadingPaymentReference() {
        progressLoadPayReference.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoadingPaymentReference() {
        progressLoadPayReference.setVisibility(View.GONE);
    }

    @Override
    public void showLoadingDocumentType() {
        progressLoadDocumentType.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoadingDocumentType() {
        progressLoadDocumentType.setVisibility(View.GONE);
    }

    //region Navigation
    public void navigateToLogin() {
        navigator.navigateToLogin(RegisterUserActivity.this);
    }

    //endregion

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Navigator.REQUEST_TERM_CONDIC) {
            if (resultCode == RESULT_OK) {
                chkTerminosCondiciones.setChecked(true);
            } else {
                chkTerminosCondiciones.setChecked(false);
            }
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
        showDialogError(psMessage);
    }

    @Override
    public Context context() {
        return this;
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.goDocumentTypePresenter.resume();
        this.goRegisterUserPresenter.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        this.goDocumentTypePresenter.pause();
        this.goRegisterUserPresenter.pause();
        //unRegisterReceiver();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.goDocumentTypePresenter.destroy();
        this.goRegisterUserPresenter.destroy();
    }

    /**
     * EJEMPLO INICIAR SERVICE IN BACKGROUND
     */
    /*private BroadcastReceiver goSyncReceiver;
    private boolean gbSyncCompleted = true;

    private void invokeBroadcast() {
        RegisterUserActivity.this.navigator.startSyncService(context());
        RegisterUserActivity.this.broadcastSync();
    }

    private void broadcastSync() {
        IntentFilter loIntentFilter = new IntentFilter(
                getString(R.string.broadcast_sync_completed));
        this.goSyncReceiver = new BroadcastReceiver() {

            @Override
            public void onReceive(Context context, Intent intent) {
                //extract our message from intent
                LogUtil.i("MuLTIPAY", "receiver sync");
                try {
                    RegisterUserActivity.this.unregisterReceiver(RegisterUserActivity.this.goSyncReceiver);
                } catch (Exception poException) {

                }
                gbSyncCompleted = intent.getBooleanExtra(getString(R.string.broadcast_success), false);
                if (gbSyncCompleted) {
                    Toast.makeText(getApplicationContext(),"Envio correcto truee",Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(),"Envio erroneo falsee",Toast.LENGTH_LONG).show();
                }
            }
        };
        this.registerReceiver(goSyncReceiver, loIntentFilter);
    }

    private void initiliaze() {
        invokeBroadcast();
    }

    private void unRegisterReceiver() {
        if (RegisterUserActivity.this.goSyncReceiver != null) {
            try {
                RegisterUserActivity.this.unregisterReceiver(RegisterUserActivity.this.goSyncReceiver);
            } catch (Exception poException) {

            }
        }
    }*/
}