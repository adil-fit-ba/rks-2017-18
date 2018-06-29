using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations.Schema;
using System.Text;

namespace Posiljka.Data.EntityModels
{
    public class Opstina
    {
        public int Id { get; set; }
        public string Naziv { get; set; }

        [ForeignKey(nameof(Drzava))]
        public int DrzavaID { get; set; }
        public Drzava Drzava { get; set; }
    }
}
