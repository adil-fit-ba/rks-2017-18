using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Posiljka.Data.EF;
using Posiljka.Data.EntityModels;
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
            //if (DateTime.Now.DayOfWeek != DayOfWeek.Friday)
            //    return StatusCode(500, "Možete pristupiti samo petkom.");

            var model = new PosiljkaPregledVM
            {
                rows = _db.Posiljka
                    .OrderByDescending(s=>s.Id)
                    .Select(s => new PosiljkaPregledVM.Row
                    {
                        brojPosiljke = s.Id,
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

        [Route("{brojPosiljke}")]
        public IActionResult Remove(int brojPosiljke)
        {
            PosiljkaRecord x = _db.Posiljka.Find(brojPosiljke);
            if (x != null)
            {
                _db.Posiljka.Remove(x);
                _db.SaveChanges();
            }
            return Ok();
        }

        [HttpPost]
        public int Add([FromBody] PosiljkaAddVM input)
        {
            PosiljkaRecord newPosiljka = new PosiljkaRecord()
            {
                Iznos = input.iznos,
                Masa = input.masa,
                Napomena = input.napomena,
                PlacaPouzecem = input.placaPouzecem,
                KorisnikID = input.korisnikPrimaocId
            };
            _db.Add(newPosiljka);
            _db.SaveChanges();
            return 0;
        }
    }
}