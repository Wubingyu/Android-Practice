package com.example.diarylist.DataBinding;

import android.databinding.ObservableField;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

public class mvvmUtils {

    public static <T> Observable toObservable(ObservableField<T> field){

        return Observable.create(emitter -> {
            T initialValue = field.get();
            if (initialValue != null) {
                emitter.onNext(initialValue);
            }

            //如果发生变化，就进行回调
            android.databinding.Observable.OnPropertyChangedCallback changedCallback = new android.databinding.Observable.OnPropertyChangedCallback() {
                @Override
                public void onPropertyChanged(android.databinding.Observable sender, int propertyId) {
                    emitter.onNext(field.get());
                }
            };
            field.addOnPropertyChangedCallback(changedCallback);

            emitter.setCancellable(() -> field.removeOnPropertyChangedCallback(changedCallback));
        });

//        return Observable.create(new ObservableOnSubscribe<T>() {
//            @Override
//            public void subscribe(ObservableEmitter<T> emitter) throws Exception {
//                T initialValue = field.get();
//                if (initialValue != null) {
//                    emitter.onNext(initialValue);
//                }
//
//                //如果发生变化，就进行回调
//                android.databinding.Observable.OnPropertyChangedCallback changedCallback = new android.databinding.Observable.OnPropertyChangedCallback() {
//                    @Override
//                    public void onPropertyChanged(android.databinding.Observable sender, int propertyId) {
//                        emitter.onNext(field.get());
//                    }
//                };
//                field.addOnPropertyChangedCallback(changedCallback);
//
//                emitter.setCancellable(() -> field.removeOnPropertyChangedCallback(changedCallback));
//            }
//        });
    }

}
