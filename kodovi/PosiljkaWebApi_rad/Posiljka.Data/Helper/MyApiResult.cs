using System;

namespace Posiljka.Data.Helper
{
    public class MyApiResult<T> 
    {
        public String exceptionMessage = "";
        public bool isException = false;
        public int exceptionCode = 0;
        public T value;

        public static MyApiResult<T> Error(int exceptionCode, string exceptionMessage)
        {
            return new MyApiResult<T>
            {
                isException = true,
                exceptionCode = exceptionCode,
                exceptionMessage = exceptionMessage
            };
        }
        public static MyApiResult<T> OK(T value)
        {
            return new MyApiResult<T>
            {
                isException = false,
                value = value
            };
        }

       
    }

}