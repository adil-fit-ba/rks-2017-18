using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Posiljka.Api.ViewModels
{
    public class PosiljkaPregledVM
    {
       

        public class Row
        {
            public string primaocImePrezime;
            public string primaocAdresa;
            public float masa;
            public String napomena;
            public int brojPosiljke;
            public float iznos;
            public bool placaPouzecem;
        }

        public List<Row> rows;

    }
}
