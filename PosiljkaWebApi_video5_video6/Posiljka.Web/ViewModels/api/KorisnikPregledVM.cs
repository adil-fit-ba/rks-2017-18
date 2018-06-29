using System.Collections.Generic;

namespace Posiljka.Web.ViewModels.api
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
