package ba.fit.app.hci_odbrana;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import ba.fit.app.hci_odbrana.podaci.PosiljkaVM;
import ba.fit.app.hci_odbrana.podaci.Storage;

/**
 * Created by adil on 3/19/2018.
 */

public class Paket3Activity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paket3);

        ListView mojListView = (ListView) findViewById(R.id.mojListViewID);


        List<String> values = new ArrayList<>();

        for (PosiljkaVM x : Storage.getPosiljkeAll()) {
            values.add(x.posljiaoc.getImePrezime() + " -> " + x.primaoc.getImePrezime()+ " (" + x.iznosNaplate  + ")");
        }


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, values);



        mojListView.setAdapter(adapter);

    }
}
