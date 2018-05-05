package ba.fit.app.hci_odbrana.helper.api;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Adil Joldic on 09/04/2018.
 */

public class OdjeljenjeIndexVM implements Serializable{
        public List<Row> rows ;

        public class Row implements Serializable
        {
            public int OdeljenjeId;
            public String SkolskaGodina ;
            public String Razrednik ;
            public String Oznaka ;
            public int Razred ;
            public Integer RazrednikID ;
        }
}
