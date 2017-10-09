package com.yangxiaohe.yangshop.controller;

import android.content.Context;
import android.view.View;

import com.yangxiaohe.yangshop.bean.NewsCenterBean;

import java.util.List;

/**
 * 作者：Administrator on 2017/10/6 13:24
 * 邮箱：281893416@qq.com
 */
public class NewsMenuControll extends BaseController {
    public NewsMenuControll(Context mContext, List<NewsCenterBean.DataBean.ChildrenBean> children) {
        super(mContext);
    }

    @Override
    protected View initView(Context context) {
        return null;
    }
}
