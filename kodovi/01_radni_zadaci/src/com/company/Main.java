package com.company;

import com.company.helper.MyDownloader;
import com.company.helper.zadaci.RadniZadatak;
import com.company.helper.zadaci.UpravljateljZadataka;

public class Main {

    public static void main(String[] args) {
	// write your code here

        UpravljateljZadataka uprav = new UpravljateljZadataka("Server1");

        uprav.dodaj(new RadniZadatak("download 10 mb", 10){

            @Override
            public void run() throws Exception {

                String urlStr = "https://raw.githubusercontent.com/adil-fit-ba/rs1-2017-18/master/02.1_Csharp_razlike/C%20sharp%201_zadaci.doc";

                MyDownloader.downloadFile(urlStr, "c:\\tmp");
            }
        });


        uprav.dodaj(new RadniZadatak("trougao-printer"){

            @Override
            public void run() throws Exception {

                for (int i = 0; i < 12; i++)
                {
                    for (int j = 0; j < i; j++)
                    {
                        System.out.print(".");
                        Thread.sleep(100);
                    }
                    System.out.println("");
                }
            }
        });


        uprav.dodaj(new RadniZadatak("spavanje"){

            @Override
            public void run() throws Exception {

                int v = 5;
                for (int i = 0; i < v; i++)
                {
                    System.out.println("spavam " + i + "/" + v);
                    Thread.sleep(1000);
                }
            }
        });


        uprav.start();
    }
}
