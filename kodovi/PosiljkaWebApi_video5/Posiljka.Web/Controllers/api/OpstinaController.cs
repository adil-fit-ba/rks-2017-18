using System.Linq;
using Microsoft.AspNetCore.Mvc;
using Posiljka.Data.EF;
using Posiljka.Web.Helper.webapi;
using Posiljka.Web.ViewModels.api;

namespace Posiljka.Web.Controllers.api
{
    [MyApiAuthorize]
    public class OpstinaController : MyWebApiBaseController
    {
        public OpstinaController(MyContext db) : base(db)
        {
        }

        [HttpGet]
        public ActionResult<OpstinaPregledVM> GetAll()
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

            return model;
        }
    }
}