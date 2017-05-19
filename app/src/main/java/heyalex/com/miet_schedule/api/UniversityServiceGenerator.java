package heyalex.com.miet_schedule.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

/*package*/ class UniversityServiceGenerator {

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    public final static String SERVICE_MIET_ENDPOINT = "https://miet.ru";

    private static Gson gson = new GsonBuilder()
            .setLenient()
            .create();

    private static Retrofit.Builder retrofitScheduleBuilder
            = new Retrofit.Builder()
            .baseUrl(SERVICE_MIET_ENDPOINT)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(new GsonBuilder()
                    .setLenient()
                    .create()
            ));

    private static Retrofit.Builder retrofitNewsBuilder
            = new Retrofit.Builder()
            .baseUrl(SERVICE_MIET_ENDPOINT)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(SimpleXmlConverterFactory.create());

    /*package*/ static <S> S createScheduleService(Class<S> serviceClass) {
        Retrofit retrofit = retrofitScheduleBuilder.client(httpClient.build()).build();
        return retrofit.create(serviceClass);
    }

    /*package*/ static <S> S createNewsService(Class<S> serviceClass) {
        Retrofit retrofit = retrofitNewsBuilder.client(httpClient.build()).build();
        return retrofit.create(serviceClass);
    }

}
