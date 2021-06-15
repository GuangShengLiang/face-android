package com.face.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import face.R;

public class BackDialog extends Dialog implements android.view.View.OnClickListener {
    private Context context;
    private TextView contents, oks, cancels;
    private Dialog3ClickListener cl;
    private String contentss, okss, cancelss;

    public interface Dialog3ClickListener {
        void onClick(View view);
    }

    public BackDialog(Context context, String contentss, String okss, String cancelss, int theme, Dialog3ClickListener cl) {
        super(context, theme);
        this.context = context;
        this.cl = cl;
        this.contentss = contentss;
        this.okss = okss;
        this.cancelss = cancelss;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.back_dialog);
        initUI();
        initValue();
        initData();
    }

    private void initUI() {
        contents = findViewById(R.id.content);
        oks = findViewById(R.id.ok);
        cancels = findViewById(R.id.cancel);
    }

    private void initData() {
        oks.setOnClickListener(this);
        oks.setText(okss);
        cancels.setOnClickListener(this);
        cancels.setText(cancelss);
        contents.setText(contentss);
    }

    private void initValue() {
        Window window = getWindow();
        assert window != null;
        WindowManager.LayoutParams lp = window.getAttributes();
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        lp.width = dm.widthPixels;
        lp.gravity = Gravity.CENTER;
        window.setAttributes(lp);
    }

    @Override
    public void onClick(View v) {
        cl.onClick(v);
    }

}
