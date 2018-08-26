package demo.vijay.surya.com.wikipediademo.views;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import demo.vijay.surya.com.wikipediademo.R;
import demo.vijay.surya.com.wikipediademo.databinding.ActivityCustomWebViewBinding;

import static demo.vijay.surya.com.wikipediademo.views.HomeActivity.WIKI_PEDIA_URL;

public class CustomWebViewActivity extends AppCompatActivity {

    ActivityCustomWebViewBinding mActivityCustomWebViewBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String name = getIntent().getStringExtra(HomeActivity.PERSON_NAME);
        String url = WIKI_PEDIA_URL+ name;
        mActivityCustomWebViewBinding = DataBindingUtil.setContentView(this, R.layout.activity_custom_web_view);
        initProgressDialog();
        mActivityCustomWebViewBinding.webView.getSettings().setBuiltInZoomControls(true);
        mActivityCustomWebViewBinding.webView.loadUrl(url);
        mActivityCustomWebViewBinding.webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                dismissProgressDialog();
            }
        });
    }

    private void initProgressDialog() {
        if (!isFinishing()) {
            mActivityCustomWebViewBinding.progressBar.setVisibility(View.VISIBLE);
        }
    }

    private void dismissProgressDialog() {
        mActivityCustomWebViewBinding.progressBar.setVisibility(View.GONE);
    }
}
