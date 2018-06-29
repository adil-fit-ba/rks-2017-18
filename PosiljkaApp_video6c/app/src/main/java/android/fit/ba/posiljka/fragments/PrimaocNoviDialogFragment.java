package android.fit.ba.posiljka.fragments;


import android.app.DialogFragment;
import android.app.Fragment;
import android.fit.ba.posiljka.R;
import android.fit.ba.posiljka.data.KorisnikAddVM;
import android.fit.ba.posiljka.data.KorisnikPregledVM;
import android.fit.ba.posiljka.data.OpstinaPregledVM;
import android.fit.ba.posiljka.helper.MyApiRequest;
import android.fit.ba.posiljka.helper.MyRunnable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;




/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PrimaocNoviDialogFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PrimaocNoviDialogFragment extends DialogFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";

    private MyRunnable<KorisnikPregledVM.Row> pMyCallBack;
    private Spinner spinnerOpstina;
    private EditText txtPrezime;
    private EditText txtIme;
    private OpstinaPregledVM podaci;


    public PrimaocNoviDialogFragment() {
        // Required empty public constructor
    }


    public static PrimaocNoviDialogFragment newInstance(MyRunnable<KorisnikPregledVM.Row> myCallBack) {
        PrimaocNoviDialogFragment fragment = new PrimaocNoviDialogFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, myCallBack);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            pMyCallBack = (MyRunnable<KorisnikPregledVM.Row>) getArguments().getSerializable(ARG_PARAM1);
        }
        setStyle(DialogFragment.STYLE_NORMAL, R.style.MojDialog);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.dialog_primaoc_novi, container, false);
        view.findViewById(R.id.button_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });


        txtIme = view.findViewById(R.id.txtIme);
        txtPrezime = view.findViewById(R.id.txtPrezime);
        spinnerOpstina = view.findViewById(R.id.spinnerOpstina);
        view.findViewById(R.id.btnSnimi).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                do_btnDodaj();
            }
        });

        popuniPodatkeTask();

        return view;
    }

    private void popuniPodatkeTask() {

        MyApiRequest.get(getActivity(), "/Opstina/GetAll", new MyRunnable<OpstinaPregledVM>() {
            @Override
            public void run(OpstinaPregledVM x) {
                podaci = x;
                popuniPodatke();
            }
        });
    }

    private void popuniPodatke() {
        List<String> result = new ArrayList<>();

        for (OpstinaPregledVM.Row x : podaci.rows) {
            result.add(x.drzava + " - " + x.naziv);
        }

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, result);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerOpstina.setAdapter(dataAdapter);

    }

    private void do_btnDodaj() {
        int position = spinnerOpstina.getSelectedItemPosition();
        OpstinaPregledVM.Row x = podaci.rows.get(position);

        KorisnikAddVM newKorisnik = new KorisnikAddVM(txtIme.getText().toString(), txtPrezime.getText().toString(), x.id);

        MyApiRequest.post(getActivity(), "/Korisnik/Add", newKorisnik, new MyRunnable<KorisnikPregledVM.Row>(){

            @Override
            public void run(KorisnikPregledVM.Row x) {
                pMyCallBack.run(x);
            }
        } );

        //
        getDialog().dismiss();
    }



}
