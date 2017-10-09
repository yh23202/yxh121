package com.yangxiaohe.yangshop.controller;

import android.content.Context;
import android.view.View;

/**
 * 作者：Administrator on 2017/10/6 01:39
 * 邮箱：281893416@qq.com
 */
public abstract class BaseController {
    protected Context mContext;
    protected View mRootView;
    public BaseController(Context context) {
        this.mContext=context;
        mRootView=initView(context);
    }

    public View getRootView() {
        return mRootView;
    }

    protected abstract View initView(Context context);
    public void initData(){

    }
}
