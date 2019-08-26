package com.example.face.weibo;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.face.R;
import com.example.face.common.data.fixtures.DialogsFixtures;
import com.example.face.common.data.model.Dialog;
import com.squareup.picasso.Picasso;
import com.stfalcon.chatkit.commons.ImageLoader;
import com.stfalcon.chatkit.dialogs.DialogsList;
import com.stfalcon.chatkit.dialogs.DialogsListAdapter;
import com.stfalcon.chatkit.utils.DateFormatter;

import java.util.Date;


/**
 * Created by Jue on 2018/6/2.
 */

public class AddThirdFragment extends Fragment implements DateFormatter.Formatter{
    private DialogsList dialogsList;
    protected ImageLoader imageLoader;
    protected DialogsListAdapter<Dialog> dialogsAdapter;
    private Context context;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_styled_dialogs_bar, container,false);

        dialogsList = (DialogsList)view.findViewById(R.id.dialogsList);
        context = this.getContext();
        imageLoader = new ImageLoader() {
            @Override
            public void loadImage(ImageView imageView, String url, Object payload) {
//                Picasso.get(context).load(url).into(imageView);
            }
        };
        initAdapter();
//        StyledDialogsActivity.open(this.getContext());
        return view;
    }
    public void onDialogClick(Dialog dialog) {
//        StyledMessagesActivity.open(this.getContext());
    }

    public String format(Date date) {
        if (DateFormatter.isToday(date)) {
            return DateFormatter.format(date, DateFormatter.Template.TIME);
        } else if (DateFormatter.isYesterday(date)) {
            return getString(R.string.date_header_yesterday);
        } else if (DateFormatter.isCurrentYear(date)) {
            return DateFormatter.format(date, DateFormatter.Template.STRING_DAY_MONTH);
        } else {
            return DateFormatter.format(date, DateFormatter.Template.STRING_DAY_MONTH_YEAR);
        }
    }

    private void initAdapter() {
        dialogsAdapter = new DialogsListAdapter<>(imageLoader);
        dialogsAdapter.setItems(DialogsFixtures.getDialogs());

//        dialogsAdapter.setOnDialogClickListener(this);
//        dialogsAdapter.setOnDialogLongClickListener(this);
//        dialogsAdapter.setDatesFormatter(this);

//        dialogsList.setAdapter(dialogsAdapter);
    }

}
