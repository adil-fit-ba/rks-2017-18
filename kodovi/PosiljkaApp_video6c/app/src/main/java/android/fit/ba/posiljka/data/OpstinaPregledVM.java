package android.fit.ba.posiljka.data;

import java.io.Serializable;
import java.util.List;

public class OpstinaPregledVM implements Serializable
    {
       public static class Row implements Serializable
       {
           public int id ;
           public String naziv;
           public String drzava ;
       }

       public List<Row> rows;

    }
