package com.yangxiaohe.yangshop;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;
import com.yangxiaohe.yangshop.fragment.ContentFragment;
import com.yangxiaohe.yangshop.fragment.MenuFragment;
import com.yangxiaohe.yangshop.utils.DensityUtils;

public class MainActivity extends SlidingFragmentActivity {
    private static final String MENU_TAG="menu_tag";
    private static final String CONTENT_TAG="content_tag";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setBehindContentView(R.layout.main_menu);
        setContentView(R.layout.main_content);
        SlidingMenu menu = getSlidingMenu();
        //menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        //menu.setShadowWidthRes(R.dimen.shadow_width);

        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
       // menu.setShadowWidthRes(R.dimen.shadow_width);
        //menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        menu.setBehindWidth(DensityUtils.dp2px(this,140));
        menu.setBehindScrollScale(0.5f);
        menu.setFadeDegree(0.35f);
        menu.setShadowDrawable(R.drawable.shadow);
        initFragment();
       // menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
       // menu.setMenu(R.layout.menu);
        //setContentView(R.layout.main_menu);
        //setBehindContentView(R.layout.main_menu);

    }

    private void initFragment() {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = supportFragmentManager.beginTransaction();
        transaction.replace(R.id.main_menu_container,new MenuFragment(),MENU_TAG);
        transaction.replace(R.id.main_content_container,new ContentFragment(),CONTENT_TAG);
        transaction.commit();
    }
    public MenuFragment getMenuFragment(){
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        //supportFragmentManager.findFragmentById(MENU_TAG);
       return (MenuFragment) supportFragmentManager.findFragmentByTag(MENU_TAG);


    }
    public ContentFragment getContentFragment(){
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        //supportFragmentManager.findFragmentById(MENU_TAG);
       return (ContentFragment) supportFragmentManager.findFragmentByTag(CONTENT_TAG);
    }
}
