using System.ComponentModel.DataAnnotations;
using Posiljka.Web.Helper;

namespace Posiljka.Web.ViewModels.api
{
    public class AutentifikacijaResultVM
    {
        public string username { get; set; }
        public string ime{ get; set; }
        public string prezime{ get; set; }
        public string token{ get; set; }
        public int? korisnickiNalogId;
    }
}
