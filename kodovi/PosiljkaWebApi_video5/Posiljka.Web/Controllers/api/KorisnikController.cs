using System;
using System.Collections.Generic;
using System.Linq;
using Microsoft.AspNetCore.Mvc;
using Posiljka.Data.EF;
using Posiljka.Web.Helper;
using Posiljka.Web.ViewModels.api;

namespace Posiljka.Web.Controllers.api
{
    public class KorisnikController : MyWebApiBaseController
    {
        [HttpGet]
        public IActionResult GetAll()
        {
        


            List<KorisnikPregledVM.Row> podaci = _db.Korisnik
                .Select(s => new KorisnikPregledVM.Row
                {
                    id = s.Id,
                    ime = s.Ime,
                    prezime = s.Prezime,
                    opstina = s.Opstina.Drzava.Naziv + " - " + s.Opstina.Naziv
                }).ToList();

            var model = new KorisnikPregledVM
            {
                rows = podaci
            };



            return Ok(model);
        }

        [HttpGet("{name}")]
        public KorisnikPregledVM Find(string name)
        {
            return new KorisnikPregledVM
            {
                rows = _db.Korisnik
                .Where(w => (w.Ime + " " + w.Prezime).StartsWith(name) || (w.Prezime + " " + w.Ime).StartsWith(name))
                .Select(s => new KorisnikPregledVM.Row
                {
                    id = s.Id,
                    ime = s.Ime,
                    prezime = s.Prezime,
                    opstina = s.Opstina.Drzava.Naziv + " - " + s.Opstina.Naziv
                }).ToList()
            };

        }


        public KorisnikController(MyContext db) : base(db)
        {
        }
    }
}
