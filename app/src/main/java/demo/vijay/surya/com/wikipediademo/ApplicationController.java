package demo.vijay.surya.com.wikipediademo;

import android.app.Application;

import demo.vijay.surya.com.wikipediademo.DI.ApplicationComponent;
import demo.vijay.surya.com.wikipediademo.DI.ApplicationModule;
import demo.vijay.surya.com.wikipediademo.DI.DaggerApplicationComponent;


public class ApplicationController extends Application {


    private ApplicationComponent mApplicationComponent;

    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }

    private static ApplicationController mInstance;

    public static ApplicationController getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        mApplicationComponent = DaggerApplicationComponent.builder().applicationModule(new ApplicationModule(this)).build();
        mApplicationComponent.inject(this);
    }


}
