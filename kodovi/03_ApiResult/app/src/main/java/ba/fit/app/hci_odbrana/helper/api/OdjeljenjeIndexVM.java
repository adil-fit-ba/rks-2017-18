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
            public int odeljenjeId;
            public String skolskaGodina;
            public String razrednik;
            public String oznaka;
            public int razred;
            public Integer razrednikID;
        }
}
