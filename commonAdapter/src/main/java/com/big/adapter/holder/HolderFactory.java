package com.big.adapter.holder;

import android.view.ViewGroup;

public interface HolderFactory {
    BaseHolder create(ViewGroup parent, final int viewType);
}
