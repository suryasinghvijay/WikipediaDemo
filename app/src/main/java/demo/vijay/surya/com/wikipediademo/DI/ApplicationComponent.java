package demo.vijay.surya.com.wikipediademo.DI;

import dagger.Component;
import demo.vijay.surya.com.wikipediademo.ApplicationController;
import demo.vijay.surya.com.wikipediademo.database.DatabaseHelper;
import retrofit2.Retrofit;

@Component(modules = {ApplicationModule.class, NetworkModule.class})
public interface ApplicationComponent {

    Retrofit wikiPediaRetrofit();

    DatabaseHelper getDataBaseHelperInstance();

    void inject(ApplicationController mApplicationController);
}
