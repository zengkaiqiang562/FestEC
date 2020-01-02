package com.diabin.festec.example;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.diabin.latte.delegates.LatteDelegate;

/**
 * Created by zengk on 2020/01/02
 */
public class ExampleDelegate extends LatteDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_example;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
