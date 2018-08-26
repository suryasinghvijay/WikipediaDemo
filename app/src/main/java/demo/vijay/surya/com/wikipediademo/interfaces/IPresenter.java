package demo.vijay.surya.com.wikipediademo.interfaces;

public interface IPresenter<T extends Imvp> {
    void attachView(T iMvpView);

    void detachView();
}
