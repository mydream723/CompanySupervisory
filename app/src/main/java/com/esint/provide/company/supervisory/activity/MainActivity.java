package com.esint.provide.company.supervisory.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.esint.communitytools.bean.CCompanyUser;
import com.esint.communitytools.utils.CommunityHelper;
import com.esint.provide.company.supervisory.R;
import com.esint.provide.company.supervisory.adapter.TabAdapter;
import com.esint.provide.company.supervisory.service.PushCoreService;
import com.esint.provide.company.supervisory.service.PushService;
import com.igexin.sdk.PushManager;

public class MainActivity extends BaseActivity implements TabLayout.OnTabSelectedListener{
    private static final String TAG = MainActivity.class.getSimpleName();
    private Toolbar mToolbar;
    private TabLayout mTabLayout;

    private ViewPager mViewPager;
    private TabAdapter mTabAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();
        initEvent();

        mViewPager.setAdapter(mTabAdapter);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mTabLayout.addOnTabSelectedListener(this);

        PushManager.getInstance().initialize(this.getApplicationContext(), PushCoreService.class);
        PushManager.getInstance().registerPushIntentService(this.getApplicationContext(), PushService.class);
        //获得登陆账号
        if(CommunityHelper.getInstance(mContext).getLoginedCompanyUser() != null){
            CCompanyUser companyUser = CommunityHelper.getInstance(mContext).getLoginedCompanyUser();
            PushManager.getInstance().bindAlias(mContext, companyUser.getBindCode());
        }


    }

    private void initView(){
        mToolbar = (Toolbar)findViewById(R.id.tb_mainActivity_toolbar);
        setSupportActionBar(mToolbar);

        mTabLayout = (TabLayout)findViewById(R.id.tl_mainActivity_tab);
        mViewPager = (ViewPager)findViewById(R.id.vp_mainActivity_content);

        mTabLayout.addTab(mTabLayout.newTab().setText(R.string.tab_unRead));
        mTabLayout.addTab(mTabLayout.newTab().setText(R.string.tab_readed));
        mTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
    }

    @Override
    protected void initData() {
        super.initData();
        mContext = MainActivity.this;
        mTabAdapter = new TabAdapter(getSupportFragmentManager());
    }

    @Override
    protected void initEvent() {
        super.initEvent();
    }


    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        mViewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
