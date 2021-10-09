package com.big.adapter;


import android.view.View;

import com.jakewharton.rxbinding4.internal.Preconditions;

import io.reactivex.rxjava3.android.MainThreadDisposable;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;


public class RxCommonAdapter {

    public static Observable<Integer> onItemClick(CommonAdapter adapter) {
        return new AdapterViewItemClickObservable(adapter);

    }

    private static class AdapterViewItemClickObservable extends Observable<Integer> {
        private CommonAdapter adapter;

        public AdapterViewItemClickObservable(CommonAdapter adapter) {
            this.adapter = adapter;
        }

        @Override
        protected void subscribeActual(@NonNull Observer<? super Integer> observer) {
            if (!Preconditions.checkMainThread(observer)) {
                return;
            }
            Listener listener = new Listener(adapter, observer);
            observer.onSubscribe(listener);
            adapter.setOnItemClickListener(listener);
        }


        private static class Listener extends MainThreadDisposable implements Adapter.OnItemClickListener {
            private CommonAdapter adapter;
            private Observer observer;

            public Listener(CommonAdapter adapter, Observer observer) {
                this.adapter = adapter;
                this.observer = observer;
            }

            @Override
            protected void onDispose() {
                adapter.setOnItemClickListener(null);

            }

            @Override
            public boolean onItemClick(View v, Object info, int position) {
                if (!isDisposed()) {
                    observer.onNext(position);
                }
                return false;
            }
        }
    }

}
