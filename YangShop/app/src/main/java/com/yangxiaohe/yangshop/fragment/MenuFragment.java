package com.yangxiaohe.yangshop.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yangxiaohe.yangshop.R;
import com.yangxiaohe.yangshop.bean.NewsCenterBean;

import java.util.List;

/**
 * 作者：Administrator on 2017/10/6 01:29
 * 邮箱：281893416@qq.com
 */
public class MenuFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return inflater.inflate(R.layout.);
         return inflater.inflate(R.layout.menu_fragment,container,false);
    }

    public void setMenuData(List<NewsCenterBean.DataBean> dataBeen) {

    }
}
