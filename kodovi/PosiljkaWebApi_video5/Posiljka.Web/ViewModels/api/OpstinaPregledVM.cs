using System;
using System.Collections.Generic;

namespace Posiljka.Web.ViewModels.api
{
        
    public class OpstinaPregledVM
    {
        public class Row
        {
            public int id;
            public String naziv;
            public String drzava;
        }

        public List<Row> rows;

    }
}
