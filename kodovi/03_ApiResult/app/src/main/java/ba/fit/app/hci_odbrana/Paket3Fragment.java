package ba.fit.app.hci_odbrana;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import ba.fit.app.hci_odbrana.helper.ApiTask;
import ba.fit.app.hci_odbrana.helper.api.OdjeljenjeApi;
import ba.fit.app.hci_odbrana.helper.api.OdjeljenjeIndexVM;

/**
 * Created by adil on 3/19/2018.
 */

public class Paket3Fragment extends Fragment {

    private ListView mojListView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_paket3, container, false);

        mojListView = (ListView) view.findViewById(R.id.mojListViewID);

        OdjeljenjeApi.Index(new ApiTask<OdjeljenjeIndexVM>() {
            @Override
            public void run(boolean isCommunicationOrParseError, boolean isException, int exceptionCode, String message, OdjeljenjeIndexVM value) {
                if(!isException)
                    popuniListView(value);
            }
        });

        return view;
    }



    private void popuniListView(final OdjeljenjeIndexVM value) {
        mojListView.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return value.rows.size();
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
            public View getView(int i, View convertView, ViewGroup viewGroup) {


                if (convertView == null) {

                    LayoutInflater inflater = (LayoutInflater) getActivity()
                            .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

                    convertView = (View) inflater.inflate(R.layout.stavka_odjeljenje1, null);
                }
                OdjeljenjeIndexVM.Row row = value.rows.get(i);

                TextView oznakaOdjeljenja = (TextView) convertView.findViewById(R.id.oznakaOdjeljenjaTxt);
                oznakaOdjeljenja.setText(row.oznaka);

                TextView razrednik = (TextView) convertView.findViewById(R.id.razrednikTxt);
                razrednik.setText(row.razrednik);

                TextView razred = (TextView) convertView.findViewById(R.id.razredTxt);
                razred.setText(String.valueOf(row.razred));

                TextView skolskaGodina = (TextView) convertView.findViewById(R.id.skolskaGodinaTxt);
                skolskaGodina.setText(row.skolskaGodina);

                return convertView;
            }
        });
    }
}
