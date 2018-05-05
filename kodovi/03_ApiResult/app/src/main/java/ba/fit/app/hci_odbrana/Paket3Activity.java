package ba.fit.app.hci_odbrana;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
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

public class Paket3Activity extends FragmentActivity {

    private ListView mojListView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paket3);

        mojListView = (ListView) findViewById(R.id.mojListViewID);

        OdjeljenjeApi.Index(new ApiTask<OdjeljenjeIndexVM>() {
            @Override
            public void run(boolean isCommunicationOrParseError, boolean isException, int exceptionCode, String message, OdjeljenjeIndexVM value) {
                if(!isException)
                   popuniListView(value);
            }
        });
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

                    LayoutInflater inflater = (LayoutInflater) Paket3Activity.this
                            .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

                    convertView = (View) inflater.inflate(R.layout.stavka_odjeljenje, null);
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
