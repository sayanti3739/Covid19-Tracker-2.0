package com.example.covid19tracker;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class MyAdapter extends FragmentPagerAdapter {

    private Context context;
    int totalTabs;

    public MyAdapter(Context context, FragmentManager fm, int totalTabs) {
        super(fm);
        this.context = context;
        this.totalTabs = totalTabs;
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: IndiaFragment indiaFragment = new IndiaFragment();
                    return indiaFragment;

            case 1: StateFragment stateFragment = new StateFragment();
                return stateFragment;

            case 2: CountryFragment countryFragment = new CountryFragment();
                return countryFragment;

            default: return null;
        }
    }

    @Override
    public int getCount() {
        return totalTabs;
    }
}
