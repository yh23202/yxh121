package com.yangxiaohe.yangshop.controller;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

/**
 * 作者：Administrator on 2017/10/6 10:20
 * 邮箱：281893416@qq.com
 */
public class SmartSerViceController extends TabController {
    public SmartSerViceController(FragmentActivity activity) {
        super(activity);
    }

    @Override
    protected View initContentView(Context context) {
        TextView textView = new TextView(context);
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(Color.RED);
        textView.setTextSize(25);
        textView.setText(getClass().getSimpleName());
        return textView;
    }
}
