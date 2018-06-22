package android.fit.ba.posiljka.data;

import java.io.Serializable;

/**
 * Created by Adil on 18/06/2016.
 */
public class KorisnikVM implements Serializable
{
    private String ime;
    private String prezime;
    private String username;
    private String password;
    private OpstinaVM opstinaVM;

    public KorisnikVM(String username, String password, String ime, String prezime, OpstinaVM opstinaVM) {
        this.username = username;
        this.password = password;
        this.ime = ime;
        this.prezime = prezime;
        this.opstinaVM = opstinaVM;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public OpstinaVM getOpstinaVM() {
        return opstinaVM;
    }

    public void setOpstinaVM(OpstinaVM opstinaVM) {
        this.opstinaVM = opstinaVM;
    }

    public String getImePrezime() {
        return ime + " " + prezime;
    }
}
