package com.example.face.view;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.face.R;

public class CustomTitleBar extends RelativeLayout {
    private ImageView ivBack;
    private TextView tvTitle;
    private TextView tvMore;

    public CustomTitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);

        initView(context,attrs);
    }

    //初始化视图
    private void initView(final Context context, AttributeSet attributeSet) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.layout_titlebar, this);
        ivBack = inflate.findViewById(R.id.iv_back);
        tvTitle = inflate.findViewById(R.id.tv_title);
        tvMore = inflate.findViewById(R.id.tv_more);
        init(context,attributeSet);
        if(context instanceof Activity) {
            ivBack.setOnClickListener((s)->
                    ((Activity) context).finish()
            );
        }
    }

    //初始化资源文件
    public void init(Context context, AttributeSet attributeSet){
        TypedArray typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.CustomTitleBar);
        String title = typedArray.getString(R.styleable.CustomTitleBar_title);//标题
        int leftIcon = typedArray.getResourceId(R.styleable.CustomTitleBar_left_icon, R.drawable.barrow_left);//左边图片
//        int rightIcon = typedArray.getResourceId(R.styleable.CustomTitleBar_right_icon, R.drawable.icon_more);//右边图片
        String rightText = typedArray.getString(R.styleable.CustomTitleBar_right_text);//右边文字
//        int titleBarType = typedArray.getInt(R.styleable.CustomTitleBar_titlebar_type, 10);//标题栏类型,默认为10
//
//        //赋值进去我们的标题栏
        tvTitle.setText(title);
        ivBack.setImageResource(leftIcon);
        tvMore.setText(rightText);
//
        //可以传入type值,可自定义判断值
//        if(titleBarType == 10){//不传入,默认为10,显示更多 文字,隐藏更多图标按钮
//            tvMore.setVisibility(View.VISIBLE);
//        }else if(titleBarType == 11){//传入11,显示更多图标按钮,隐藏更多 文字
//            tvMore.setVisibility(View.GONE);
//        }
    }

    //左边图片点击事件
    public void setLeftIconOnClickListener(OnClickListener l){
        ivBack.setOnClickListener(l);
    }

    //右边图片点击事件
    public void setRightIconOnClickListener(OnClickListener l){
        ivBack.setOnClickListener(l);
    }

    //右边文字点击事件
    public void setRightTextOnClickListener(OnClickListener l){
        tvMore.setOnClickListener(l);
    }
}
