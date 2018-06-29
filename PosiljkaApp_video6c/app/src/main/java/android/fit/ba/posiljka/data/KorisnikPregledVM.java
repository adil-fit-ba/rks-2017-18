package android.fit.ba.posiljka.data;

import java.io.Serializable;
import java.util.List;

public class KorisnikPregledVM implements Serializable
    {
        public static class Row implements Serializable
        {
            public Integer id;
            public String ime;
            public String prezime;
            public String opstina;
            public String drzava;
        }
        public List<Row> rows;
    }
