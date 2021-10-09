package com.big.adapter;

import android.view.View;

public interface Adapter {
    interface OnItemFocusChangeListener<T> {
        boolean onItemFocusChange(View v, T info, int position, boolean hasFocus);
    }

    interface OnItemClickListener<T> {
        boolean onItemClick(View v, T info, int position);
    }

    interface OnItemLongClickListener<T> {
        boolean onItemLongClick(View v, T info, int position);
    }

    interface Layout {
        int getLayout();
    }
}
