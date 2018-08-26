package demo.vijay.surya.com.wikipediademo.network;

import com.google.gson.Gson;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkManager implements NetworkManagerClients<Retrofit, OkHttpClient> {

    @Override
    public Retrofit getWikiPediaNetworkClient(OkHttpClient client, Gson gson) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl("https://en.wikipedia.org//")
                .client(client).build();
    }
}
