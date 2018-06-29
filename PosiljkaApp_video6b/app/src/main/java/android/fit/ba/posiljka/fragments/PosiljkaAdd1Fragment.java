package android.fit.ba.posiljka.fragments;


import android.app.Fragment;
import android.fit.ba.posiljka.data.KorisnikPregledVM;
import android.fit.ba.posiljka.data.PosiljkaAddVM;
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


    public static PosiljkaAdd1Fragment newInstance() {
        return new PosiljkaAdd1Fragment();
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_posiljka_add1, container, false);

        txtIme = view.findViewById(R.id.txtIme);
        txtAdresa = view.findViewById(R.id.txtAdresa);
        ImageView imgAdd= view.findViewById(R.id.imgAdd);
        Button btnDalje= view.findViewById(R.id.btnDalje);

        imgAdd.setOnClickListener(new View.OnClickListener() {
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

        return view;
    }

    private void do_btnDaljeClick() {
        MyFragmentUtils.openAsReplace(getActivity(), R.id.mjestoFragment , PosiljkaAdd2Fragment.newInstance(posiljkaVM));
    }

    private PosiljkaAddVM posiljkaVM=new PosiljkaAddVM();

    private void do_btnPromjeniClick() {

        MyRunnable<KorisnikPregledVM.Row> callBack=new MyRunnable<KorisnikPregledVM.Row>(){

            @Override
            public void run(KorisnikPregledVM.Row result) {

                posiljkaVM.korisnikPrimaocId = result.id;

                txtIme.setText(result.ime + " " + result.prezime);
                txtAdresa.setText(result.drzava + " " + result.opstina);
            }
        };


        PretragaDialogFragment dlg = PretragaDialogFragment.newInstance(callBack);


        MyFragmentUtils.openAsDialog(getActivity(), dlg);
    }


}
