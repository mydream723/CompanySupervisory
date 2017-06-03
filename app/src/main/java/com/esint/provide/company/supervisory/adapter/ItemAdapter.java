package com.esint.provide.company.supervisory.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.esint.provide.company.supervisory.R;
import com.esint.provide.company.supervisory.bean.MessageInfo;
import com.esint.provide.company.supervisory.bean.OrderInfo;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by MX on 2017/5/8.
 */

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.MyViewHolder> {
    private static final String TAG = ItemAdapter.class.getSimpleName();
    private Context mContext;
    private List<MessageInfo> orderList;

    private ItemOnClickListener mListener;

    public ItemAdapter(Context context, List<MessageInfo> orderList) {
        mContext = context;
        this.orderList = orderList;
    }

    public interface ItemOnClickListener {
        void onItemClick(View v, int pos);
    }

    public void setOnClickListener(ItemOnClickListener listener){
        mListener = listener;
    }


    @Override
    public ItemAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_order, parent,
                false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ItemAdapter.MyViewHolder holder, int position) {
        String type = orderList.get(position).getE_type();
        String typeName = "";
        int typeImg = 0;
        typeName =  orderList.get(position).getE_type_name();
        switch (type){
            case OrderInfo.TYPE_SHOP:
                //商铺
                typeImg = R.drawable.ic_type_shop;
                break;
            case OrderInfo.TYPE_SOCIAL:
                //社工
                typeImg = R.drawable.ic_type_social;
                break;
            case OrderInfo.TYPE_CALL:
                //呼叫
                typeImg = R.drawable.ic_type_call;
                break;
            default:
                typeImg = R.drawable.ic_type_default;
                break;
        }
        Picasso.with(mContext).load(typeImg).placeholder(R.drawable.ic_type_default).error(R.drawable.ic_error).into(holder.typeImageView);
        holder.typeTextView.setText(typeName);
        holder.timeTextView.setText(orderList.get(position).getE_add_time());
        holder.itemView.setOnClickListener(new ItemClick(holder.getLayoutPosition()));
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView typeImageView;
        private TextView typeTextView, numTextView, timeTextView;

        public MyViewHolder(View view) {
            super(view);
            typeImageView = (ImageView)view.findViewById(R.id.iv_orderItem_type);
            typeTextView = (TextView)view.findViewById(R.id.tv_orderItem_orderType);
            numTextView = (TextView)view.findViewById(R.id.tv_orderItem_orderNum);
            timeTextView = (TextView)view.findViewById(R.id.tv_orderItem_orderTime);
        }
    }

    class ItemClick implements View.OnClickListener {
        private int position;

        public ItemClick(int position) {
            this.position = position;
        }

        @Override
        public void onClick(View v) {
            if (null != mListener) {
                mListener.onItemClick(v, position);
            }
        }
    }

}
