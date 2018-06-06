package app.fit.ba.posiljka.dialogs;


import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import app.fit.ba.posiljka.R;
import app.fit.ba.posiljka.helper.MyRunnable;
import app.fit.ba.posiljka.podaci.KorisnikVM;
import app.fit.ba.posiljka.podaci.Storage;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PretragaDialogFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PretragaDialogFragment extends DialogFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";

    private MyRunnable<KorisnikVM> pMyCallBack;
    private ListView listView;
    private EditText editText;


    public PretragaDialogFragment() {
        // Required empty public constructor
    }


    public static PretragaDialogFragment newInstance(MyRunnable<KorisnikVM> myCallBack) {
        PretragaDialogFragment fragment = new PretragaDialogFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, myCallBack);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            pMyCallBack = (MyRunnable<KorisnikVM>) getArguments().getSerializable(ARG_PARAM1);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pretraga_dialog, container, false);


        listView = (ListView) view.findViewById(R.id.listView);
        editText = (EditText) view.findViewById(R.id.editText);


        Button btnPretraga = (Button) view.findViewById(R.id.btnPretraga);
        btnPretraga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                do_btnClick();
            }
        });

        return view;
    }

    private void do_btnClick() {

        String query = editText.getText().toString();
        popuniPodatke(query);
    }

    private void popuniPodatke(String query) {

        final List<KorisnikVM> podaci = Storage.getKorisniciByIme(query);

        listView.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return podaci.size();
            }

            @Override
            public Object getItem(int i) {
                return null;
            }

            @Override
            public long getItemId(int i) {
                return 0;
            }

            @Override
            public View getView(int i, View view, ViewGroup viewGroup) {

                if(view==null)
                {
                    LayoutInflater inflater = (LayoutInflater)getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    view = inflater.inflate(R.layout.stavka_korisnici, viewGroup,false);
                }

                TextView txtIme = (TextView) view.findViewById(R.id.txtIme);
                TextView txtAdresa = (TextView) view.findViewById(R.id.txtAdresa);

                KorisnikVM x = podaci.get(i);


                txtAdresa.setText(x.getOpstinaVM().getNaziv());
                txtIme.setText(x.getIme() + " " + x.getPrezime());


                return view;
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                KorisnikVM x = podaci.get(position);

                pMyCallBack.run(x);
                getDialog().dismiss();

            }
        });
    }



}
