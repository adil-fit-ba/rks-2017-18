using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Posiljka.Api.Helper;
using Posiljka.Data.EF;

namespace Posiljka.Api.Controllers
{
    public class HomeController : MyWebApiBaseController
    {
        [HttpGet("/")]
        public ActionResult<string> Index()
        {
            _db.Korisnik.Count();
            return "WebAPI Server status: OK";
        }


        public HomeController(MyContext db) : base(db)
        {
        }
    }
}
