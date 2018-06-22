using System.ComponentModel.DataAnnotations;
using Posiljka.Web.Helper;

namespace Posiljka.Web.ViewModels
{
    public class LoginVM
    {
        public string Username { get; set; }
        [DataType(DataType.Password)]
        public string Password{ get; set; }
        public bool SavePassword { get; set; }
    }
}
