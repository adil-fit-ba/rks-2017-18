using Microsoft.AspNetCore.Mvc;
using Posiljka.Data.EF;

namespace Posiljka.Api.Helper
{
    [ApiController]
    [Route("[controller]/[action]")]
    public class MyWebApiBaseController : ControllerBase
    {
        protected readonly MyContext _db;

        public MyWebApiBaseController(MyContext db)
        {
            _db = db;
        }

       
    }
}
