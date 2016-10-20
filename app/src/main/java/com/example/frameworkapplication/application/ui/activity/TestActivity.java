package com.example.frameworkapplication.application.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.example.foxconn.frameworkapplication.R;

import org.xutils.x;

/**
 * Created by Administrator on 2016/10/19 0019.
 */

public class TestActivity extends BaseActivity{
    @Override
    public void widgetClick(View view) {

    }

    @Override
    public void initParams(Bundle params) {
        x.Ext.init(this.getApplication());
    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.layout_test;
    }

    @Override
    public void initView(View view) {

    }

    @Override
    public void doBusiness(Context mContext) {

    }
}
