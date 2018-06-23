using System;
using System.Collections.Generic;
using System.Linq;
using Microsoft.AspNetCore.Mvc;
using Posiljka.Data.EF;
using Posiljka.Data.EntityModels;
using Posiljka.Web.Helper;
using Posiljka.Web.ViewModels.api;

namespace Posiljka.Web.Controllers.api
{
    public class AutentifikacijaController : MyWebApiBaseController
    {
        [HttpPost]
        public ActionResult<AutentifikacijaResultVM> LoginCheck([FromBody] AutentifikacijaLoginPostVM input)
        {
            string token = Guid.NewGuid().ToString();

            AutentifikacijaResultVM model = _db.Korisnik
                .Where(w=>w.KorisnickiNalog.KorisnickoIme == input.Username && w.KorisnickiNalog.Lozinka == input.Password)
                .Select(s => new AutentifikacijaResultVM
                {
                    ime = s.Ime,
                    korisnickiNalogId = s.KorisnickiNalogId,
                    prezime = s.Prezime,
                    username = s.KorisnickiNalog.KorisnickoIme,
                    token = token
                }).SingleOrDefault();

            if (model != null)
            {
                _db.AutorizacijskiToken.Add(new AutorizacijskiToken
                {
                    Vrijednost = model.token,
                    KorisnickiNalogId = model.korisnickiNalogId.Value,
                    VrijemeEvidentiranja = DateTime.Now
                });
                _db.SaveChanges();
            }

            return model;
        }


        public AutentifikacijaController(MyContext db) : base(db)
        {
        }
    }
}
