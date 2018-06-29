package android.fit.ba.posiljka.fragments;

import android.app.Fragment;
import android.content.Context;
import android.fit.ba.posiljka.R;
import android.fit.ba.posiljka.data.PosiljkaVM;
import android.fit.ba.posiljka.data.Storage;
import android.fit.ba.posiljka.helper.MyFragmentUtils;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.QuickContactBadge;
import android.widget.TextView;

import java.util.List;

public class PosiljkaListFragment extends Fragment {

    private ListView lvPosiljka;
    private Button btnDodaj;


    public  static PosiljkaListFragment newInstance()
    {
        return new PosiljkaListFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_posiljka_list, container, false);

        btnDodaj = view.findViewById(R.id.btnDodaj);
        lvPosiljka = view.findViewById(R.id.lvPosiljka);
        popuniPodatke();

        btnDodaj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                do_btnDodajClick();
            }
        });
        
        return view;


    }

    private void do_btnDodajClick() {
        MyFragmentUtils.openAsReplace(getActivity(), R.id.mjestoFragment, PosiljkaAdd1Fragment.newInstance());
    }

    private void popuniPodatke() {

        final List<PosiljkaVM> podaci = Storage.getPosiljke();

        lvPosiljka.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return podaci.size();
            }

            @Override
            public Object getItem(int position) {
                return null;
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public View getView(int position, View view, ViewGroup parent) {

                if (view == null)
                {
                    LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    view = inflater.inflate(R.layout.stavka_posiljka, parent, false);
                }
                TextView txtFirstLine = view.findViewById(R.id.txtFirstLine);
                TextView txtSecondLine = view.findViewById(R.id.txtSecondLine);


                PosiljkaVM x = podaci.get(position);

                txtFirstLine.setText(x.primaoc.getImePrezime());
                txtSecondLine.setText(x.primaoc.getOpstinaVM().toString());

                return view;
            }
        });
    }
}
