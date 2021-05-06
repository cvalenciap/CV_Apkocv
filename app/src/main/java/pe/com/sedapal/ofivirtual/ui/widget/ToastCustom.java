package pe.com.sedapal.ofivirtual.ui.widget;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;

import pe.com.sedapal.ofivirtual.R;


/**
 * Created by jsaenz on 05/03/2019.
 */
public class ToastCustom extends Toast {
    /**
     * The Constant TOAST_DURATION.
     */
    public final static int TYPE_ERROR=100;
    public final static int TYPE_CORRECTO=-100;
    public final static int TYPE_INFO=0;
    private static final int TOAST_DURATION = 6000;
    /**
     * Vista toast custom
     */
    View loToastCustomView;


    /**
     * Constructor de toast personalizado.
     * @param poContext         El contexto de donde usar.
     * @param poText            Indica el mensaje a mostrar en el Toast.
     */
    public ToastCustom(Context poContext, String poText){
        super(poContext);
        LayoutInflater loLayoutInflater = LayoutInflater.from(poContext);
        loToastCustomView = loLayoutInflater.inflate(R.layout.toast_custom,null);
        TextView loTextToast = (TextView)loToastCustomView.findViewById(R.id.toast_custom_text);
        loTextToast.setText(poText);
        loTextToast.setTextColor(Color.BLACK);
        this.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        this.setDuration(LENGTH_SHORT);
        this.setView(loToastCustomView);
    }
    /**
     * Constructor de toast personalizado con parametro de tipo icono a mostrar.
     * @param poContext         El contexto de donde usar.
     * @param poText            Indica el mensaje a mostrar en el Toast.
     * @param poTipoMensaje     Indica si el toast personalizado visualizara un icono
     *                          de tipo error, info, correcto. los valores estaticos son:
     *                          TYPE_ERROR, TYPE_CORRECTO, TYPE_INFO.
     */
    public ToastCustom(Context poContext, String poText, int poTipoMensaje){
        super(poContext);
        LayoutInflater loLayoutInflater = LayoutInflater.from(poContext);
        loToastCustomView = loLayoutInflater.inflate(R.layout.toast_custom,null);
        TextView loTextToast = (TextView)loToastCustomView.findViewById(R.id.toast_custom_text);
        ImageView loImgError= (ImageView)loToastCustomView.findViewById(R.id.toast_custom_img_error);
        ImageView loImgCorrecto = (ImageView) loToastCustomView.findViewById(R.id.toast_custom_img_correcto);
        ImageView loImgInformacion = (ImageView) loToastCustomView.findViewById(R.id.toast_custom_img_info);
        CardView llToastCustom = (CardView) loToastCustomView.findViewById(R.id.ll_toast_custom);

        if (poTipoMensaje == TYPE_CORRECTO){
            loImgCorrecto.setVisibility(View.VISIBLE);
            llToastCustom.setCardBackgroundColor(loToastCustomView.getResources().getColor(R.color.bg_mensaje_confirmacion_correcto));
        }else if (poTipoMensaje == TYPE_INFO){
            loImgInformacion.setVisibility(View.VISIBLE);
            llToastCustom.setCardBackgroundColor(loToastCustomView.getResources().getColor(R.color.bg_mensaje_confirmacion_correcto));
        }else{
            loImgError.setVisibility(View.VISIBLE);
            llToastCustom.setCardBackgroundColor(loToastCustomView.getResources().getColor(R.color.bg_mensaje_confirmacion_incorrecto));
        }
        loTextToast.setText(poText);
        loTextToast.setTextColor(Color.WHITE);
        this.setDuration(LENGTH_SHORT);
        this.setView(loToastCustomView);
    }

    /**
     * Constructor de toast personalizado sin icono.
     * @param poContext         El contexto de donde usar.
     * @param poText            Indica el mensaje a mostrar en el Toast.
     * @param poTime            Parametro no funcional, mandar cualquier valor.
     */
    public static ToastCustom makeText(Context poContext,String poText,int poTime){
        ToastCustom loToastCustom = new ToastCustom(poContext,poText);
        return loToastCustom;
    }
    /**
     * Constructor de toast personalizado con parametro de tipo icono a mostrar.
     * @param poContext         El contexto de donde usar.
     * @param poText            Indica el mensaje a mostrar en el Toast.
     * @param poTime            Parametro no funcional, mandar cualquier valor.
     * @param poTypeToast       Indica si el toast personalizado visualizara un icono
     *                          de tipo error, info, correcto. los valores estaticos son:
     *                          TYPE_ERROR, TYPE_CORRECTO, TYPE_INFO.
     */
    public static ToastCustom makeText(Context poContext,String poText,int poTime,int poTypeToast){
        ToastCustom loToastCustom = new ToastCustom(poContext,poText,poTypeToast);
        return loToastCustom;
    }
}
