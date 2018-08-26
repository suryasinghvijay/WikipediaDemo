package demo.vijay.surya.com.wikipediademo.DI;

import dagger.Component;
import demo.vijay.surya.com.wikipediademo.views.HomeActivity;

@Component(dependencies = {ApplicationComponent.class}, modules = {HomeModule.class})
public interface HomeComponent {

    void inject(HomeActivity mHomeActivity);

}
