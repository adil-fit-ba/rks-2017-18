package android.fit.ba.posiljka.data;

import java.io.Serializable;

public class KorisnikAddVM implements Serializable
    {
        public String ime;
        public String prezime;
        public int opstinaId;

        public KorisnikAddVM(String ime, String prezime, int opstinaId) {

            this.ime = ime;
            this.prezime = prezime;
            this.opstinaId = opstinaId;
        }
    }
