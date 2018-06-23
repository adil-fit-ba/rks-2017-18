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
    public class OpstinaController : MyWebApiBaseController
    {
        public OpstinaController(MyContext db) : base(db)
        {
        }

        public IActionResult GetAll()
        {
            var model = new OpstinaPregledVM()
            {
                rows = _db.Opstina
                    .Select(s => new OpstinaPregledVM.Row
                    {
                        id = s.Id,
                        naziv = s.Naziv,
                        drzava = s.Drzava.Naziv
                    }).ToList()
            };

            return Ok(model);
        }
    }
}