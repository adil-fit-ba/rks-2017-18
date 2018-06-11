package android.fit.ba.posiljka.fragments;


import android.app.Activity;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.fit.ba.posiljka.data.KorisnikVM;
import android.fit.ba.posiljka.data.PosiljkaVM;
import android.fit.ba.posiljka.helper.MyFragmentUtils;
import android.fit.ba.posiljka.helper.MyRunnable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.fit.ba.posiljka.R;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PosiljkaAdd1Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PosiljkaAdd1Fragment extends Fragment {
    private EditText txtIme;
    private EditText txtAdresa;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;


    public static PosiljkaAdd1Fragment newInstance() {
        PosiljkaAdd1Fragment fragment = new PosiljkaAdd1Fragment();
        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_posiljka_add1, container, false);

        txtIme = view.findViewById(R.id.txtIme);
        txtAdresa = view.findViewById(R.id.txtAdresa);
        Button btnPromjeni= view.findViewById(R.id.btnPromjeni);
        Button btnDalje= view.findViewById(R.id.btnDalje);

        btnPromjeni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                do_btnPromjeniClick();
            }
        });

        btnDalje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                do_btnDaljeClick();
            }
        });

        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        return view;
    }

    private void do_btnDaljeClick() {
MyFragmentUtils.openAsReplace(getActivity(), R.id.mjestoFragment , PosiljkaAdd2Fragment.newInstance(posiljkaVM));
    }

    private PosiljkaVM posiljkaVM=new PosiljkaVM();

    private void do_btnPromjeniClick() {

        MyRunnable callBack=new MyRunnable<KorisnikVM>(){

            @Override
            public void run(KorisnikVM result) {

                posiljkaVM.primaoc = result;

                txtIme.setText(result.getIme() + " " + result.getPrezime());
                txtAdresa.setText(result.getOpstinaVM().toString());
            }
        };


        PretragaDialogFragment dlg = PretragaDialogFragment.newInstance(callBack);


       MyFragmentUtils.openAsDialog(getActivity(), dlg);
    }



}
