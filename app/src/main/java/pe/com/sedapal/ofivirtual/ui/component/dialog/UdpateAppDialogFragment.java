package pe.com.sedapal.ofivirtual.ui.component.dialog;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Html;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import pe.com.sedapal.ofivirtual.R;
import pe.com.sedapal.ofivirtual.util.ValidationUtil;

public class UdpateAppDialogFragment extends DialogFragment implements View.OnClickListener {

    ImageView imgCerrarDialog;
    ImageView imgMostrarImagenDialog;
    LinearLayout llDialogContenido;
    LinearLayout llRegistrate;
    TextView txtDialogTitulo;
    TextView txtDialogDescripcion;
    TextView txtContinue;
    Button btnUpdate;
    ImageView imgIconDialog;

    OnClickCallbackDialogEventos callbackDialog;
    RelativeLayout rlMostrarImagen;

    private static String ID_IMG_CONTENIDO = "IMG_IMAGEN_MOSTRAR";
    private static String ID_OPTIONALLY = "ID_OPTIONALLY";
    private static String ID_TITULO = "ID_TITULO";
    private static String ID_DESCRIPCION = "ID_DESCRIPCION";
    private static String ID_BUTTON = "ID_BUTTON";
    private static String ID_OPCION = "ID_OPCION";
    private static String ID_IS_ANIMATED = "ID_IS_ANIMATED";

    boolean optionally;
    String strTitulo;
    String strDescripcion;
    String strButton;
    String strOpcion;
    boolean isAnimated;

    public interface OnClickCallbackDialogEventos {
        void onClickButtonUpdateAppDialog();

        void onClickContinueUpdateAppDialog();
    }

    public static UdpateAppDialogFragment newInstance(boolean optionally,
                                                      String titulo,
                                                      String descripcion,
                                                      String txtButton,
                                                      String txtOpcionInf,
                                                      boolean isAnimated) {
        UdpateAppDialogFragment frag = new UdpateAppDialogFragment();
        Bundle args = new Bundle();
        args.putBoolean(ID_OPTIONALLY, optionally);
        args.putString(ID_TITULO, titulo);
        args.putString(ID_DESCRIPCION, descripcion);
        args.putString(ID_BUTTON, txtButton);
        args.putString(ID_OPCION, txtOpcionInf);
        args.putBoolean(ID_IS_ANIMATED, isAnimated);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        callbackDialog = (UdpateAppDialogFragment.OnClickCallbackDialogEventos) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final Context contextThemeWrapper = new ContextThemeWrapper(getActivity(), R.style.AppCompatAlertDialogFragmentStyle);
        LayoutInflater localInflater = inflater.cloneInContext(contextThemeWrapper);
        View view = localInflater.inflate(R.layout.fragment_update_app_dialog, container);

        optionally = getArguments().getBoolean(ID_OPTIONALLY, false);
        strTitulo = getArguments().getString(ID_TITULO, "");
        strDescripcion = getArguments().getString(ID_DESCRIPCION, "");
        strButton = getArguments().getString(ID_BUTTON, "");
        strOpcion = getArguments().getString(ID_OPCION, "");
        isAnimated = getArguments().getBoolean(ID_IS_ANIMATED, true);

        imgIconDialog = view.findViewById(R.id.imgIconDialog);
        rlMostrarImagen = view.findViewById(R.id.rlMostrarImagen);
        imgCerrarDialog = view.findViewById(R.id.imgCerrarDialog);
        imgMostrarImagenDialog = view.findViewById(R.id.imgMostrarImagenDialog);
        llDialogContenido = view.findViewById(R.id.llDialogContenido);

        /**
         * Para mostrar un dialogo que contenga algún mensaje con evento
         */
        txtDialogTitulo = view.findViewById(R.id.txtDialogTitulo);
        txtDialogDescripcion = view.findViewById(R.id.txtDialogDescripcion);
        llRegistrate = view.findViewById(R.id.llRegistrate);

        /**
         * Mostrar un diálogo que contenga una opción de registro y reintento
         */
        btnUpdate = view.findViewById(R.id.btnUpdate);
        txtContinue = view.findViewById(R.id.txtContinue);


        imgCerrarDialog.setOnClickListener(this);
        btnUpdate.setOnClickListener(this);
        txtContinue.setOnClickListener(this);

        if (getDialog() != null && getDialog().getWindow() != null) {
            getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
            if (isAnimated) {
                getDialog().getWindow().getAttributes().windowAnimations = R.style.DialogInput;
            }
        }

        generarDialogo();
        return view;
    }

    public void generarDialogo() {
        imgIconDialog.setImageResource(R.drawable.ic_incidents_operat);

        if (!strTitulo.equalsIgnoreCase("")) {
            txtDialogTitulo.setText(strTitulo);
        } else {
            txtDialogTitulo.setVisibility(View.GONE);
        }

        if (!strDescripcion.equalsIgnoreCase("")) {
            if (ValidationUtil.isHtml(strDescripcion)) {
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                    txtDialogDescripcion.setText(Html.fromHtml(strDescripcion, Html.FROM_HTML_MODE_LEGACY));
                } else {
                    txtDialogDescripcion.setText(Html.fromHtml(strDescripcion));
                }
            } else {
                txtDialogDescripcion.setText(strDescripcion);
            }
        } else {
            txtDialogDescripcion.setVisibility(View.GONE);
        }

        /**
         * Si tengo que realizar un evento con registro
         */
        btnUpdate.setText(strButton);
        txtContinue.setText(strOpcion);
        imgCerrarDialog.setVisibility(View.INVISIBLE);

        if (optionally) {
            txtContinue.setVisibility(View.VISIBLE);
        } else {
            txtContinue.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnUpdate) {
            callbackDialog.onClickButtonUpdateAppDialog();
        }

        if (v.getId() == R.id.txtContinue) {
            callbackDialog.onClickContinueUpdateAppDialog();
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

}
