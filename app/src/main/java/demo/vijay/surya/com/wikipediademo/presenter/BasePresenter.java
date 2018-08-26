package demo.vijay.surya.com.wikipediademo.presenter;

import demo.vijay.surya.com.wikipediademo.interfaces.IPresenter;
import demo.vijay.surya.com.wikipediademo.interfaces.Imvp;
import rx.subscriptions.CompositeSubscription;

public class BasePresenter<T extends Imvp> implements IPresenter<T> {
    private Imvp mImvp;
    private CompositeSubscription mSubscriptions;

    public CompositeSubscription getSubscription() {
        return mSubscriptions;
    }

    public Imvp getImvp() {
        return mImvp;
    }

    @Override
    public void attachView(T iMvpView) {
        mImvp = iMvpView;
        mSubscriptions = new CompositeSubscription();
    }

    @Override
    public void detachView() {
        mImvp = null;
        if (!mSubscriptions.isUnsubscribed()) {
            mSubscriptions.unsubscribe();
        }
    }
}
