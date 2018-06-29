using System;
using System.Collections.Generic;
using System.Linq;
using Posiljka.Data.EntityModels;

namespace Posiljka.Data.EF
{
    public static class MyDbInit
    {
        public static void Run(MyContext _context)
        {
            // Look for any Posiljkas.
            if (_context.Posiljka.Any())
            {
                return; // DB has been seeded
            }
            List<Opstina> opstinas = new List<Opstina>();
            List<Drzava> drzavas = new List<Drzava>();
            List<Korisnik> korisniks = new List<Korisnik>();
            List<PosiljkaRecord> posiljkas = new List<PosiljkaRecord>();


            drzavas.Add(new Drzava {Naziv = "BiH"});
            drzavas.Add(new Drzava {Naziv = "Hrvatska"});
            drzavas.Add(new Drzava {Naziv = "Srbija"});
            drzavas.Add(new Drzava {Naziv = "Japan"});
            drzavas.Add(new Drzava {Naziv = "Katar"});
            drzavas.Add(new Drzava {Naziv = "Mjesec"});

            for (int i = 0; i < 10; i++)
            {
                opstinas.Add(new Opstina { Drzava = drzavas.MyRandom(), Naziv = MyRandomString(5) });
            }
            for (int i = 0; i < 10; i++)
            {
                korisniks.Add(new Korisnik{Ime = MyRandomString(5), Prezime = MyRandomString(5),  Opstina = opstinas.MyRandom(), KorisnickiNalog = new KorisnickiNalog { KorisnickoIme = "Korisnik" + i, Lozinka = "test"} });
            }

            for (int i = 0; i < 20; i++)
            {
                posiljkas.Add(new PosiljkaRecord{KorisnikEvidentirao = korisniks.MyRandom(), VrijemeEvidentirana = DateTime.Now, KorisnikPrimaoc = korisniks.MyRandom(), BrojPosiljke = i, Napomena = "Napomena br " + i, PlacaPouzecem = true, Iznos = random.Next(5, 50), Masa = random.Next(5, 15)});
            }

            _context.Drzava.AddRange(drzavas);
            _context.Opstina.AddRange(opstinas);
            _context.Korisnik.AddRange(korisniks);
            _context.Posiljka.AddRange(posiljkas);
          

            _context.SaveChanges();
        }

        private static string MyRandomString(int length)
        {
            const string chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
            return new string(Enumerable.Repeat(chars, length)
                .Select(s => s[random.Next(s.Length)]).ToArray());
        }


        static readonly Random random = new Random();

        private static T MyRandom<T>(this List<T> lista)
        {
            int r = random.Next(0, lista.Count);
            return lista[r];
        }

    }
}