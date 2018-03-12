package ba.fit.app.hci_odbrana.podaci;

import java.io.Serializable;

/**
 * Created by Adil on 18/06/2016.
 */
public class KorisnikVM
{
    private String imePrezime;
    private String adresaOpstina;

    public KorisnikVM(String imePrezime,  String adresaOpstina) {
        this.imePrezime = imePrezime;
        this.adresaOpstina = adresaOpstina;
    }

    public String getImePrezime() {
        return imePrezime;
    }

    public void setImePrezime(String imePrezime) {
        this.imePrezime = imePrezime;
    }

    public String getAdresaOpstina() {
        return adresaOpstina;
    }

    public void setAdresaOpstina(String adresaOpstina) {
        this.adresaOpstina = adresaOpstina;
    }
}
