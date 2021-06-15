package com.face.activitys.user;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.face.base.BaseActivity;
import com.face.dialog.DoubleDatePickerDialog;
import com.face.http.BaseObserver;
import com.face.http.HTTP;
import com.face.http.model.JsonResponse;
import com.face.http.model.param.MyInfoUpdateParam;
import com.face.http.model.vo.MyInfoVO;

import java.util.Calendar;

import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import face.R;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static com.face.utils.TimeUtils.getDateToString;

/**
 * @author smlzhixing@163.com
 * @date 6/15/21
 * @describe TODO 个人资料
 */

public class UserInfoActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.head_img_left)
    View headImgLeft;
    @BindView(R.id.head_text_title)
    TextView headTextTitle;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.li_name)
    RelativeLayout liName;
    @BindView(R.id.sex)
    TextView sex;
    @BindView(R.id.li_sex)
    RelativeLayout liSex;
    @BindView(R.id.birthday)
    TextView birthday;
    @BindView(R.id.li_birthday)
    RelativeLayout liBirthday;
    @BindView(R.id.emotion)
    TextView emotion;
    @BindView(R.id.li_emotion)
    RelativeLayout liEmotion;
    @BindView(R.id.place)
    TextView place;
    @BindView(R.id.li_place)
    RelativeLayout liPlace;
    @BindView(R.id.introduce)
    TextView introduce;
    @BindView(R.id.li_introduce)
    RelativeLayout liIntroduce;

    private Intent intent;
    private PopupWindow popWindow;
    private View view;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        headTextTitle.setText("个人资料");
        HTTP.account.myInfo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<JsonResponse<MyInfoVO>>() {
                    @Override
                    public void onNext(JsonResponse<MyInfoVO> a) {
                        name.setText(a.getData().getNickName());
                        sex.setText(a.getData().getGenderName());
                        birthday.setText(getDateToString(a.getData().getBirthdayTime()));
                        emotion.setText(a.getData().getLoveStatusName());
                        place.setText("");
                        introduce.setText("");
//                        CommonUtil.loadAvatar(getContext(), mAvatarSdv, a.getData().getAvatar());
                    }
                });
    }

    @OnClick({R.id.head_img_left, R.id.li_name, R.id.li_sex, R.id.li_birthday, R.id.li_emotion, R.id.li_place, R.id.li_introduce})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.head_img_left:
                finish();
                break;
            case R.id.li_name:
                intent = new Intent(getApplicationContext(), UpdateNameActivity.class);
                intent.putExtra("name", name.getText().toString());
                startActivityForResult(intent, 10000);
                break;
            case R.id.li_sex:
                showPopupWindow(liSex);
                break;
            case R.id.li_birthday:
                Calendar c = Calendar.getInstance();
                new DoubleDatePickerDialog(UserInfoActivity.this, 0, new DoubleDatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker startDatePicker, int startYear, int startMonthOfYear,
                                          int startDayOfMonth
                    ) {
                        String textString = String.format("%d-%d-%d\n", startYear,
                                startMonthOfYear + 1, startDayOfMonth);
                        birthday.setText(textString);
                    }
                }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DATE), true).show();
                break;
            case R.id.li_emotion:
                break;
            case R.id.li_place:
                break;
            case R.id.li_introduce:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case 10000:
                    initView();
                    break;
            }
        }
    }

    private void showPopupWindow(View parent) {
        if (popWindow == null) {
            view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.pop_select_photo, null);
            popWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
        }
        initPop(view);
        popWindow.setAnimationStyle(android.R.style.Animation_InputMethod);
        popWindow.setFocusable(true);
        popWindow.setOutsideTouchable(true);
        popWindow.setBackgroundDrawable(new BitmapDrawable());
        popWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        popWindow.showAtLocation(parent, Gravity.CENTER, 0, 0);
    }

    public void initPop(View view) {
        TextView photograph = view.findViewById(R.id.photograph);
        TextView albums = view.findViewById(R.id.albums);
        TextView other = view.findViewById(R.id.other);
        LinearLayout cancel = view.findViewById(R.id.cancel);
        photograph.setOnClickListener(this);
        albums.setOnClickListener(this);
        other.setOnClickListener(this);
        cancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.photograph:
                update(0);
                popWindow.dismiss();
                break;
            case R.id.albums:
                update(1);
                popWindow.dismiss();
                break;
            case R.id.other:
                update(-1);
                break;
            case R.id.cancel:
                popWindow.dismiss();
                break;
        }
    }

    private void update(int sex) {
        MyInfoUpdateParam param = new MyInfoUpdateParam();
        param.setGender(sex);
        HTTP.account.updateMyInfo(param)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<JsonResponse>() {
                    @Override
                    public void onNext(JsonResponse a) {
                        initView();
                    }
                });
    }


}