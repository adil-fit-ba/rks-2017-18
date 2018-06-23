using System.ComponentModel.DataAnnotations;
using Posiljka.Web.Helper;

namespace Posiljka.Web.ViewModels.api
{
    public class AutentifikacijaCheckVM
    {
        public string Username { get; set; }
        public string Password{ get; set; }
    }
}
