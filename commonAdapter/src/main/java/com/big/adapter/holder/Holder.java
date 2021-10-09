package com.big.adapter.holder;

public interface Holder<T> {

    void onBindViewHolder(final T info, final int position);
}
