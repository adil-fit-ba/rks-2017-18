package ba.fit.app.hci_odbrana.api;

import java.util.List;

/**
 * Created by Adil Joldic on 09/04/2018.
 */

public class AdminOdjeljenjaVM {
        public List<Row> rows ;

        public class Row
        {
            public int OdeljenjeId;
            public String SkolskaGodina ;
            public String Razrednik ;
            public String Oznaka ;
            public int Razred ;
        }
}
