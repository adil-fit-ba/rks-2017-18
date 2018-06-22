using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations.Schema;

namespace Posiljka.Api.ViewModels
{
    public class KorisnikPregledVM
    {
        public class Row
        {
            public int? id;
            public string ime;
            public string prezime;
            public string opstina;
        }

        public List<Row> rows;
    }
}
