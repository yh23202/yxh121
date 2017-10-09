package com.yangxiaohe.yangshop.fragment;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.yangxiaohe.yangshop.MainActivity;
import com.yangxiaohe.yangshop.R;
import com.yangxiaohe.yangshop.controller.GovController;
import com.yangxiaohe.yangshop.controller.HomeController;
import com.yangxiaohe.yangshop.controller.NewsCenterController;
import com.yangxiaohe.yangshop.controller.SettingController;
import com.yangxiaohe.yangshop.controller.SmartSerViceController;
import com.yangxiaohe.yangshop.controller.TabController;
import com.yangxiaohe.yangshop.view.NoScrollViewPager;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 作者：Administrator on 2017/10/6 01:32
 * 邮箱：281893416@qq.com
 */
public class ContentFragment extends Fragment {
    @BindView(R.id.np_content)
    NoScrollViewPager npContent;
    @BindView(R.id.content_rb_home)
    RadioButton contentRbHome;
    @BindView(R.id.content_rb_newscenter)
    RadioButton contentRbNewscenter;
    @BindView(R.id.content_rb_smart)
    RadioButton contentRbSmart;
    @BindView(R.id.content_rb_gov)
    RadioButton contentRbGov;
    @BindView(R.id.content_rb_setting)
    RadioButton contentRbSetting;
    @BindView(R.id.rg_content_root)
    RadioGroup rgContentRoot;
    Unbinder unbinder;
    private ArrayList<TabController> mPagerDatas;
    private int mCurrentIndex=0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.content_fragment, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
      /*  NoScrollViewPager mNpContent= (NoScrollViewPager) view.findViewById(R.id.np_content);
        view.findViewById(R.id.rg_content_root);*/
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //ContentAdapter adapter=new ContentAdapter();
        mPagerDatas = new ArrayList<>();
        mPagerDatas.add(new HomeController(getActivity()));
        mPagerDatas.add(new NewsCenterController(getActivity()));
        mPagerDatas.add(new SmartSerViceController(getActivity()));
        mPagerDatas.add(new GovController(getActivity()));
        mPagerDatas.add(new SettingController(getActivity()));
        ContentAdatper adatper = new ContentAdatper();
        npContent.setAdapter(adatper);
        //设置选中时候的监听
        rgContentRoot.setOnCheckedChangeListener(new TabCheckedChangeListener());
        //默认选中
        rgContentRoot.check(R.id.content_rb_home);
    }
    class TabCheckedChangeListener implements RadioGroup.OnCheckedChangeListener {

        @Override
        public void onCheckedChanged(RadioGroup radioGroup, @IdRes int checkId) {
          switch (checkId){
              case R.id.content_rb_home:
                  mCurrentIndex=0;
                  enableSlidingMenu(false);
                  break;
              case R.id.content_rb_newscenter:
                  mCurrentIndex=1;
                  enableSlidingMenu(true);
                  break;
              case R.id.content_rb_smart:
                  mCurrentIndex=2;
                  enableSlidingMenu(true);
                  break;
              case R.id.content_rb_gov:
                  mCurrentIndex=3;
                  enableSlidingMenu(true);
                  break;
              case R.id.content_rb_setting:
                  mCurrentIndex=4;
                  enableSlidingMenu(false);
                  break;
          }
          npContent.setCurrentItem(mCurrentIndex);
        }

    }

    private void enableSlidingMenu(boolean enable) {
        SlidingMenu menu = ((MainActivity) getActivity()).getSlidingMenu();
        menu.setTouchModeAbove(enable?SlidingMenu.TOUCHMODE_FULLSCREEN:SlidingMenu.TOUCHMODE_NONE);
    }

    class ContentAdatper extends PagerAdapter{

       @Override
       public int getCount() {
           if (mPagerDatas!=null){
               return mPagerDatas.size();
           }
           return 0;
       }

       @Override
       public boolean isViewFromObject(View view, Object object) {
           return view==object;
       }

       @Override
       public Object instantiateItem(ViewGroup container, int position) {
           TabController controller = mPagerDatas.get(position);
           View rootView = controller.getRootView();
           container.addView(rootView);
           controller.initData();
           return rootView;
       }

       @Override
       public void destroyItem(ViewGroup container, int position, Object object) {
           super.destroyItem(container, position, object);
           container.removeView((View) object);
       }
   }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
