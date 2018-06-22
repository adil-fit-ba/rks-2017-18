using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Posiljka.Data.EF;
using Posiljka.Web.Helper;
using Posiljka.Web.ViewModels;
using Posiljka.Web.ViewModels.api;

namespace Posiljka.Web.Controllers
{
    public class PosiljkaController : MyWebApiBaseController
    {
        public PosiljkaController(MyContext db) : base(db)
        {
        }

        public IActionResult Index()
        {
            if (DateTime.Now.DayOfWeek != DayOfWeek.Friday)
                return StatusCode(500, "Možete pristupiti samo petkom.");

            var model = new PosiljkaPregledVM
            {
                rows = _db.Posiljka
                    .Select(s => new PosiljkaPregledVM.Row
                    {
                        brojPosiljke = s.BrojPosiljke,
                        iznos = s.Iznos,
                        masa = s.Masa,
                        napomena = s.Napomena,
                        placaPouzecem = s.PlacaPouzecem,
                        primaocAdresa = s.Korisnik.Opstina.Drzava.Naziv + " " + s.Korisnik.Opstina.Naziv,
                        primaocImePrezime = s.Korisnik.Ime + " " + s.Korisnik.Prezime

                    }).ToList()
            };


            return Ok(model);
        }
    }
}