package android.fit.ba.posiljka.data;

import java.util.List;

public class KorisnikPregledVM
    {
        public static class Row
        {
            public Integer id;
            public String ime;
            public String prezime;
            public String opstina;
            public String drzava;
        }
        public List<Row> rows;
    }
