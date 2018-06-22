using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Posiljka.Api.Helper;
using Posiljka.Api.ViewModels;
using Posiljka.Data.EF;

namespace Posiljka.Api.Controllers
{
    public class KorisnikController : MyWebApiBaseController
    {
        [HttpGet]
        public ActionResult<KorisnikPregledVM> GetAll()
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



            return model;
        }

        [HttpGet("{name}")]
        public ActionResult<KorisnikPregledVM> Find(string name)
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
                    opstina = s.Opstina.Drzava + " - " + s.Opstina.Naziv
                }).ToList()
            };

        }


        public KorisnikController(MyContext db) : base(db)
        {
        }
    }
}
