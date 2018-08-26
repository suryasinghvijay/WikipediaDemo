package demo.vijay.surya.com.wikipediademo.interfaces;

import demo.vijay.surya.com.wikipediademo.models.Response;

public interface HomeView extends Imvp {
    void updateRecycleViewOnResposneSuccess(Response response);
    void noMatchFound();
    void onUpdateFailure();
}
