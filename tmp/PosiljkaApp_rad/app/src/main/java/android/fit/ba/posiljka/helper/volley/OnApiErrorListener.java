package android.fit.ba.posiljka.helper.volley;


public abstract class OnApiErrorListener {
    public static enum ApiErrorType
    {
        CommunicationError, ApiError, ParseError
    }
    public abstract void run(ApiErrorType errorType);
}
