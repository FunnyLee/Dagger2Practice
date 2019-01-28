package com.funny.geek.ui.zhihu;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.funny.geek.R;
import com.funny.geek.base.RootActivity;
import com.funny.geek.contract.zhihu.ZhihuDetailContract;
import com.funny.geek.model.bean.ZhihuDetailBean;
import com.funny.geek.model.net.ImageHelper;
import com.funny.geek.util.Constants;
import com.funny.geek.util.HtmlUtil;
import com.jakewharton.rxbinding2.view.RxView;
import com.just.agentweb.AgentWebView;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;

/**
 * 详情页面
 */
public class ZhihuDetailActivity extends RootActivity<ZhihuDetailPresenter> implements ZhihuDetailContract.View {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.collapsing_layout)
    CollapsingToolbarLayout mCollapsingLayout;
    @BindView(R.id.app_bar)
    AppBarLayout mAppBar;
    @BindView(R.id.fab)
    FloatingActionButton mFab;
    @BindView(R.id.detail_bar_iv)
    ImageView mDetailBarIv;
    @BindView(R.id.copyright_tv)
    TextView mCopyrightTv;
    @BindView(R.id.agent_webview)
    AgentWebView mAgentWebView;

    private ZhihuDetailBean mZhihuDetailBean;


    public static void start(Context context, int id) {
        Intent starter = new Intent(context, ZhihuDetailActivity.class);
        starter.putExtra("id", id);
        context.startActivity(starter);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_zhihu_detail;
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(ZhihuDetailActivity.this);
    }

    @Override
    protected void initData() {
        int id = getIntent().getIntExtra("id", -1);
        if (id != -1) {
            mPresenter.doLoadData(id);

            //查询是否是喜爱数据
            mPresenter.queryFavorite(String.valueOf(id));
        }
    }

    @Override
    protected void initView() {
        setSupportActionBar(mToolbar);
        //ToolBar左边增加返回箭头
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //返回箭头可用
        getSupportActionBar().setHomeButtonEnabled(true);

        //设置WebView
        WebSettings settings = mAgentWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        mAgentWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
            }
        });
        mAgentWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
    }

    @SuppressLint("CheckResult")
    @Override
    protected void initEvent() {
        RxView.clicks(mFab)
                .throttleFirst(Constants.CLICK_INTERVAL, TimeUnit.SECONDS)
                .subscribe(o -> {
                    if (mZhihuDetailBean != null) {
                        boolean selected = mFab.isSelected();
                        if (selected) {
                            //如果已选中，点击则删除喜爱
                            mPresenter.deleteFavorite(String.valueOf(mZhihuDetailBean.id));
                            mFab.setSelected(false);
                        } else {
                            //如果不选中，点击则添加喜爱
                            mPresenter.addFavorite(mZhihuDetailBean);
                            mFab.setSelected(true);
                        }

                    }
                });
    }

    @Override
    public void onShowContentView(ZhihuDetailBean zhihuDetailBean) {
        mZhihuDetailBean = zhihuDetailBean;
        mCollapsingLayout.setTitle(zhihuDetailBean.title);
        ImageHelper.loadImage(this, zhihuDetailBean.image, mDetailBarIv);
        mCopyrightTv.setText(zhihuDetailBean.image_source);

        //WebView加载Html
        String htmlData = HtmlUtil.createHtmlData(zhihuDetailBean.body, zhihuDetailBean.css, zhihuDetailBean.js);

        mAgentWebView.loadDataWithBaseURL(null, htmlData, "text/html", "UTF-8", null);
    }

    @Override
    public void onShowFavoriteState(boolean state) {
        if (state) {
            mFab.setSelected(true);
        } else {
            mFab.setSelected(false);
        }
    }

}
