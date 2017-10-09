package com.yangxiaohe.yangshop.controller;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

/**
 * 作者：Administrator on 2017/10/6 13:25
 * 邮箱：281893416@qq.com
 */
public class SubjectMenuController extends BaseController {
    public SubjectMenuController(Context mContext) {
        super(mContext);
    }

    @Override
    protected View initView(Context context) {
        TextView textView = new TextView(context);
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(Color.RED);
        textView.setTextSize(25);
        textView.setText(getClass().getSimpleName());
        return textView;
    }
}
