using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations.Schema;

namespace Posiljka.Api.ViewModels
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
