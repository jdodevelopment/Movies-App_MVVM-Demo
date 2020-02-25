package ar.com.jdodevelopment.moviesapp.network;

import java.util.concurrent.TimeUnit;

import ar.com.jdodevelopment.moviesapp.network.interceptor.TokenInterceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
//import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitServiceGenerator {


    private static final String SERVER_URL = "https://api.themoviedb.org/4/";
    private static final int TIMEOUT_SECONDS = 20;


    private static Retrofit buildRetrofit() {
        OkHttpClient okHttpClient = buildOkHttpClient();
        return new Retrofit.Builder()
                .baseUrl(SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create())
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();
    }


    private static OkHttpClient buildOkHttpClient() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.level(HttpLoggingInterceptor.Level.BODY);
        TokenInterceptor tokenInterceptor = new TokenInterceptor();
        return new OkHttpClient.Builder()
                .connectTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .readTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .writeTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .addInterceptor(httpLoggingInterceptor)
                .addInterceptor(tokenInterceptor)
                .build()
                ;
    }

    public static <S> S create(Class<S> serviceClass) {
        return buildRetrofit().create(serviceClass);
    }


//    public static String getErrorMessage(Response response) {
//        ResponseBody responseBody = response.errorBody();
//        if(responseBody == null)
//            return "No hay información disponible.";
//
//        try {
//            return responseBody.string();
//        } catch (IOException e) {
//            e.printStackTrace();
//            return "No hay información disponible.";
//        }
//    }
}
