package demo.vijay.surya.com.wikipediademo.DI;

import com.google.gson.Gson;

import dagger.Module;
import dagger.Provides;
import demo.vijay.surya.com.wikipediademo.network.NetworkManager;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

@Module
public class NetworkModule {

    @Provides
    OkHttpClient provideOkHttpClient() {
        return new OkHttpClient.Builder().build();
    }


    @Provides
    Gson provideGson() {
        return new Gson();
    }

    @Provides
    Retrofit provideWikiPediaRetrofit(OkHttpClient okHttpClient, Gson gson) {
        return new NetworkManager().getWikiPediaNetworkClient(okHttpClient, gson);
    }
}
