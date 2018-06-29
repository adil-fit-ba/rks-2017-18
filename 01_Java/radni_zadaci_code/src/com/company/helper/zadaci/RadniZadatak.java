package com.company.helper.zadaci;

import java.net.MalformedURLException;
import java.util.Date;

/**
 * Created by adil on 3/5/2018.
 */


public abstract class RadniZadatak {

    public enum Status
    {
        CEKANJE, IZVRSAVANJE_U_TOKU, ZAVRSENO_USPJESNO, ZAVRSENO_GRESKA
    }



    private String naziv;
    private int prioritet;
    private Status status;

    public RadniZadatak(String naziv, int prioritet) {
        this.naziv = naziv;
        this.prioritet = prioritet;
        status  = Status.CEKANJE;
    }

    public RadniZadatak(int prioritet) {
        this("no name at " +  new Date().toString(), prioritet);
    }

    public RadniZadatak(String naziv) {
        this(naziv, 5);
    }

    public RadniZadatak() {
        this("no name at " +  new Date().toString(), 5);
    }

    public abstract void run() throws Exception;

    public void oznaciIzvrsavanjeUToku() {
        status = Status.IZVRSAVANJE_U_TOKU;

    }

    public boolean jelNaCekanju() {
        return status == Status.CEKANJE;
    }

    public void oznaciZavrsenUspjesno() {
        status = Status.ZAVRSENO_USPJESNO;
    }

    public void oznaciZavrsenGreska() {
        status = Status.ZAVRSENO_GRESKA;

    }

    public int getPrioritet() {
        return prioritet;
    }

    public Status getStatus() {
        return status;
    }
    public String getNaziv() {
        return naziv;
    }
}
