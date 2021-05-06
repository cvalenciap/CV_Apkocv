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
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import pe.com.sedapal.ofivirtual.R;

public class LoadingDialogFragment extends DialogFragment {
    TextView txtDescripcionLoading;

    //ImageView imvLoadGif;
    public static LoadingDialogFragment newInstance(String strDescripcion) {
        LoadingDialogFragment frag = new LoadingDialogFragment();
        Bundle args = new Bundle();
        args.putString("TXT_DESCRIPCION",strDescripcion);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final Context contextThemeWrapper = new ContextThemeWrapper(getActivity(), R.style.AppCompatAlertDialogFragmentStyle);
        LayoutInflater localInflater = inflater.cloneInContext(contextThemeWrapper);
        View view = localInflater.inflate(R.layout.fragment_loading_dialog, container);

        if (getDialog() != null && getDialog().getWindow() != null) {
            getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        }

        String descripcionOntenida = getArguments().getString("TXT_DESCRIPCION","Enviando");

        txtDescripcionLoading = (TextView)view.findViewById(R.id.txtDescripcionLoading);
        txtDescripcionLoading.setText(descripcionOntenida);

        //imvLoadGif = (ImageView)view.findViewById(R.id.imvLoadGif);
        //GlideDrawableImageViewTarget imageViewTarget = new GlideDrawableImageViewTarget(imvLoadGif);
        //Glide.with(this).load(R.mipmap.loading_97).into(imageViewTarget);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
