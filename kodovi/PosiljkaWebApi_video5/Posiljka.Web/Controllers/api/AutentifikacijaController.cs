using System;
using System.Collections.Generic;
using System.Linq;
using Microsoft.AspNetCore.Mvc;
using Posiljka.Data.EF;
using Posiljka.Web.Helper;
using Posiljka.Web.ViewModels.api;

namespace Posiljka.Web.Controllers.api
{
    public class AutentifikacijaController : MyWebApiBaseController
    {
        [HttpPost]
        public IActionResult LoginCheck([FromBody] AutentifikacijaCheckVM input)
        {
            KorisnikPregledVM.Row model = _db.Korisnik
                .Where(w=>w.KorisnickiNalog.KorisnickoIme == input.Username && w.KorisnickiNalog.Lozinka == input.Password)
                .Select(s => new KorisnikPregledVM.Row
                {
                    id = s.Id,
                    ime = s.Ime,
                    prezime = s.Prezime,
                    opstina = s.Opstina.Drzava.Naziv + " - " + s.Opstina.Naziv
                }).SingleOrDefault();


            return Ok(model);
        }


        public AutentifikacijaController(MyContext db) : base(db)
        {
        }
    }
}
