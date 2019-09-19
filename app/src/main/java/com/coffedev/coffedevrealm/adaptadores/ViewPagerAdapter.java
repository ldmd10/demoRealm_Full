package com.coffedev.coffedevrealm.adaptadores;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.coffedev.coffedevrealm.adaptadores.interfaces.ViewPager;

import java.util.LinkedList;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private LinkedList<Fragment> agrupadorViewPager;


    public ViewPagerAdapter(FragmentManager fm, LinkedList<Fragment> agrupadorViewPager) {
        super(fm);
        this.agrupadorViewPager = agrupadorViewPager;

    }


    @Override
    public Fragment getItem(int i) {
        return agrupadorViewPager.get(i);
    }

    @Override
    public int getCount() {
        return agrupadorViewPager.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return ((ViewPager) agrupadorViewPager.get(position)).getTitle();
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        int index = agrupadorViewPager.indexOf(object);
        if (index == -1)
            return POSITION_NONE;
        else
            return index;
    }


}
