package demo.vijay.surya.com.wikipediademo.DI;

import dagger.Module;
import dagger.Provides;
import demo.vijay.surya.com.wikipediademo.interfaces.HomeApi;
import retrofit2.Retrofit;

@Module
public class HomeModule {
    @Provides
    public HomeApi provideIHomeApi(Retrofit retrofit) {
        return retrofit.create(HomeApi.class);
    }
}
