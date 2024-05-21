package com.emon.pocketwallet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;

public class ViewPaperAdapter extends FragmentStateAdapter{

    ArrayList<Fragment> fragmentArrayListarrayList;

    public ViewPaperAdapter(@NonNull FragmentActivity fragmentActivity, ArrayList<Fragment> fragmentArrayListarrayList) {
        super(fragmentActivity);
        this.fragmentArrayListarrayList = fragmentArrayListarrayList;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return fragmentArrayListarrayList.get(position);
    }

    @Override
    public int getItemCount() {
        return fragmentArrayListarrayList.size();
    }
}