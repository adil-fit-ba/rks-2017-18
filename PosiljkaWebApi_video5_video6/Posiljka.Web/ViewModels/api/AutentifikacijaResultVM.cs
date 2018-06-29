using System.ComponentModel.DataAnnotations;
using Posiljka.Web.Helper;

namespace Posiljka.Web.ViewModels.api
{
    public class AutentifikacijaResultVM
    {
        public string username;
        public string ime;
        public string prezime;
        public string token;
        public int? korisnickiNalogId;
    }
}
