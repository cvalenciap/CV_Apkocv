package pe.com.sedapal.ofivirtual.ui.activity;

import android.content.Context;

import androidx.fragment.app.Fragment;

import pe.com.sedapal.ofivirtual.ui.fragment.BaseFragment;

public abstract class BaseDrawerFragment extends BaseFragment {

    protected OnFragmentInteractionListener goOnFragmentInteractionListener;
    protected OnBackFragment goOnBackFragment;

    @Override
    public void onAttach(Context poContext) {
        super.onAttach(poContext);
        if (poContext instanceof OnFragmentInteractionListener) {
            goOnFragmentInteractionListener = (OnFragmentInteractionListener) poContext;
        } else {
            throw new RuntimeException(poContext.toString()
                    + " must implement OnFragmentInteractionListener");
        }

        if (poContext instanceof CommercialOfficeActivity) {
            goOnBackFragment = (OnBackFragment) poContext;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        goOnFragmentInteractionListener = null;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Fragment poFragment);
    }

    public interface OnBackFragment {
        void onBack(BaseDrawerFragment poBaseDrawerFragment);
    }

}

