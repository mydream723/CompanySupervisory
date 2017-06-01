package com.esint.provide.company.supervisory.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.esint.provide.company.supervisory.R;
import com.esint.provide.company.supervisory.adapter.ItemAdapter;
import com.esint.provide.company.supervisory.bean.OrderInfo;
import com.esint.provide.company.supervisory.utils.Constances;
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

public class UnReadFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {
    private static final String TAG = UnReadFragment.class.getSimpleName();
    //数据对象
    private List<OrderInfo> infoList;
    private RecyclerView mRecyclerView;
    private ItemAdapter mAdapter;
    //刷新
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private LinearLayoutManager mLinearLayoutManager;
    private int lastVisibleItem;

    private int pageNum = 1;

    private Handler mHandler = new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case OKHttpUtils.WEBFLAG_ERROR:
                    Log.e(TAG, "WEBFLAG_ERROR");
                    break;
                case WebConstances.WEBFLAG_GETMESSAGE:
                    Log.e(TAG, "WEBFLAG_GETMESSAGE:" + msg.obj.toString());
                    break;
            }
        }
    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_unread, null);
        initView(view);
        initEvent();
        mLinearLayoutManager = new LinearLayoutManager(mContext);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(mContext,
                DividerItemDecoration.VERTICAL_LIST));

        // 这句话是为了，第一次进入页面的时候显示加载进度条
        mSwipeRefreshLayout.setProgressViewOffset(false, 0, (int) TypedValue
                .applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources()
                        .getDisplayMetrics()));




        mRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView,
                                             int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE
                        && lastVisibleItem + 1 == mAdapter.getItemCount()) {
                    mSwipeRefreshLayout.setRefreshing(true);
                    // 此处在现实项目中，请换成网络请求数据代码，sendRequest .....
//                    handler.sendEmptyMessageDelayed(0, 3000);
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
        mSwipeRefreshLayout = (SwipeRefreshLayout)view.findViewById(R.id.srl_unReadFragment_refresh);
        mRecyclerView = (RecyclerView)view.findViewById(R.id.rv_unReadFragment_list);
    }

    @Override
    protected void initData() {
        super.initData();
        mContext = getActivity();

        setTestData();

        mAdapter = new ItemAdapter(mContext, infoList);
    }

    @Override
    protected void initEvent() {
        super.initEvent();
        mSwipeRefreshLayout.setOnRefreshListener(this);
    }

    private void setTestData(){
        infoList = new ArrayList<OrderInfo>();

        OrderInfo info = new OrderInfo();
        info.setOrderType(1);
        info.setOrderNum("20170517196547");
        info.setOrderTime("2017年5月17日 12:24:32");
        infoList.add(info);

        info = new OrderInfo();
        info.setOrderType(1);
        info.setOrderNum("20170517991254");
        info.setOrderTime("2017年5月17日 14:20:52");
        infoList.add(info);

        info = new OrderInfo();
        info.setOrderType(2);
        info.setOrderNum("20170524196547");
        info.setOrderTime("2017年5月24日 14:26:02");
        infoList.add(info);

        info = new OrderInfo();
        info.setOrderType(3);
        info.setOrderNum("20170521132584");
        info.setOrderTime("2017年5月21日 14:30:56");
        infoList.add(info);

        info = new OrderInfo();
        info.setOrderType(3);
        info.setOrderNum("20170522365847");
        info.setOrderTime("2017年5月22日 09:10:32");
        infoList.add(info);

        info = new OrderInfo();
        info.setOrderType(1);
        info.setOrderNum("20170523196547");
        info.setOrderTime("2017年5月23日 10:54:24");
        infoList.add(info);


    }

    @Override
    public void onRefresh() {
        getMessages(pageNum, Constances.PAGECOUNT);
    }

    private void getMessages(int pageNum, int pageCount){
        Map<String, String> params = new HashMap<String, String>();
        params.put(WebConstances.PARAMS_GETMESSAGE_STATUS, 1 + "");
        params.put(WebConstances.PARAMS_GETMESSAGE_CODE, "331");
        params.put(WebConstances.PARAMS_GETMESSAGE_PAGENUM, pageNum + "");
        params.put(WebConstances.PARAMS_GETMESSAGE_PAGECOUNT, pageCount + "");
        OKHttpUtils.getInstance().postRequest(WebConstances.BASE_URL + WebConstances.URL_MESSAGE_GET, params,mHandler, WebConstances.WEBFLAG_GETMESSAGE );
    }

}
