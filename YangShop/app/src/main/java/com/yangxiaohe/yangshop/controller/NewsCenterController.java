package com.yangxiaohe.yangshop.controller;

import android.content.Context;
import android.provider.Settings;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.viewpagerindicator.TabPageIndicator;
import com.yangxiaohe.yangshop.MainActivity;
import com.yangxiaohe.yangshop.R;
import com.yangxiaohe.yangshop.bean.NewsCenterBean;
import com.yangxiaohe.yangshop.controller.TabController;
import com.yangxiaohe.yangshop.fragment.MenuFragment;
import com.yangxiaohe.yangshop.utils.Contants;
import com.yangxiaohe.yangshop.utils.PreferenceUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：Administrator on 2017/10/6 10:19
 * 邮箱：281893416@qq.com
 */
public class NewsCenterController extends TabController {

    private TabPageIndicator mNewsIndicator;
    private ViewPager mNewsPager;
    private ArrayList<BaseController> controllers;
    private OnListOrGridClickListener mOnListOrGridClickListener;
    private FrameLayout mContainer;
    private List<NewsCenterBean.DataBean> dataBeen;

    public NewsCenterController(FragmentActivity activity) {
        super(activity);
    }

    @Override
    protected View initContentView(Context context) {
        mContainer = new FrameLayout(context);
        ivTabGridType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnListOrGridClickListener!=null){
                    mOnListOrGridClickListener.onClick(ivTabGridType);
                }
            }
        });
        return mContainer;
    }

    @Override
    public void initData() {
        super.initData();
        //PreferenceUtils.getString(mContext,)
        final String url = Contants.NEWSCENTER_URL;
        String json = PreferenceUtils.getString(mContext, url);
        if (!TextUtils.isEmpty(json)){
            long current = PreferenceUtils.getLong(mContext, url + "time");
            if (current+Contants.DATA_DALEY<System.currentTimeMillis()){
                //先读取缓存数据
                processData(json);
                //然后在加载网络数据
                loadNetData();
            }else {
                //数据没有过期
                processData(json);
            }
        }else {
            loadNetData();
        }

        //String url = PreferenceUtils.getString(mContext, url);
       /* RequestQueue queue = Volley.newRequestQueue(mContext);
        Response.Listener<String> success = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                 PreferenceUtils.putLong(mContext,url,System.currentTimeMillis());
                PreferenceUtils.putString(mContext,url,response);
                //加载缓存
                 processData(response);
            }
        };
        Response.ErrorListener error = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        };
        StringRequest request = new StringRequest(Request.Method.GET, url, success, error);
        queue.add(request);*/
    }

    private void loadNetData() {
        final String url = Contants.NEWSCENTER_URL;
        RequestQueue queue = Volley.newRequestQueue(mContext);
        Response.Listener<String> success = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                PreferenceUtils.putString(mContext,url,response);
                PreferenceUtils.putLong(mContext,url+"time",System.currentTimeMillis());
                processData(response);
            }
        };
        Response.ErrorListener error = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        };
        StringRequest request = new StringRequest(Request.Method.GET, url, success, error);
        queue.add(request);
    }

    private void processData(String response) {
        Gson gson = new Gson();
        NewsCenterBean newsCenterBean = gson.fromJson(response, NewsCenterBean.class);
        dataBeen = newsCenterBean.getData();
        MenuFragment menuFragment = ((MainActivity) mContext).getMenuFragment();
        menuFragment.setMenuData(dataBeen);
        controllers = new ArrayList<>();
        for (int i = 0; i < dataBeen.size(); i++) {
            BaseController controll=null;
            NewsCenterBean.DataBean menu = dataBeen.get(i);
            switch (menu.getType()){
                case 0:
                    //新闻菜单
                    controll = new NewsMenuControll(mContext, menu.getChildren());
                    break;
                case 1:
                    //组图菜单
                    controll=new PicMenuController(mContext);
                    setOnListOrGridClickListener((OnListOrGridClickListener) controll);
                    break;
                case 2:
                    //动画菜单
                    controll=new InteractMenuController(mContext);
                    break;
                case 3:
                    //分类菜单
                    controll=new SubjectMenuController(mContext);
                    break;
            }
            controllers.add(controll);
        }
        changeMenu(0);
    }

    @Override
    public void changeMenu(int position) {
        super.changeMenu(position);
        mContainer.removeAllViews();
        //BaseController baseController = controllers.get(position);
        NewsCenterBean.DataBean bean = dataBeen.get(position);
        tvTabTitle.setText(bean.getTitle());
        BaseController baseController = controllers.get(position);
        View rootView = baseController.initView(mContext);
        mContainer.addView(rootView);
        baseController.initData();
        ivTabGridType.setVisibility(baseController instanceof PicMenuController?View.VISIBLE:View.GONE);
        //bean.getTitle();
      /*  BaseController controller = controllers.get(position);
        View view = controller.initView(mContext);
        controller.initData();*/
    }
    public void setOnListOrGridClickListener(OnListOrGridClickListener onListOrGridClickListener){
        this.mOnListOrGridClickListener=onListOrGridClickListener;
    }
    public interface OnListOrGridClickListener{
       void onClick(ImageView iv);
   }

}
