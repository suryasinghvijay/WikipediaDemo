package demo.vijay.surya.com.wikipediademo.views;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;

import com.jakewharton.rxbinding.widget.RxTextView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import demo.vijay.surya.com.wikipediademo.Adapter.HomeAdapter;
import demo.vijay.surya.com.wikipediademo.ApplicationController;
import demo.vijay.surya.com.wikipediademo.DI.DaggerHomeComponent;
import demo.vijay.surya.com.wikipediademo.DI.HomeComponent;
import demo.vijay.surya.com.wikipediademo.DI.HomeModule;
import demo.vijay.surya.com.wikipediademo.R;
import demo.vijay.surya.com.wikipediademo.databinding.ActivityHomeBinding;
import demo.vijay.surya.com.wikipediademo.interfaces.HomeAdapterCommunicator;
import demo.vijay.surya.com.wikipediademo.interfaces.HomeView;
import demo.vijay.surya.com.wikipediademo.models.PagesItem;
import demo.vijay.surya.com.wikipediademo.models.Response;
import demo.vijay.surya.com.wikipediademo.presenter.HomePresenter;
import rx.android.schedulers.AndroidSchedulers;

public class HomeActivity extends AppCompatActivity implements HomeView, HomeAdapterCommunicator {

    public static final String PERSON_NAME = "name";
    public static final String WIKI_PEDIA_URL = "https://en.wikipedia.org/wiki/";
    public static final Long SEARCH_DEBOUNCE_LIMIT = 1500L;

    @Inject
    HomePresenter mHomePresenter;

    private HomeComponent mHomeComponent;
    private HomeAdapter mHomeAdapter;
    private List<PagesItem> resposneData = new ArrayList<>();
    private List<String> historyData = new ArrayList<>();
    private ArrayAdapter<String> adapter = null;
    private ActivityHomeBinding mHomeBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHomeBinding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        mHomeComponent = DaggerHomeComponent.builder().
                applicationComponent(ApplicationController.getInstance().getApplicationComponent())
                .homeModule(new HomeModule()).build();
        mHomeComponent.inject(this);
        mHomePresenter.attachView(this);
        mHomeBinding.include.imageView.setOnClickListener(view -> {
            mHomeBinding.include.editText.setText("");
            resposneData.clear();
            mHomeAdapter.addDataToList(resposneData, true);
        });
        setHomeAdapter();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            mHomeBinding.include.editText.setTextColor(getColor(R.color.color546E7A));
        } else {
            mHomeBinding.include.editText.setTextColor(ContextCompat.getColor(mHomeBinding.include.editText.getContext(),
                    R.color.color546E7A));
        }
        if (null != getWindow()){
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
            mHomeBinding.include.editText.setCursorVisible(false);
        }
        historyData = mHomePresenter.getDataInDataBase();
        adapter = new ArrayAdapter<String>
                (this, R.layout.drop_down_layout, historyData);
        mHomeBinding.include.editText.setAdapter(adapter);
        RxTextView.textChanges(mHomeBinding.include.editText).debounce(SEARCH_DEBOUNCE_LIMIT, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread()).subscribe(charSequence -> {
            if (TextUtils.isEmpty(charSequence)) {
                mHomeBinding.tvEmptySearchResult.setVisibility(View.VISIBLE);
                mHomeBinding.rvRecycleView.setVisibility(View.GONE);
            } else {
                if (mHomeBinding.include.editText.isPopupShowing()) {
                    mHomeBinding.include.editText.dismissDropDown();
                }
                resposneData.clear();
                showProgressDialog();
                mHomeBinding.tvEmptySearchResult.setVisibility(View.GONE);
                mHomeBinding.rvRecycleView.setVisibility(View.VISIBLE);
                mHomePresenter.callHomeApi(charSequence.toString(), 20);
            }
        });
    }

    private void setHomeAdapter() {
        if (null == mHomeAdapter) {
            mHomeAdapter = new HomeAdapter(resposneData, this);
        }
    }

    @Override
    public void updateRecycleViewOnResposneSuccess(Response response) {
        dismissProgressDialog();
        mHomeBinding.rvRecycleView.setFocusable(true);
        if (null != getWindow()){
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
            mHomeBinding.include.editText.setCursorVisible(false);
        }
        if (null != response.getQuery().getPages()) {
            mHomeBinding.rvRecycleView.setLayoutManager(new LinearLayoutManager(this));
            mHomeAdapter.addDataToList(response.getQuery().getPages(), true);
            mHomeBinding.rvRecycleView.setAdapter(mHomeAdapter);
        }
    }

    @Override
    public void noMatchFound() {
        mHomeBinding.rvRecycleView.setVisibility(View.GONE);
        dismissProgressDialog();
        Snackbar.make(mHomeBinding.getRoot(), getString(R.string.no_match_found), Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void onUpdateFailure() {
        dismissProgressDialog();
        Snackbar.make(mHomeBinding.getRoot(), getString(R.string.no_internet), Snackbar.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        mHomePresenter.detachView();
        mHomeComponent = null;
        super.onDestroy();
    }

    private void showProgressDialog() {
        if (!isFinishing()) {
            mHomeBinding.progressBar.setVisibility(View.VISIBLE);
        }
    }

    private void dismissProgressDialog() {
        mHomeBinding.progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onItemCLicked(String personName) {
        historyData.add(personName);
        if (null != adapter){
            adapter.notifyDataSetChanged();
        }
        mHomePresenter.addDataToDataBase(personName);
        startActivity((new Intent(this, CustomWebViewActivity.class)).putExtra(PERSON_NAME, personName));
    }
}
