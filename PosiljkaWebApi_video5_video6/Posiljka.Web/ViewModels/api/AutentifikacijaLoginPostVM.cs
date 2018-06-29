using System.ComponentModel.DataAnnotations;
using Posiljka.Web.Helper;

namespace Posiljka.Web.ViewModels.api
{
    public class AutentifikacijaLoginPostVM
    {
        public string Username { get; set; }
        public string Password{ get; set; }
        public string deviceInfo{ get; set; }
    }
}
