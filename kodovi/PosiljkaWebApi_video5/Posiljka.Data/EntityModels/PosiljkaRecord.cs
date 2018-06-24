using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations.Schema;
using System.Text;

namespace Posiljka.Data.EntityModels
{
    public class PosiljkaRecord
    {
        public int Id { get; set; }

        public float Masa { get; set; }
        public string Napomena { get; set; }
        public int BrojPosiljke { get; set; }
        public float Iznos { get; set; }
        public bool PlacaPouzecem { get; set; }


        [ForeignKey(nameof(KorisnikPrimaoc))]
        public int KorisnikPrimaocID { get; set; }
        public Korisnik KorisnikPrimaoc { get; set; }


        [ForeignKey(nameof(KorisnikEvidentirao))]
        public int? KorisnikEvidentiraoID { get; set; }
        public Korisnik KorisnikEvidentirao { get; set; }
        public DateTime? VrijemeEvidentirana { get; set; }
    }
}
