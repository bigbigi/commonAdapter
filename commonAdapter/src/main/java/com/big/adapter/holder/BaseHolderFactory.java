package com.big.adapter.holder;

import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import java.lang.reflect.Constructor;
import java.util.HashMap;


/**
 * Created by big on 2019/10/31
 */
public class BaseHolderFactory implements HolderFactory {
    public static HashMap<Integer, Class<? extends BaseHolder>> mHashMap = new HashMap<>();

    static {
      /*  mHashMap.put(R.layout.ly_common_item_poster_one_242, PostOneHolder.class);
        mHashMap.put(R.layout.ly_common_item_poster_two, PostTwoHolder.class);
        mHashMap.put(R.layout.ly_common_item_poster_three, PostThreeHolder.class);

        mHashMap.put(R.layout.ly_common_item_category_one_112, CategoryOneHolder.class);
        mHashMap.put(R.layout.ly_common_item_category_two_118, CategoryTwoHolder.class);
        mHashMap.put(R.layout.ly_common_item_category_three_176, CategoryThreeRoundHolder.class);

        mHashMap.put(R.layout.ly_common_item_title_one, TitleHolder.class);
        mHashMap.put(R.layout.ly_common_item_title_empty, EmptyHolder.class);

        mHashMap.put(R.layout.item_operate_more, MoreHolder.class);
        mHashMap.put(R.layout.ly_common_item_page, TitlePageHolder.class);
*/
    }

    public static void register(Integer key, Class<? extends BaseHolder> c) {
        if (!mHashMap.containsKey(key)) {
            mHashMap.put(key, c);
        }
    }

    @Override
    public BaseHolder create(ViewGroup parent, int viewType) {
        try {
            View itemView = getItemView(parent, viewType);
            if (itemView != null) {
                Constructor con = mHashMap.get(viewType).getConstructor(new Class[]{View.class});
                return (BaseHolder) con.newInstance(itemView);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static View getItemView(ViewGroup parent, int viewType) {
        View itemView = null;
        try {
            itemView = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
        }
        return itemView;
    }

    public static View getItemView(ViewGroup parent, int viewType, int defaultLayout) {
        View itemView;
        try {
            itemView = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
            itemView = LayoutInflater.from(parent.getContext()).inflate(defaultLayout, parent, false);
        }
        return itemView;
    }
}
