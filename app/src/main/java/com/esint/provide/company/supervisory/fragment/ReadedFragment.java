package com.esint.provide.company.supervisory.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.esint.provide.company.supervisory.R;
import com.esint.provide.company.supervisory.activity.BaseActivity;
import com.esint.provide.company.supervisory.activity.MainActivity;
import com.esint.provide.company.supervisory.adapter.ItemAdapter;
import com.esint.provide.company.supervisory.bean.JsonMessage;
import com.esint.provide.company.supervisory.bean.MessageBean;
import com.esint.provide.company.supervisory.bean.MessageInfo;
import com.esint.provide.company.supervisory.dialog.DetailDialog;
import com.esint.provide.company.supervisory.utils.Constances;
import com.esint.provide.company.supervisory.utils.JsonUtils;
import com.esint.provide.company.supervisory.utils.OKHttpUtils;
import com.esint.provide.company.supervisory.utils.WebConstances;
import com.esint.provide.company.supervisory.view.DividerItemDecoration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by MX on 2017/6/1.
 */

public class ReadedFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, ItemAdapter.ItemOnClickListener{
    private static final String TAG = ReadedFragment.class.getSimpleName();
    //数据对象
    private List<MessageInfo> infoList;
    private RecyclerView mRecyclerView;
    private ItemAdapter mAdapter;
    //刷新
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private LinearLayoutManager mLinearLayoutManager;
    private int lastVisibleItem;

    private String companyUserCode;

    private int pageNum = 1;
    /**
     * 接口返回的总页数
     */
    private int allPageNum = 0;
    /**
     * 接口返回的当前页数
     */
    private int nowPageNum = 1;

    private View loadingView;

    private Handler mHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case OKHttpUtils.WEBFLAG_ERROR:
                    Log.e(TAG, "WEBFLAG_ERROR");
                    mSwipeRefreshLayout.setRefreshing(false);
                    BaseActivity.showToast(mContext, mContext.getResources().getString(R.string.toast_network_error));
                    break;
                case WebConstances.WEBFLAG_GETMESSAGE:
                    Log.e(TAG, "WEBFLAG_GETMESSAGE:" + msg.obj.toString());
                    mSwipeRefreshLayout.setRefreshing(false);
                    try {
                        JsonMessage messageJson = JsonUtils.getInstance().getMessgae(msg.obj.toString());
                        if (null != messageJson && messageJson.getMark().equals(WebConstances.RESULT_MARK_OK)) {
                            // 返回值正确
                            MessageBean messageBean = messageJson.getMsg();
                            allPageNum = Integer.valueOf(messageBean.getPage_all());
                            nowPageNum = Integer.valueOf(messageBean.getPage_now());
                            List<MessageInfo> infos = messageBean.getMesInfo();
                            if (null != infos && infos.size() > 0 && pageNum <= nowPageNum) {
                                pageNum++;
                                infoList.addAll(infos);
                                mAdapter.notifyDataSetChanged();
                            }


                        }
                    } catch (Exception e) {

                    }
                    if(infoList.size() != 0){
                        loadingView.setVisibility(View.GONE);
                        mRecyclerView.setVisibility(View.VISIBLE);
                    }else{
                        loadingView.setVisibility(View.VISIBLE);
                        mRecyclerView.setVisibility(View.GONE);
                        showResult(loadingView, mContext.getResources().getString(R.string.loading_message_empty));
                    }
                    break;
            }
        }
    };

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        MainActivity activity = (MainActivity) context;
        companyUserCode = activity.getCompanyUserCode();
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_readed, null);
        initView(view);
        initEvent();
        mLinearLayoutManager = new LinearLayoutManager(mContext);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(mContext,
                DividerItemDecoration.VERTICAL_LIST));
        // 设置下拉进度的背景颜色，默认就是白色的
        mSwipeRefreshLayout.setProgressBackgroundColorSchemeResource(android.R.color.white);
        // 设置下拉进度的主题颜色
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorAccent, R.color.colorPrimary, R.color.colorPrimaryDark);
        // 这句话是为了，第一次进入页面的时候显示加载进度条
//        mSwipeRefreshLayout.setProgressViewOffset(false, 0, (int) TypedValue
//                .applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources()
//                        .getDisplayMetrics()));
        mSwipeRefreshLayout.setRefreshing(true);
        getMessages(pageNum, Constances.PAGECOUNT);


        mRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView,
                                             int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE
                        && lastVisibleItem + 1 == mAdapter.getItemCount()) {

                    Log.e(TAG, "NOW PAGE NUM " + nowPageNum + "; all page num" + allPageNum);
                    if (nowPageNum < allPageNum) {
                        mSwipeRefreshLayout.setRefreshing(true);
                        getMessages(pageNum, Constances.PAGECOUNT);
                    } else {
                        BaseActivity.showToast(mContext, mContext.getResources().getString(R.string.toast_date_noMore));
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem = mLinearLayoutManager.findLastVisibleItemPosition();
            }

        });
        return view;
    }

    private void initView(View view){
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.srl_readedFragment_refresh);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv_readedFragment_list);

        loadingView = view.findViewById(R.id.ic_readedFragment_loading);
    }

    @Override
    protected void initData() {
        super.initData();
        mContext = getActivity();
        infoList = new ArrayList<MessageInfo>();
        mAdapter = new ItemAdapter(mContext, infoList);
    }

    @Override
    protected void initEvent() {
        super.initEvent();
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mAdapter.setOnClickListener(this);
    }

    @Override
    public void onRefresh() {
        pageNum = 1;
        infoList.clear();
        getMessages(pageNum, Constances.PAGECOUNT);
    }

    private void getMessages(int pageNum, int pageCount) {
        Log.e(TAG, "pageNum is " + pageNum);

        Map<String, String> params = new HashMap<String, String>();
        params.put(WebConstances.PARAMS_GETMESSAGE_STATUS, "1");
        params.put(WebConstances.PARAMS_GETMESSAGE_CODE, companyUserCode);
        params.put(WebConstances.PARAMS_GETMESSAGE_PAGENUM, pageNum + "");
        params.put(WebConstances.PARAMS_GETMESSAGE_PAGECOUNT, pageCount + "");
        OKHttpUtils.getInstance().postRequest(WebConstances.BASE_URL + WebConstances.URL_MESSAGE_GET, params, mHandler, WebConstances.WEBFLAG_GETMESSAGE);
    }

    @Override
    public void onItemClick(View v, final int pos) {
        DetailDialog dialog = new DetailDialog(mContext, infoList.get(pos), 1);
        dialog.setCanceledOnTouchOutside(false);

        dialog.show();
    }
}
