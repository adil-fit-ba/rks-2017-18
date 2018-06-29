package ba.fit.app.hci_odbrana.podaci;

import java.io.Serializable;

/**
 * Created by Adil on 02/02/2017.
 */

public class PosiljkaVM implements Serializable {
	private static int brojac;
    public KorisnikVM posljiaoc;
    public KorisnikVM primaoc;
    public float masa;
    public float duzina;
    public float sirina;
    public float visina;
	public int brojPosiljke;

    public PosiljkaVM(KorisnikVM posljiaoc, KorisnikVM primaoc, float masa, float duzina, float sirina, float visina) {
        this.posljiaoc = posljiaoc;
        this.primaoc = primaoc;
        this.masa = masa;
        this.duzina = duzina;
        this.sirina = sirina;
        this.visina = visina;
		brojPosiljke = brojac++;
    }

    public PosiljkaVM()
    {

    }
}
