package ba.fit.app.hci_odbrana.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ba.fit.app.hci_odbrana.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link K4Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class K4Fragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public K4Fragment() {
        // Required empty public constructor
    }

    public static K4Fragment newInstance() {
        K4Fragment fragment = new K4Fragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_k4, container, false);
        return view;
    }

}
