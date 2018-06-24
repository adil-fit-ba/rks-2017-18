using Microsoft.AspNetCore.Mvc;
using Posiljka.Data.EF;

namespace Posiljka.Web.Helper.mvc
{
    public abstract class MyMvcBaseController : Controller
    {
        protected readonly MyContext _db;

        protected MyMvcBaseController(MyContext db)
        {
            _db = db;
        }

       
    }
}
