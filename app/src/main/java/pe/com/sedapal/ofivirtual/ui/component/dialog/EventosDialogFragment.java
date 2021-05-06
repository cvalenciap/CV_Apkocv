package pe.com.sedapal.ofivirtual.ui.component.dialog;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Html;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
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
import pe.com.sedapal.ofivirtual.util.ImageUtil;
import pe.com.sedapal.ofivirtual.util.ValidationUtil;

public class EventosDialogFragment extends DialogFragment implements View.OnClickListener {

    ImageView imgCerrarDialog;
    ImageView imgMostrarImagenDialog;
    LinearLayout llCircleProgressBar;
    LinearLayout llDialogContenido;
    LinearLayout llRegistrate;
    TextView txtDialogTitulo;
    TextView txtDialogDescripcion;
    TextView txtDialogOpcion;
    TextView txtDialogIntentarNuevamente;
    Button btnDialogRegistrate;
    ImageView imgIconDialog;


    OnClickCallbackDialogEventos callbackDialog;
    RelativeLayout rlMostrarImagen;

    private static String ID_IMG_CONTENIDO = "IMG_IMAGEN_MOSTRAR";
    private static String ID_ES_ERROR = "ES_ERROR";
    private static String ID_TIENE_REGISTRO = "ID_TIENE_REGISTRO";
    private static String ID_TITULO = "ID_TITULO";
    private static String ID_DESCRIPCION = "ID_DESCRIPCION";
    private static String ID_OPCION = "ID_OPCION";
    private static String ID_ICONO_DIALOG = "ID_ICONO_DIALOG";
    private static String ID_END_SESSION ="ID_END_SESSION";
    private static String ID_IS_ANIMATED = "ID_IS_ANIMATED";

    int imagenContenidoRecibido;
    boolean esError;
    boolean tengoRegistro;
    boolean isEndSession;
    boolean isAnimated;
    String strTitulo;
    String strDescripcion;
    String strOpcion;
    int intIconoDialog;

    public interface OnClickCallbackDialogEventos{
        void onClickCloseDialogEventos();
        void onClickOptionDialog();
        void onClickOptionDialogError();

        void onClickDialogBtnRegistro();
        void onClickOptionDialogIntentarNuevamente();
        void onClickCloseDialogEndSession();
    }

    public static EventosDialogFragment newInstance(int imagen,
                                                    boolean isError,
                                                    boolean tieneRegistro,
                                                    String titulo,
                                                    String descripcion,
                                                    String txtOpcionInf,
                                                    int iconoDialog,
                                                    boolean isEndSession,
                                                    boolean isAnimated) {
        EventosDialogFragment frag = new EventosDialogFragment();
        Bundle args = new Bundle();
        args.putInt(ID_IMG_CONTENIDO, imagen);
        args.putBoolean(ID_ES_ERROR, isError);
        args.putBoolean(ID_TIENE_REGISTRO, tieneRegistro);
        args.putString(ID_TITULO, titulo);
        args.putString(ID_DESCRIPCION, descripcion);
        args.putString(ID_OPCION, txtOpcionInf);
        args.putInt(ID_ICONO_DIALOG, iconoDialog);
        args.putBoolean(ID_END_SESSION, isEndSession);
        args.putBoolean(ID_IS_ANIMATED,isAnimated);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        callbackDialog = (EventosDialogFragment.OnClickCallbackDialogEventos) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final Context contextThemeWrapper = new ContextThemeWrapper(getActivity(), R.style.AppCompatAlertDialogFragmentStyle);
        LayoutInflater localInflater = inflater.cloneInContext(contextThemeWrapper);
        View view = localInflater.inflate(R.layout.fragment_mensajes_dialog, container);

        imagenContenidoRecibido = getArguments().getInt(ID_IMG_CONTENIDO,0);
        esError = getArguments().getBoolean(ID_ES_ERROR,false);
        tengoRegistro = getArguments().getBoolean(ID_TIENE_REGISTRO,false);
        strTitulo = getArguments().getString(ID_TITULO , "");
        strDescripcion = getArguments().getString(ID_DESCRIPCION,"");
        strOpcion = getArguments().getString(ID_OPCION,"");
        intIconoDialog = getArguments().getInt(ID_ICONO_DIALOG,0);
        isEndSession = getArguments().getBoolean(ID_END_SESSION,false);
        isAnimated = getArguments().getBoolean(ID_IS_ANIMATED,true);

        imgIconDialog = view.findViewById(R.id.imgIconDialog);
        rlMostrarImagen = view.findViewById(R.id.rlMostrarImagen);
        imgCerrarDialog = view.findViewById(R.id.imgCerrarDialog);
        imgMostrarImagenDialog = view.findViewById(R.id.imgMostrarImagenDialog);
        llCircleProgressBar = view.findViewById(R.id.llCircleProgressBar);
        llDialogContenido = view.findViewById(R.id.llDialogContenido);

        /**
         * Para mostrar un dialogo que contenga algún mensaje con evento
         */
        txtDialogTitulo = view.findViewById(R.id.txtDialogTitulo);
        txtDialogDescripcion = view.findViewById(R.id.txtDialogDescripcion);
        txtDialogOpcion = view.findViewById(R.id.txtDialogOpcion);
        llRegistrate = view.findViewById(R.id.llRegistrate);

        /**
         * Mostrar un diálogo que contenga una opción de registro y reintento
         */
        btnDialogRegistrate = view.findViewById(R.id.btnDialogRegistrate);
        txtDialogIntentarNuevamente = view.findViewById(R.id.txtDialogIntentarNuevamente);


        imgCerrarDialog.setOnClickListener(this);
        txtDialogOpcion.setOnClickListener(this);
        btnDialogRegistrate.setOnClickListener(this);
        txtDialogIntentarNuevamente.setOnClickListener(this);

        if (getDialog() != null && getDialog().getWindow() != null) {
            getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
            if(isAnimated) {
                getDialog().getWindow().getAttributes().windowAnimations = R.style.DialogInput;
            }
        }

        generarDialogo();
        return view;
    }

    public void generarDialogo(){
        if(isEndSession){
            imgCerrarDialog.setVisibility(View.INVISIBLE);
        }
        /**En caso se tenga una imagen a mostrar en el dialogo
         *
         */
        if(imagenContenidoRecibido != 0){
            llRegistrate.setVisibility(View.GONE);
            llDialogContenido.setVisibility(View.GONE);

            ImageUtil.setGlideImagen(getContext(), imagenContenidoRecibido, imgMostrarImagenDialog, new ImageUtil.callbackImage() {
                @Override
                public void onSucess() {
                    llCircleProgressBar.setVisibility(View.GONE);
                }

                @Override
                public void onError() {
                    llCircleProgressBar.setVisibility(View.GONE);
                }
            });
        }else {
            /**
             * Si no tengo que realizar un evento con registro
             */
            if (intIconoDialog == 0) {
                if (!esError) {
                    imgIconDialog.setImageResource(R.drawable.ic_correct_dialog);
                }
            } else {
                imgIconDialog.setImageResource(intIconoDialog);
            }

            if (!strTitulo.equalsIgnoreCase("")) {
                txtDialogTitulo.setText(strTitulo);
            } else {
                txtDialogTitulo.setVisibility(View.GONE);
            }

            if (!strDescripcion.equalsIgnoreCase("")) {
                if (ValidationUtil.isHtml(strDescripcion)){
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                        txtDialogDescripcion.setText(Html.fromHtml(strDescripcion,Html.FROM_HTML_MODE_LEGACY));
                    } else {
                        txtDialogDescripcion.setText(Html.fromHtml(strDescripcion));
                    }
                }else {
                    txtDialogDescripcion.setText(strDescripcion);
                }
            } else {
                txtDialogDescripcion.setVisibility(View.GONE);
            }

            if (!tengoRegistro) {
                llCircleProgressBar.setVisibility(View.GONE);
                llRegistrate.setVisibility(View.GONE);
                rlMostrarImagen.setVisibility(View.GONE);

                if(!strOpcion.equalsIgnoreCase("")) {
                    SpannableString content = new SpannableString(strOpcion);
                    content.setSpan(new UnderlineSpan(), 0, strOpcion.length(), 0);
                    txtDialogOpcion.setText(content);
                }else txtDialogOpcion.setVisibility(View.GONE);
            }

            /**
             * Si tengo que realizar un evento con registro
             */
            if (tengoRegistro) {
                llCircleProgressBar.setVisibility(View.GONE);
                txtDialogOpcion.setVisibility(View.GONE);
                rlMostrarImagen.setVisibility(View.GONE);
                llRegistrate.setVisibility(View.VISIBLE);
                txtDialogIntentarNuevamente.setVisibility(View.VISIBLE);
                SpannableString content = new SpannableString(strOpcion);
                content.setSpan(new UnderlineSpan(), 0, strOpcion.length(), 0);
                txtDialogIntentarNuevamente.setText(content);
            }
        }
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.imgCerrarDialog){
            callbackDialog.onClickCloseDialogEventos();
        }

        if(v.getId() == R.id.txtDialogOpcion){
            if (esError) {
                callbackDialog.onClickOptionDialogError();
            } else {
                callbackDialog.onClickOptionDialog();
            }
        }

        if(v.getId() == R.id.btnDialogRegistrate){
            callbackDialog.onClickDialogBtnRegistro();
        }

        if(v.getId() == R.id.txtDialogIntentarNuevamente){
            if(isEndSession){
                callbackDialog.onClickCloseDialogEndSession();
            }else {
                callbackDialog.onClickOptionDialogIntentarNuevamente();
            }
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

}
