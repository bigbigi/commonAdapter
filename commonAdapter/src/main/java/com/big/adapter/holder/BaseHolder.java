package com.big.adapter.holder;

import android.content.Context;
import android.view.View;

import androidx.annotation.CallSuper;
import androidx.recyclerview.widget.RecyclerView;

import com.big.adapter.Adapter;


public abstract class BaseHolder<T> extends RecyclerView.ViewHolder implements
        Holder<T>,
        Adapter.OnItemClickListener<T>,
        Adapter.OnItemFocusChangeListener<T>,
        Adapter.OnItemLongClickListener<T> {



    public BaseHolder(View itemView) {
        super(itemView);
        mContext = itemView.getContext();
    }


    public Context mContext;
    private boolean isSpecialView;

    public void setSpecialView(boolean specialView) {
        isSpecialView = specialView;
    }

    public boolean canFitHeight(T t) {
        return true;
    }

    /*protected void commonFocusChange(View v, boolean hasFocus) {
        if (hasFocus) {
            AniUtils.aniScale(itemView, 1.0f, 1.06f, 250);
        } else {
            AniUtils.aniScale(itemView, 1.06f, 1.0f, 250);
        }
    }*/

    @CallSuper
    public void onBindViewHolder(final T info, final int position) {
        itemView.setTag(position);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean cusume = false;
                if (mOnItemClickListener != null) {
                    cusume = mOnItemClickListener.onItemClick(v, info, position);
                }
                if (!cusume) {
                    onItemClick(v, info, position);
                }
            }
        });
        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                boolean cusume = false;
                if (mOnItemLongClickListener != null) {
                    cusume = mOnItemLongClickListener.onItemLongClick(v, info, position);
                }
                if (!cusume) {
                    cusume = onItemLongClick(v, info, position);
                }
                return cusume;
            }
        });
        itemView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                /*if (!isSpecialView) {
                    commonFocusChange(v, hasFocus);
                }*/
                boolean cusume = false;
                if (mOnItemFocusChangeListener != null) {
                    cusume = mOnItemFocusChangeListener.onItemFocusChange(v, info, position, hasFocus);
                }
                if (!cusume) {
                    onItemFocusChange(v, info, position, hasFocus);
                }
            }
        });
    }

    private Adapter.OnItemClickListener mOnItemClickListener;
    private Adapter.OnItemFocusChangeListener mOnItemFocusChangeListener;
    private Adapter.OnItemLongClickListener mOnItemLongClickListener;

    public BaseHolder setOnItemClickListener(Adapter.OnItemClickListener listener) {
        mOnItemClickListener = listener;
        return this;
    }

    public BaseHolder setOnItemLongClickListener(Adapter.OnItemLongClickListener listener) {
        mOnItemLongClickListener = listener;
        return this;
    }

    public BaseHolder setOnItemFocusChangeListener(Adapter.OnItemFocusChangeListener listener) {
        mOnItemFocusChangeListener = listener;
        return this;
    }

    @Override
    public boolean onItemFocusChange(View v, T info, int position, boolean hasFocus) {
        return false;
    }

    @Override
    public boolean onItemClick(View v, T info, int position) {
        return false;
    }

    @Override
    public boolean onItemLongClick(View v, T info, int position) {
        return true;
    }
}
