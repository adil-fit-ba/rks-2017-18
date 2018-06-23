using Microsoft.AspNetCore.Mvc;
using Posiljka.Data.EF;

namespace Posiljka.Web.Helper
{
    [Route("api/[controller]/[action]")]
    public abstract class MyWebApiBaseController : Controller
    {
        protected readonly MyContext _db;

        protected MyWebApiBaseController(MyContext db)
        {
            _db = db;
        }

       
    }
}
