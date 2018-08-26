package demo.vijay.surya.com.wikipediademo.DI;

import android.content.Context;
import dagger.Module;
import dagger.Provides;
import demo.vijay.surya.com.wikipediademo.ApplicationController;
import demo.vijay.surya.com.wikipediademo.database.DatabaseHelper;

@Module
public class ApplicationModule {

    private ApplicationController mApplicationController;

    public ApplicationModule(ApplicationController appController) {
        mApplicationController = appController;
    }

    @Provides
    Context provideContext() {
        return mApplicationController.getApplicationContext();
    }

    @Provides
    DatabaseHelper getDataBaseHelperInstance(Context mContext) {
        return new DatabaseHelper(mContext);
    }
}
