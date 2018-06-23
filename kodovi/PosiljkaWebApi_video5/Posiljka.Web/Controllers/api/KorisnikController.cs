﻿using System;
using System.Collections.Generic;
using System.Linq;
using Microsoft.AspNetCore.Mvc;
using Posiljka.Data.EF;
using Posiljka.Data.EntityModels;
using Posiljka.Web.Helper;
using Posiljka.Web.ViewModels.api;

namespace Posiljka.Web.Controllers.api
{
    public class KorisnikController : MyWebApiBaseController
    {
        [HttpGet]
        public KorisnikPregledVM Find()
        {
            return Find("");
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

        [HttpPost]
        public KorisnikPregledVM.Row Add([FromBody]KorisnikAddVM input)
        {
            Korisnik newKorisnik = new Korisnik
            {
                Ime = input.ime,
                Prezime = input.prezime,
                OpstinaID = input.opstinaId,
                KorisnickiNalog = new KorisnickiNalog
                {
                    KorisnickoIme = input.ime + "." + input.prezime,
                    Lozinka = "test"
                }
            };
            _db.Korisnik.Add(newKorisnik);
            _db.SaveChanges();


            return _db.Korisnik
                    .Where(w => w.Id == newKorisnik.Id)
                    .Select(s => new KorisnikPregledVM.Row
                    {
                        id = s.Id,
                        ime = s.Ime,
                        prezime = s.Prezime,
                        opstina = s.Opstina.Drzava.Naziv + " - " + s.Opstina.Naziv
                    })
                    .Single();
        }

        public KorisnikController(MyContext db) : base(db)
        {
        }
    }
}
