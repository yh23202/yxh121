package com.yangxiaohe.yangshop;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.yangxiaohe.yangshop.utils.Contants;
import com.yangxiaohe.yangshop.utils.DensityUtils;
import com.yangxiaohe.yangshop.utils.PreferenceUtils;

import java.util.ArrayList;

/**
 * 作者：Administrator on 2017/10/5 23:30
 * 邮箱：281893416@qq.com
 */
public class GuideActivity extends AppCompatActivity{

    private ViewPager mVpGuide;
    private View mGuideSelectPoint;
    private RelativeLayout mContainer;
    private Button mBtStart;
    private static final int[] PICS={R.mipmap.guide_1,R.mipmap.guide_2,R.mipmap.guide_3};
    private ArrayList<ImageView> mImages;
    private int mSpace;
    private ImageView mIvage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        initView();
        initData();

    }

    private void initView() {
        mVpGuide = (ViewPager) findViewById(R.id.vp_guide);
        mGuideSelectPoint = findViewById(R.id.guide_select_point);
        mContainer = (RelativeLayout) findViewById(R.id.ll_guide_container);
        mBtStart = (Button) findViewById(R.id.bt_guide_start);


    }

    private void initData() {
        mImages = new ArrayList<>();
        for (int i = 0; i <PICS.length ; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(PICS[i]);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            mImages.add(imageView);
            View point = new View(this);
            point.setBackgroundResource(R.drawable.point_normal);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(DensityUtils.dp2px(this, 10), DensityUtils.dp2px(this, 10));
            if (i!=0){
                params.leftMargin=DensityUtils.dp2px(this,10);
            }
            mContainer.addView(point,params);
        }
        //布局改变时候的监听
        mContainer.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mSpace = mContainer.getChildAt(1).getLeft() - mContainer.getChildAt(0).getLeft();
                mContainer.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            }
        });
        GuidePagerAdapter adapter = new GuidePagerAdapter();
        mVpGuide.setAdapter(adapter);
        mVpGuide.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mGuideSelectPoint.getLayoutParams();
                params.leftMargin = (int) (mSpace * position + mSpace * positionOffsetPixels+0.5f);
               mGuideSelectPoint.setLayoutParams(params);
            }

            @Override
            public void onPageSelected(int position) {
               mBtStart.setVisibility(position==(PICS.length-1)?View.VISIBLE:View.GONE);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mBtStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PreferenceUtils.putBoolean(GuideActivity.this, Contants.SPLASH_FIRST_USE,false);
                startActivity(new Intent(GuideActivity.this,MainActivity.class));
                finish();
            }
        });
    }
    class GuidePagerAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            if (mImages!=null){
                return mImages.size();
            }
            return 0;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            mIvage = mImages.get(position);
            container.addView(mIvage);
            return mIvage;

        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            //super.destroyItem(container, position, object);
            container.removeView((View) object);
        }
    }
}
