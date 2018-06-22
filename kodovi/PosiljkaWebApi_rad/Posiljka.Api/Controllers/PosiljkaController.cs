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
    public class PosiljkaController : MyWebApiBaseController
    {
        [HttpGet]
        public ActionResult<PosiljkaPregledVM> GetAll()
        {

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
                        primaocImePrezime = s.Korisnik.Ime + " " +s.Korisnik.Prezime

                    }).ToList()
                };


            return model;
        }

      


        public PosiljkaController(MyContext db) : base(db)
        {
        }
    }
}
