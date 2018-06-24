using System;
using Microsoft.AspNetCore.Mvc;
using Posiljka.Data.EF;
using Posiljka.Data.EntityModels;
using Posiljka.Web.Helper.webapi;
using Posiljka.Web.ViewModels.api;
using System.Linq;

namespace Posiljka.Web.Controllers.api
{
    [MyApiAuthorize]
    public class PosiljkaController : MyWebApiBaseController
    {
        public PosiljkaController(MyContext db) : base(db)
        {
        }

        [HttpGet]
        public ActionResult<PosiljkaPregledVM> Index()
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
                        primaocAdresa = s.KorisnikPrimaoc.Opstina.Drzava.Naziv + " " + s.KorisnikPrimaoc.Opstina.Naziv,
                        primaocImePrezime = s.KorisnikPrimaoc.Ime + " " + s.KorisnikPrimaoc.Prezime

                    }).ToList()
            };


            return model;
        }

        [HttpDelete("{brojPosiljke}")]
        public ActionResult Remove(int brojPosiljke)
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
                KorisnikPrimaocID = input.korisnikPrimaocId,

                KorisnikEvidentirao = AuthKorisnik,
                VrijemeEvidentirana = DateTime.Now
            };
            _db.Add(newPosiljka);
            _db.SaveChanges();
            return 0;
        }
    }
}