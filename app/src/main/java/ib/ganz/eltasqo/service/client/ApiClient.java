package ib.ganz.eltasqo.service.client;

import java.util.concurrent.TimeUnit;

import ib.ganz.eltasqo.helper.Develop;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * Created by limakali on 1/5/2018.
 */

public class ApiClient
{
    public static String IP = "http://192.168.23.1/";
    private static String BASE_URL = IP + "el_tasqo/";
    public static String IMG_DIR = IP + "el_tasqo/images/";

    private static Retrofit retrofit;

    public static Retrofit getRetrofit()
    {
        if (Develop.isIpChange)
        {
            setIp();
        }

        if (retrofit == null || Develop.isIpChange)
        {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(getOkHttpClient())
                    .addConverterFactory(GxonConverterFaxtory.create())
                    .addCallAdapterFactory(MyErrorHandlerFactory.create())
                    .build();
        }

        return retrofit;
    }

    private static void setIp()
    {
        BASE_URL = IP + "wiskul/api/";
        IMG_DIR = IP + "wiskul/admin/images/";
    }

    private static OkHttpClient getOkHttpClient()
    {
        return new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build();
    }
}
