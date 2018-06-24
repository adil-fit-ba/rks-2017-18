using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Http;
using Microsoft.Extensions.DependencyInjection;
using Posiljka.Data.EF;
using Posiljka.Data.EntityModels;

namespace Posiljka.Web.Helper.webapi
{
    public static class MyAuthTokenExtension
    {

        public static KorisnickiNalog GetKorisnikOfAuthToken(this HttpContext httpContext)
        {
            MyContext db = httpContext.RequestServices.GetService<MyContext>();

            string token = httpContext.GetMyAuthToken();
            AutorizacijskiToken autorizacijskiToken = db.AutorizacijskiToken.SingleOrDefault(x => token != null && x.Vrijednost == token);
            return autorizacijskiToken?.KorisnickiNalog;
        }

        public static string GetMyAuthToken(this HttpContext httpContext)
        {
            string token = httpContext.Request.Headers["authToken"];
            return token;
        }
    }
}
