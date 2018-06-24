using System.Linq;
using Microsoft.AspNetCore.Mvc;
using Posiljka.Data.EF;
using Posiljka.Data.EntityModels;
using Posiljka.Web.Helper;
using Posiljka.Web.Helper.mvc;
using Posiljka.Web.ViewModels;

namespace Posiljka.Web.Controllers
{
    public class AutentifikacijaController : MyMvcBaseController
    {

        public IActionResult Index()
        {
            return View(new LoginVM()
            {
                SavePassword = true,
            });
        }

        public IActionResult Login(LoginVM input)
        {
            KorisnickiNalog korisnik = _db.KorisnickiNalog
                .SingleOrDefault(x => x.KorisnickoIme == input.Username /*&& x.Lozinka == input.password*/);

            if (korisnik == null)
            {
                TempData["error_poruka"] = "pogrešan username ili password";
                return View("Index", input);
            }

            HttpContext.SetLogiraniKorisnik(korisnik, input.SavePassword);

            return RedirectToAction("Index", "Home");
        }

        public IActionResult Logout()
        {

            return RedirectToAction("Index");
        }

        public AutentifikacijaController(MyContext db) : base(db)
        {
        }
    }
}