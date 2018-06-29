using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations.Schema;
using System.Text;

namespace Posiljka.Data.EntityModels
{
    public class Korisnik
    {
        public int Id { get; set; }
        public string Ime { get; set; }
        public string Prezime { get; set; }
        [ForeignKey(nameof(Opstina))]
        public int OpstinaID { get; set; }
        public Opstina Opstina { get; set; }

        [ForeignKey(nameof(KorisnickiNalog))]
        public int? KorisnickiNalogId { get; set; }
        public KorisnickiNalog KorisnickiNalog { get; set; }
    }
}
