package app.fit.ba.posiljka.fragments;


import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import app.fit.ba.posiljka.R;
import app.fit.ba.posiljka.helper.Util;
import app.fit.ba.posiljka.podaci.PosiljkaVM;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PosiljkaListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PosiljkaListFragment extends Fragment {


    public PosiljkaListFragment() {
        // Required empty public constructor
    }

    public static PosiljkaListFragment newInstance() {
        return new PosiljkaListFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_posiljka_list, container, false);


        FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                do_fabClick();
            }
        });


        return view;
    }

    private void do_fabClick() {
        Snackbar.make(getView(), "Here's a Snackbar", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();

        Util.otvoriFragmentKaoReplace(getActivity(), R.id.fragmentPlace, PosiljkaAdd1Fragment.newInstance());
    }

}
