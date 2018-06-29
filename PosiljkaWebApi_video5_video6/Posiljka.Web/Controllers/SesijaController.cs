using System.Linq;
using Microsoft.AspNetCore.Mvc;
using Posiljka.Data.EF;
using Posiljka.Data.EntityModels;
using Posiljka.Web.Helper;
using Posiljka.Web.Helper.mvc;
using Posiljka.Web.ViewModels;

namespace Posiljka.Web.Controllers
{
    [MyMvcAuthorize()]
    public class SesijaController : MyMvcBaseController
    {

        public SesijaController(MyContext db):base(db)
        {
        }

        public IActionResult Index()
        {
            var kId = HttpContext.GetLogiraniKorisnik().Id;
            SesijaIndexVM model = new SesijaIndexVM();
            model.Rows = _db.AutorizacijskiToken
                .Where(x=>x.KorisnickiNalogId == kId)
                .Select(s => new SesijaIndexVM.Row
            {
                VrijemeLogiranja = s.VrijemeEvidentiranja,
                token = s.Vrijednost,
                IpAdresa = s.IpAdresa,
                deviceInfo = s.deviceInfo
            }).ToList();
            model.TrenutniToken = HttpContext.GetTrenutniToken();
            return View(model);
        }

        public IActionResult Obrisi(string token)
        {
            AutorizacijskiToken obrisati =_db.AutorizacijskiToken.FirstOrDefault(x => x.Vrijednost == token);
            if (obrisati != null)
            {
                _db.AutorizacijskiToken.Remove(obrisati);
                _db.SaveChanges();
            }
            return RedirectToAction("Index");
        }
    }
}