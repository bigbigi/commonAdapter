package com.big.adapter;

import android.content.Context;
import android.os.Build;
import android.os.Trace;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import com.big.adapter.holder.BaseHolder;
import com.big.adapter.holder.BaseHolderFactory;
import com.big.adapter.holder.HolderFactory;
import com.big.adapter.Adapter.*;


/**
 * Created by dage on 2017/5/03.
 */
public class CommonAdapter<INFO extends Layout> extends RecyclerView.Adapter<BaseHolder<INFO>> implements
        Adapter.OnItemFocusChangeListener<INFO>,
        Adapter.OnItemClickListener<INFO>,
        Adapter.OnItemLongClickListener<INFO> {
    protected Context mContext;
    protected List<INFO> mData = new ArrayList<>();
    protected Adapter.OnItemFocusChangeListener mOnItemFocusChangeListener;
    protected Adapter.OnItemClickListener mOnItemClickListener;
    protected Adapter.OnItemLongClickListener mOnItemLongClickListener;

    public List<INFO> getData() {
        return mData;
    }

    public void setData(List<INFO> data) {
        setData(data, true);
    }

    public void setData(List<INFO> data, boolean notifyChange) {
        Log.d("big", "setData");
        mData = data;
        if (notifyChange) {
            notifyDataSetChanged();
        }
    }

    public void setOnFocusChangeListener(OnItemFocusChangeListener onFocusChangeListener) {
        mOnItemFocusChangeListener = onFocusChangeListener;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mOnItemClickListener = listener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener listener) {
        this.mOnItemLongClickListener = listener;
    }

    private void initContext(Context context) {
        if (mContext == null) {
            mContext = context;
        }
    }

    protected HolderFactory mHolderFactory = new BaseHolderFactory();

    public void setHolderactory(HolderFactory factory) {
        mHolderFactory = factory;
    }

    @Override
    public BaseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d("big", "onCreateViewHolder:" + viewType);
        initContext(parent.getContext());
        BaseHolder holder = mHolderFactory.create(parent, viewType);
        return holder.setOnItemClickListener(this)
                .setOnItemFocusChangeListener(this);
    }


    @Override
    public void onBindViewHolder(final BaseHolder holder, final int position) {
        final INFO info = mData.get(position);
     /*   ViewAdapter.LayoutParams lp = holder.itemView.getLayoutParams();
        if (holder.canFitHeight(info)
                && info.getHeight() != 0
                && lp.height != ScreenParameter.getFitHeight(mContext, info.getHeight())) {
            if (info.getHeight() < 0) {
                lp.height = info.getHeight();
            } else {
                lp.height = ScreenParameter.getFitHeight(mContext, info.getHeight());
            }
            holder.itemView.setLayoutParams(lp);
        }*/
        if (Build.VERSION.SDK_INT >= 18) {
            Trace.beginSection("big");
        }
        holder.onBindViewHolder(info, position);
        if (Build.VERSION.SDK_INT >= 18) {
            Trace.endSection();
        }

    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    @Override
    public int getItemViewType(int position) {
        return mData.get(position).getLayout();
    }

    @Override
    public boolean onItemClick(View v, INFO info, int position) {
        boolean consume = false;
        if (mOnItemClickListener != null) {
            consume = mOnItemClickListener.onItemClick(v, info, position);
        }
        return consume;
    }

    @Override
    public boolean onItemLongClick(View v, INFO info, int position) {
        if (mOnItemLongClickListener != null) {
            mOnItemLongClickListener.onItemLongClick(v, info, position);
        }
        return false;
    }

    @Override
    public boolean onItemFocusChange(View v, INFO info, int position, boolean hasFocus) {
        if (mOnItemFocusChangeListener != null) {
            mOnItemFocusChangeListener.onItemFocusChange(v, info, position, hasFocus);
        }

        return false;
    }


}
