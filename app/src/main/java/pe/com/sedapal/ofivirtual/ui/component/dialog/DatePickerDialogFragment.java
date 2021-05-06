package pe.com.sedapal.ofivirtual.ui.component.dialog;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import pe.com.sedapal.ofivirtual.R;

public class DatePickerDialogFragment extends DialogFragment {

    ImageView imgCerrarDialog;
    DatePicker dpSeleccionaFecha;
    TextView txtSeleccionar;
    OnClickCallbackDateDialog clickCallbackDialog;

    public interface OnClickCallbackDateDialog {
        void onClickCloseDialog();
        void onClickSeleccionar(String fechaObtenida);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        clickCallbackDialog = (OnClickCallbackDateDialog) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final Context contextThemeWrapper = new ContextThemeWrapper(getActivity(), R.style.AppCompatAlertDialogFragmentStyle);
        LayoutInflater localInflater = inflater.cloneInContext(contextThemeWrapper);
        View view = localInflater.inflate(R.layout.fragment_date_picker_dialog, container);

        if (getDialog() != null && getDialog().getWindow() != null) {
            getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        }

        imgCerrarDialog = (ImageView) view.findViewById(R.id.imgCerrarDialog);
        dpSeleccionaFecha = view.findViewById(R.id.dpSeleccionaFecha);
        txtSeleccionar = view.findViewById(R.id.txtSeleccionar);

        imgCerrarDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickCallbackDialog.onClickCloseDialog();
            }
        });

        txtSeleccionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(dpSeleccionaFecha.getYear(), dpSeleccionaFecha.getMonth(), dpSeleccionaFecha.getDayOfMonth());

                String formato = "dd/MM/yy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(formato, Locale.US);

                clickCallbackDialog.onClickSeleccionar(sdf.format(calendar.getTime()));
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
