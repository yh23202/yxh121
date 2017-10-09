package com.yangxiaohe.yangshop.controller;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.yangxiaohe.yangshop.MainActivity;
import com.yangxiaohe.yangshop.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者：Administrator on 2017/10/6 01:45
 * 邮箱：281893416@qq.com
 */
public abstract class TabController extends BaseController {
    @BindView(R.id.iv_tab_menu)
    ImageView ivTabMenu;
    @BindView(R.id.iv_tab_grid_type)
    protected ImageView ivTabGridType;
    @BindView(R.id.fl_tab_container)
    FrameLayout flTabContainer;
    @BindView(R.id.tv_tab_title)
    TextView tvTabTitle;

    public TabController(Context context) {
        super(context);
    }

    @Override
    protected View initView(Context context) {
       /* LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View inflate = layoutInflater.inflate(context, R.layout.tab_controller, null);*/
        View view = View.inflate(context, R.layout.tab_controller, null);
        ButterKnife.bind(this, view);
        //加载不同部分
        flTabContainer.addView(initContentView(context));
        ivTabMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SlidingMenu menu = ((MainActivity) mContext).getSlidingMenu();
                //打开菜单
                menu.toggle();
            }
        });
        return view;
    }

    protected abstract View initContentView(Context context);

    public void changeMenu(int position) {

    }
}
