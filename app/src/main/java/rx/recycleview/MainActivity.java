package rx.recycleview;


import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.functions.Consumer;

import com.big.adapter.CommonAdapter;
import com.big.adapter.RxCommonAdapter;
import com.big.adapter.holder.BaseHolderFactory;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        BaseHolderFactory.register(R.layout.item_test1, TestHolder.class);
        BaseHolderFactory.register(R.layout.item_test2, TestHolder.class);
        recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));
        CommonAdapter<TestLayoutInfo> adapter = new CommonAdapter<>();
        recyclerView.setAdapter(adapter);
        List<TestLayoutInfo> layoutInfos = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            if (i % 2 == 0) {
                layoutInfos.add(new TestLayoutInfo(R.layout.item_test1));
            } else {
                layoutInfos.add(new TestLayoutInfo(R.layout.item_test2));
            }

        }
        adapter.setData(layoutInfos);

        RxCommonAdapter.onItemClick(adapter)
                .throttleFirst(500, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Throwable {
                        Log.d("big","click call:" + integer);
//                        Toast.makeText(MainActivity.this, "click call:" + integer, Toast.LENGTH_SHORT).show();
                    }
                });

    }
}