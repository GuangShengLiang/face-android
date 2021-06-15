package com.face.fragment.my;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

/**
 * @author smlzhixing@163.com
 * @date 4/25/21 4:39 PM
 * @describe TODO
 */
public class PagerAdapter extends FragmentStatePagerAdapter {

    private List<String> mData;

    public PagerAdapter(@NonNull FragmentManager fragmentActivity, List<String> data) {
        super(fragmentActivity);
        mData = data;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        if (position == 0) {
            fragment = Page1Fragment.newInstance(mData.get(position));
        } else if (position == 1) {
            fragment = Page2Fragment.newInstance(mData.get(position));
        } else if (position == 2) {
            fragment = Page3Fragment.newInstance(mData.get(position));
        } else if (position == 3) {
            fragment = Page4Fragment.newInstance(mData.get(position));
        } else if (position == 4) {
            fragment = Page5Fragment.newInstance(mData.get(position));
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mData.get(position);
    }
}
