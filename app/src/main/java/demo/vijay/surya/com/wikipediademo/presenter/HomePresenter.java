package demo.vijay.surya.com.wikipediademo.presenter;


import android.util.Log;

import java.util.List;

import javax.inject.Inject;

import demo.vijay.surya.com.wikipediademo.database.DatabaseHelper;
import demo.vijay.surya.com.wikipediademo.interfaces.HomeApi;
import demo.vijay.surya.com.wikipediademo.interfaces.HomeView;
import demo.vijay.surya.com.wikipediademo.models.Response;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class HomePresenter extends BasePresenter<HomeView> {

    @Inject
    HomeApi mHomeApi;

    @Inject
    DatabaseHelper mDatabaseHelper;

    @Inject
    HomePresenter() {
    }

    @Override
    public void attachView(HomeView iMvpView) {
        super.attachView(iMvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
    }

    public void callHomeApi(String searchedString, int limit) {
        getSubscription().add(mHomeApi.getWikipediaSearchResponse(searchedString, limit).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Response>() {
                               @Override
                               public void onCompleted() {
                                   // no implementation Required
                               }

                               @Override
                               public void onError(Throwable e) {
                                   if (e.getCause().toString().contains("failed to connect")){
                                       ((HomeView) getImvp()).onUpdateFailure();
                                   }
                               }

                               @Override
                               public void onNext(Response response) {
                                   if (null != response && null != response.getQuery()) {
                                       ((HomeView) getImvp()).updateRecycleViewOnResposneSuccess(response);
                                   } else {
                                       ((HomeView) getImvp()).noMatchFound();
                                   }
                               }
                           }
                ));
    }

    public void addDataToDataBase(String personName) {
        Observable.just(personName).observeOn(Schedulers.io())
                .subscribeOn(Schedulers.io()).subscribe(new Observer<String>() {
            @Override
            public void onCompleted() {
                // no implementation required
            }

            @Override
            public void onError(Throwable e) {
                Log.e("TAG", e.getMessage());
            }

            @Override
            public void onNext(String name) {
                if (mDatabaseHelper.getNotesCount() > 4) {
                    mDatabaseHelper.deleteAllData();
                }
                mDatabaseHelper.insertNote(name);
            }
        });
    }

    public List<String> getDataInDataBase() {
        return mDatabaseHelper.getAllNotes();
    }

    /*private fun handleServerError(e: Throwable?) {
    try {
      val retrofitException = e as RetrofitException
      if (mUtils.isServerMaintenanceError(retrofitException)) {
        mCartView?.maintenanceDialog()
        return
      }
    } catch (ex: Exception) {
      Timber.e("UnHandled Exception %s", ex.message)
    }
    mCartView?.showErrorResponseDialogOrMessage()
  }*/


}
