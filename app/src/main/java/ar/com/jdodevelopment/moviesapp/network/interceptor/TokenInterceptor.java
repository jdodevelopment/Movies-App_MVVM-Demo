package ar.com.jdodevelopment.moviesapp.network.interceptor;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class TokenInterceptor implements Interceptor {


    private static final String TOKEN_PREFIX = "Bearer ";
    private static final String TOKEN_PARAMETER_NAME = "Authorization";
    private static final String TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJiODkzNjU1ZDdjMjE4MDI2M2E0YjM0ZDY1MDFmMmY0ZSIsInN1YiI6IjVlNGIzZTQwMzU4MTFkMDAxMzRiNDhlYyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.NwuCEJceHa3mGcadgBwEIs2v2VibBUbFR1QYnX3T9Rw";


    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {
        Request request = chain.request();

        String authorizationHeader = TOKEN_PREFIX + TOKEN;
        Request.Builder builder = new Request.Builder(request)
                .header(TOKEN_PARAMETER_NAME, authorizationHeader);
        return chain.proceed(builder.build());
    }


}
