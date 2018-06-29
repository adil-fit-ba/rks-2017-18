package android.fit.ba.posiljka.fragments;


import android.app.Fragment;
import android.fit.ba.posiljka.R;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



public class PosiljkaAdd1Fragment extends Fragment {


    public static PosiljkaAdd1Fragment newInstance() {
        PosiljkaAdd1Fragment fragment = new PosiljkaAdd1Fragment();

        return fragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_posiljka_add1, container, false);



        return view;
    }

}
