package com.face;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.ComponentName;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.face.activity.LoginActivity;
import com.face.activity.PublishActivity;
import com.face.base.BaseActivity;
import com.face.fragment.FriendFragment;
import com.face.fragment.HomeFragment;
import com.face.fragment.MessageFragment;
import com.face.fragment.ProfileFragment;
import com.face.utils.PreferencesUtil;
import com.face.view.KickBackAnimator;
import com.next.easynavigation.utils.NavigationUtil;
import com.next.easynavigation.view.EasyNavigationBar;
import face.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {
    private EasyNavigationBar navigationBar;

    private String[] tabText = {"首页", "发现", "消息", "我的"};

    private String[] tabText2 = {"商城", "订单", "地址"};
    //未选中icon
    private int[] normalIcon = {R.mipmap.index, R.mipmap.find,  R.mipmap.message, R.mipmap.me};
    //选中时icon
    private int[] selectIcon = {R.mipmap.index1, R.mipmap.find1, R.mipmap.message1, R.mipmap.me1};

    private List<Fragment> fragments = new ArrayList<>();
    private List<Fragment> changeFragments = new ArrayList<>();


    //仿微博图片和文字集合
    private int[] menuIconItems = {R.mipmap.pic1, R.mipmap.pic2, R.mipmap.pic3, R.mipmap.pic4};
    private String[] menuTextItems = {"文字", "拍摄", "相册", "直播"};

    private LinearLayout menuLayout;
    private View cancelImageView;
    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(!isLogin()){
            toLogin();
            return;
        }
        setContentView(R.layout.weibo);

        navigationBar = findViewById(R.id.navigationBar);

        fragments.add(new HomeFragment());
        fragments.add(new FriendFragment());
        fragments.add(new MessageFragment());
        fragments.add(new ProfileFragment());

        navigationBar.titleItems(tabText)
                .centerImageRes(R.mipmap.add_image)
                .fragmentList(fragments)
                .fragmentManager(getSupportFragmentManager())
                .tabTextSize(14)   //Tab文字大小
                .normalTextColor(Color.parseColor("#8E8E93"))   //Tab未选中时字体颜色
                .selectTextColor(Color.parseColor("#FFFFFF"))   //Tab选中时字体颜色
                .navigationBackground(Color.parseColor("#202020"))   //导航栏背景色
                .centerLayoutRule(EasyNavigationBar.RULE_CENTER)
                .setOnCenterTabClickListener(new EasyNavigationBar.OnCenterTabSelectListener(){
                    @Override
                    public boolean onCenterTabSelectEvent(View view) {
                        ComponentName cn = new ComponentName(MainActivity.this, PublishActivity.class);
                        Intent intent = new Intent();
                        intent.setComponent(cn);
                        startActivity(intent);
                        return false;
                    }
                })
                .mode(EasyNavigationBar.NavigationMode.MODE_ADD)
                .centerImageRes(R.mipmap.add_image)
                .centerLayoutHeight(44)   //包含加号的布局高度 背景透明  所以加号看起来突出一块
                .navigationHeight(44)  //导航栏高度
                .build();
    }

    private boolean isLogin(){
        return PreferencesUtil.getInstance().isLogin();
    }

    private void toLogin(){
        Intent intent = new Intent(this, LoginActivity.class);
        this.startActivity(intent);
        finish();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if(!isLogin()){
            toLogin();
        }
    }

    private void showMunu() {
        startAnimation();
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                //＋ 旋转动画
                cancelImageView.animate().rotation(90).setDuration(400);
            }
        });
        //菜单项弹出动画
        for (int i = 0; i < menuLayout.getChildCount(); i++) {
            final View child = menuLayout.getChildAt(i);
            child.setVisibility(View.INVISIBLE);
            mHandler.postDelayed(new Runnable() {

                @Override
                public void run() {
                    child.setVisibility(View.VISIBLE);
                    ValueAnimator fadeAnim = ObjectAnimator.ofFloat(child, "translationY", 600, 0);
                    fadeAnim.setDuration(500);
                    KickBackAnimator kickAnimator = new KickBackAnimator();
                    kickAnimator.setDuration(500);
                    fadeAnim.setEvaluator(kickAnimator);
                    fadeAnim.start();
                }
            }, i * 50 + 100);
        }
    }


    private void startAnimation() {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                try {
                    //圆形扩展的动画
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                        int x = NavigationUtil.getScreenWidth(MainActivity.this) / 2;
                        int y = (int) (NavigationUtil.getScreenHeith(MainActivity.this) - NavigationUtil.dip2px(MainActivity.this, 25));
                        Animator animator = ViewAnimationUtils.createCircularReveal(navigationBar.getAddViewLayout(), x,
                                y, 0, navigationBar.getAddViewLayout().getHeight());
                        animator.addListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationStart(Animator animation) {
                                navigationBar.getAddViewLayout().setVisibility(View.VISIBLE);
                            }

                            @Override
                            public void onAnimationEnd(Animator animation) {
                                //							layout.setVisibility(View.VISIBLE);
                            }
                        });
                        animator.setDuration(300);
                        animator.start();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }



    public EasyNavigationBar getNavigationBar() {
        return navigationBar;
    }


    public void changeStyle() {
        navigationBar
                .defaultSetting()
                .fragmentList(changeFragments)
                .fragmentManager(getSupportFragmentManager())
                .titleItems(tabText2)
                .centerLayoutRule(EasyNavigationBar.RULE_BOTTOM)
                .setOnCenterTabClickListener(new EasyNavigationBar.OnCenterTabSelectListener() {
                    @Override
                    public boolean onCenterTabSelectEvent(View view) {
                        Toast.makeText(getApplicationContext(), "hhh,已经更改样式了", Toast.LENGTH_SHORT).show();
                        return false;
                    }
                })
                .textSizeType(EasyNavigationBar.TextSizeType.TYPE_SP)
                .build();


//        navigationBar.setAddViewLayout(createWeiboView());
    }
}
