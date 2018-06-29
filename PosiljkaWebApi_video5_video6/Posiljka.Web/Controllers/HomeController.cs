using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Posiljka.Data.EF;
using Posiljka.Web.Helper;
using Posiljka.Web.Helper.mvc;

namespace Posiljka.Web.Controllers
{
    public class HomeController : MyMvcBaseController
    {
        public IActionResult Index()
        {
            return View();
        }

        public IActionResult TestDB()
        {
            MyDbInit.Run(_db);
            return View(_db);
        }

        public HomeController(MyContext db) : base(db)
        {
        }
    }
}
