package com.company.helper.zadaci;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by adil on 3/5/2018.
 */
public class UpravljateljZadataka
{
    private final String naziv;
    private int idSeed;
    private boolean jelAktivan;

    public UpravljateljZadataka(String naziv)
    {
        this.naziv = naziv;
        jelAktivan =false;
    }

    private Set<RadniZadatak> zadaci = new HashSet<>();
    public void dodaj(RadniZadatak zadatak)
    {
        zadaci.add(zadatak);

        if (jelAktivan)
            odaberiIzvrsiZadatak();
    }

    public void start()
    {
        jelAktivan = true;
        odaberiIzvrsiZadatak();
    }

    private void odaberiIzvrsiZadatak()
    {
        RadniZadatak zMax = getRadniZadatak();

        if (zMax == null)
            return;

        zadaci.remove(zMax);

        idSeed++;
        print(zMax, "Izvrsavanje u toku");
        zMax.oznaciIzvrsavanjeUToku();
        try {
            zMax.run();

            print(zMax, "Zavrseno uspjesno");
            zMax.oznaciZavrsenUspjesno();
        } catch (Exception e) {
            e.printStackTrace();
            print(zMax, "Zavrseno sa greskom");
            zMax.oznaciZavrsenGreska();
        }

        System.out.println("");
        System.out.println("===============================");
        System.out.println("");
        odaberiIzvrsiZadatak();
    }

    private RadniZadatak getRadniZadatak() {
        int pMax=Integer.MIN_VALUE;
        RadniZadatak zMax = null;

        for (RadniZadatak z: zadaci) {
            if (z.getPrioritet() > pMax && z.jelNaCekanju())
            {
                pMax = z.getPrioritet();
                zMax = z;
            }
        }
        return zMax;
    }


    private void print(RadniZadatak zadatak, String poruka)
    {
        System.out.println("<<< " + naziv + " <<< " + zadatak.getNaziv() + " (id: " + idSeed + ") <<< " + poruka);
    }
}
